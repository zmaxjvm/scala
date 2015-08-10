/**
 * Created by zmax.
 */
object Test {

  def show(x: Option[String]): Any = {
    x match {
      case Some(s) => s
      case None => "?"
    }
  }

}
