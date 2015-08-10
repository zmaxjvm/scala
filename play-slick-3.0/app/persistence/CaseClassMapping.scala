package persistence
import slick.driver.PostgresDriver.api._

//object CaseClassMapping

case class User(id: Option[Int] = None, name: String)
case class Email(id: Option[Int] = None, idUser: Int, address: String)


class TableUser(tag: Tag) extends Table[User](tag, "USERS") {
  // Auto Increment the id primary key column
  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
  def name = column[String]("NAME")
  // the * projection (e.g. select * ...) auto-transforms the tupled
  // column values to / from a User

  def * = (id.?, name) <>(User.tupled, User.unapply)
}

class TableEmail(tag: Tag) extends Table[Email](tag, "EMAILS") {
  // Auto Increment the id primary key column
  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
  def idUser = column[Int]("ID_USER")
  def address = column[String]("NAME")

  foreignKey("SUP_FK", idUser, TableQuery[TableUser])(_.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Cascade)

  // the * projection (e.g. select * ...) auto-transforms the tupled
  // column values to / from a User
  def * = (id.?, idUser, address) <>(Email.tupled, Email.unapply)
}

