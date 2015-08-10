import org.scalatest._
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Seconds, Span}
import persistence.{Email, TableUser, User, DBService}

/**
 * @author zmax.
 */
class TablesSuite extends FunSuite with BeforeAndAfter with ScalaFutures {

  test("Creating the Schema works") {
    DBService.createSchema
  }

  test("insert user data") {
    DBService.insertUser(User(name = "Max"))
    DBService.insertUser(User(name = "Martin"))
    DBService.insertUser(User(name = "Martin"))
    DBService.insertUser(User(name = "Martin"))
  }

  test("insert email data") {
    DBService.insertEmail(Email(idUser = 1, address = "test@test1.com"))
  }

  test("get user by id") {
    DBService.userById(1)
  }

  test("monadic cross join") {
    DBService.crossJoin
  }

}
