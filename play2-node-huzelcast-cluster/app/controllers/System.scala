package controllers

import java.util.Calendar
import cache.CacheHazelcast
import play.api.mvc._
import play.api.mvc.Codec._

/**
 * @author zmax.
 */
object System extends Controller {

  def gethz(k: String) = Action {
    CacheHazelcast.get(k) match {
      case None => Ok("Hazelcast cache is empty")
      case _ => Ok(CacheHazelcast.get(k).get.toString )
      //case Some(s) => Ok(s.toString ) TODO
    }
  }

  def puthz(k: String, v: String) = Action {
    CacheHazelcast.put(k, v)
    Ok("Added " + Calendar.getInstance().getTime)
  }

  //TODO do not create Action for the each page "split pages types and reuse functions"

  //TODO add function for the dynamic pages generation"

  //TODO add function for the static pages "flexible URL handling" == "use properties file for the matching 'short name / full name' "

}