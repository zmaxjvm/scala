package elementary

import org.scalatest.FunSuite
import scala.collection.immutable.List

/**
 * @author zmax.
 */
class ForYieldExample extends FunSuite {

  val listNumbers = List(1, 2, 3, 4, 5, 6)
  val listNumbers2 = List(6, 5, 4, 3, 2, 1)
  val str = "HelloWorld"
  val strNums = "12345678"
  val listWords = List("one", "two", "three", "four", "five", "six")

  /**
   * It's like the for loop has a buffer you can't see, and for each iteration of your for loop, another item is added to that buffer
   */
  test("Basic for loop") {
    val collect = for (i <- 1 to 5) yield i
    assert(collect.equals(Vector(1, 2, 3, 4, 5)))

    val collectX2 = for (i <- 1 to 5) yield i * 2
    assert(collectX2.equals(Vector(2, 4, 6, 8, 10)))

    val collectModulus = for (i <- 1 to 5) yield i % 2
    assert(collectModulus.equals(Vector(1, 0, 1, 0, 1)))
  }

  test("for loop yield examples over a Scala Array") {
    val r = for (e <- listNumbers) yield e
    assert(r.equals(List(1, 2, 3, 4, 5, 6)))
  }


}



