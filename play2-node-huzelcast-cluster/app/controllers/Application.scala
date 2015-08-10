package controllers

import play.api._
import play.api.mvc._
import play.twirl.api.Html

/**
 * @author zmax.
 */
object Application extends Controller {

  def index = Action {
    //TODO read content from storage and push it on the page. In this case hard coded into str "just for test"
    val tx = Html("Hello, Welcome to the QuantStore proof of concept, please visit next links <br><br>" +
      "<a href=\"/strategies\">List of strategies</a>" +
      "<br><a href=\"/strategies\">Something else</a>" +
      "<br> <br> If you have any questions, you are welcome to ask.")
    Ok(views.html.index("Quant store POC")(tx))
  }


  //TODO do not create Action for the each page "split pages types and reuse functions"

  //TODO add function for the dynamic pages generation"

  //TODO add function for the static pages "flexible URL handling" == "use properties file for the matching 'short name / full name' "

}