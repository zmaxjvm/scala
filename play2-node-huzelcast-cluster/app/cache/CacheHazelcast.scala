package cache

import com.hazelcast.config.{Config, GroupConfig}
import com.hazelcast.core.Hazelcast

/**
 * @author zmax.
 */
object CacheHazelcast {
  val SHARED_CACHE = "web-node"
  val NODE_GROUP_NAME = "test-web-node"
  val NODE_GROUP_PASS = "test_password"

  val HZ = {
    val gcf = new GroupConfig().setName(NODE_GROUP_NAME).setPassword(NODE_GROUP_PASS)
    Hazelcast.newHazelcastInstance(new Config().setGroupConfig(gcf))
  }

  def get(k: String) = Option(HZ.getMap(SHARED_CACHE).get(k))

  def put(k: String, v: String) = HZ.getMap(SHARED_CACHE).put(k,v)

  def disc{}

}
