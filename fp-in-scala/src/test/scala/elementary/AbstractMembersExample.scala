package elementary

import org.scalatest.FunSuite

/**
 * @author zmax.
 */
class AbstractMembersExample extends FunSuite {

  trait Abstract {
    type T

    def transform(x: T): T

    val initial: T
    var current: T
  }

  test("Covariance and contravariance example") {

  }

  test("Lazy vals") {

  }

}
