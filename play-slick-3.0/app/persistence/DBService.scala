package persistence

import slick.dbio.DBIO
import slick.driver.PostgresDriver.api._
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

/**
 * @author zmax.
 */
object DBService {

  //TODO check driver for your database version for example current example uses 'PostgresDriver'
  //import slick.driver.PostgresDriver.api._

  //val db = Database.forConfig("mysql")
  //val db = Database.forConfig("h2mem")
  val db = Database.forConfig("postgress")

  val user = TableQuery[TableUser]
  val email = TableQuery[TableEmail]
  val mainSchema = user.schema ++ email.schema

  def insertUser(u: User) = try {
    Await.result(db.run(DBIO.seq(
      user += u,
      // print the users (select * from USERS)
      user.result.map(println)
    )), Duration.Inf)
  }

  def insertEmail(u: Email) = try {
    Await.result(db.run(DBIO.seq(
      email += u,
      // print the users (select * from USERS)
      email.result.map(println)
    )), Duration.Inf)
  }

  def userById(id: Int) ={
    val byId = user.findBy(_.id)
    val future = db.run(byId(id).result)
    val users = Await.result(future, Duration.Inf)
    users.map(println)
  }

  def crossJoin = {
    val monadicCrossJoin = for {
      u <- user
      e <- email
      if u.id === e.idUser
    } yield (u, e)

    val future = db.run(monadicCrossJoin.result)
    val res = Await.result(future, Duration.Inf)
    res.map(println)
  }

  def createSchema = try {
    Await.result(db.run(mainSchema.create), Duration.Inf)
  } //finally db.close

  def execQuery(q: String): DBIO[Int] = sqlu"q"
}
