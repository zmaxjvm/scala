import _root_.cache.CacheHazelcast
import com.hazelcast.config.Config
import com.hazelcast.core.Hazelcast
import play.api._
import play.api.cache.Cache
import play.api.Play.current

/**
 * @author zmax.
 */
object Global extends GlobalSettings {

  override def onStart(app: Application) {
    Logger.info("Application has started")
  }

  override def onStop(app: Application) {
    CacheHazelcast.disc
    Logger.info("Application shutdown...")
  }

}
