package elementary

import org.scalatest.FunSuite

/**
 * @author zmax.
 */
class OptionExample extends FunSuite {
  /**
   * Scala’s Map produces
   * Some(value) if a value corresponding to a given key has been found, or
   * None if the given key is not defined in the Map
   */
  test("Option example") {
    val capitals = Map("France" -> "Paris", "Japan" -> "Tokyo")

    println(capitals get "France")
    println(capitals get "North Pole")
  }

}

