import scala.concurrent.{Future, Await}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import slick.backend.DatabasePublisher
import slick.driver.H2Driver.api._

// The main application
object HelloSlick extends App {
  val db = Database.forConfig("postgress1")
  try {

    // The query interface for the Suppliers table
    val suppliers: TableQuery[Suppliers] = TableQuery[Suppliers]

    // the query interface for the Coffees table
    val coffees: TableQuery[Coffees] = TableQuery[Coffees]

    val setupAction: DBIO[Unit] = DBIO.seq(
      // Create the schema by combining the DDLs for the Suppliers and Coffees
      // tables using the query interfaces
      (suppliers.schema ++ coffees.schema).create,

      // Insert some suppliers
      suppliers += (101, "Acme, Inc.", "99 Market Street", "Groundsville", "CA", "95199"),
      suppliers += ( 49, "Superior Coffee", "1 Party Place", "Mendocino", "CA", "95460"),
      suppliers += (150, "The High Ground", "100 Coffee Lane", "Meadows", "CA", "93966")
    )

    val setupFuture: Future[Unit] = db.run(setupAction)
    val f = setupFuture.flatMap { _ =>

      // Insert some coffees (using JDBC's batch insert feature)
      val insertAction: DBIO[Option[Int]] = coffees ++= Seq (
        ("Colombian",         101,  1, 0),
        ("French_Roast",       49,  2, 0),
        ("Espresso",          150,  3, 0),
        ("Colombian_Decaf",   101,  4, 0),
        ("French_Roast_Decaf", 49,  5, 0)
      )

      val insertAndPrintAction: DBIO[Unit] = insertAction.map { coffeesInsertResult =>
        // Print the number of rows inserted
        coffeesInsertResult foreach { numRows =>
          println(s"Inserted $numRows rows into the Coffees table")
        }
      }

      val allSuppliersAction: DBIO[Seq[(Int, String, String, String, String, String)]] =
        suppliers.result

      val combinedAction: DBIO[Seq[(Int, String, String, String, String, String)]] =
        insertAndPrintAction >> allSuppliersAction

      val combinedFuture: Future[Seq[(Int, String, String, String, String, String)]] =
        db.run(combinedAction)

      combinedFuture.map { allSuppliers =>
        allSuppliers.foreach(println)
      }

    }.flatMap { _ =>

      /* Streaming */

      val coffeeNamesAction: StreamingDBIO[Seq[String], String] =
        coffees.map(_.name).result

      val coffeeNamesPublisher: DatabasePublisher[String] =
        db.stream(coffeeNamesAction)

      coffeeNamesPublisher.foreach(println)

    }.flatMap { _ =>

      /* Filtering / Where */

      // Construct a query where the price of Coffees is > 9.0
      val filterQuery: Query[Coffees, (String, Int, Int, Int), Seq] =
        coffees.filter(_.sales > 3)

      // Print the SQL for the filter query
      println("Generated SQL for filter query:\n" + filterQuery.result.statements)

      // Execute the query and print the Seq of results
      db.run(filterQuery.result.map(println))

    }.flatMap { _ =>

      /* Update */

      // Construct an update query with the sales column being the one to update
      val updateQuery: Query[Rep[Int], Int, Seq] = coffees.map(_.sales)

      val updateAction: DBIO[Int] = updateQuery.update(1)

      // Print the SQL for the Coffees update query
      println("Generated SQL for Coffees update:\n" + updateQuery.updateStatement)

      // Perform the update
      db.run(updateAction.map { numUpdatedRows =>
        println(s"Updated $numUpdatedRows rows")
      })

    }.flatMap { _ =>

      /* Delete */

      // Construct a delete query that deletes coffees with a price less than 8.0
      val deleteQuery: Query[Coffees,(String, Int, Int, Int), Seq] =
        coffees.filter(_.sales < 3)

      val deleteAction = deleteQuery.delete

      // Print the SQL for the Coffees delete query
      println("Generated SQL for Coffees delete:\n" + deleteAction.statements)

      // Perform the delete
      db.run(deleteAction).map { numDeletedRows =>
        println(s"Deleted $numDeletedRows rows")
      }

    }.flatMap { _ =>

      /* Sorting / Order By */

      val sortByPriceQuery: Query[Coffees, (String, Int,  Int, Int), Seq] =
        coffees.sortBy(_.sales)

      println("Generated SQL for query sorted by price:\n" +
        sortByPriceQuery.result.statements)

      // Execute the query
      db.run(sortByPriceQuery.result).map(println)

    }.flatMap { _ =>

      /* Query Composition */

      val composedQuery: Query[Rep[String], String, Seq] =
        coffees.sortBy(_.name).take(3).filter(_.sales > 2).map(_.name)

      println("Generated SQL for composed query:\n" +
        composedQuery.result.statements)

      // Execute the composed query
      db.run(composedQuery.result).map(println)

    }.flatMap { _ =>

      /* Joins */

      // Join the tables using the relationship defined in the Coffees table
      val joinQuery: Query[(Rep[String], Rep[String]), (String, String), Seq] = for {
        c <- coffees if c.sales > 1
        s <- c.supplier
      } yield (c.name, s.name)

      println("Generated SQL for the join query:\n" + joinQuery.result.statements)

      // Print the rows which contain the coffee name and the supplier name
      db.run(joinQuery.result).map(println)

    }.flatMap { _ =>

      /* Computed Values */

      // Create a new computed column that calculates the max price
      val maxPriceColumn: Rep[Option[Int]] = coffees.map(_.sales).max

      println("Generated SQL for max price column:\n" + maxPriceColumn.result.statements)

      // Execute the computed value query
      db.run(maxPriceColumn.result).map(println)

    }.flatMap { _ =>

      /* Manual SQL / String Interpolation */

      // A value to insert into the statement
      val state = "CA"

      // Construct a SQL statement manually with an interpolated value
      val plainQuery = sql"select SUP_NAME from SUPPLIERS where STATE = $state".as[String]

      println("Generated SQL for plain query:\n" + plainQuery.statements)

      // Execute the query
      db.run(plainQuery).map(println)

    }
    Await.result(f, Duration.Inf)

  } finally db.close
}
