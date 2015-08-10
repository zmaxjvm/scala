package elementary

import org.scalatest.FunSuite

/**
 * @author zmax.
 */
class AbstractionExampl extends FunSuite {

  /**
   * Writing new control structures
   */
  //This satisfies the Liskov substitution principle
  // trait Function1[@scala.specialized -T1, @scala.specialized +R] extends scala.AnyRef {
  trait Covariance[+T]

  class A(a: Int)

  class B(b: Int) extends A(b: Int)

  class C(c: Int) extends B(c: Int)

  /**
   * High order function example 1
   * this example demonstrates how closures can help you reduce code duplication.
   *
   * placeholder syntax: _.endsWith(_).
   *
   */
  test("Execute function with function by parameter") {
    val strings = List("hello", "hello1", "hello1", "testtesttest")
    println(filesMatching(strings, "1", _.contains(_)))
    println(filesMatching(strings, "hello", _.equals(_)))

    def demo(first: String, second: String) = first.length > second.length
    println(filesMatching(strings, "testtesttes", demo))
    //or
    println(filesMatching(strings, "testtesttes", _.length > _.length))
  }

  /**
   * Covariance and contravariance of Function1s.
   */
  test("Covariance and contravariance example") {


  }

  test("Upper Type Bounds example") {

    trait Similar {
      def isSimilar(x: Any): Boolean
    }

    case class MyInt(x: Int) extends Similar {
      def isSimilar(m: Any): Boolean = m.isInstanceOf[MyInt] && m.asInstanceOf[MyInt].x == x
    }
    //Without the 'upper type bound - <: Similar'  annotation it would not be possible to call method isSimilar
    def findSimilar[T <: Similar](e: T, xs: List[T]): Boolean =
      if (xs.isEmpty) false
      else if (e.isSimilar(xs.head)) true
      else findSimilar[T](e, xs.tail)


    val list: List[MyInt] = List(MyInt(1), MyInt(2), MyInt(3))
    println("sim = " + findSimilar[MyInt](MyInt(4), list))
    println("sim = " + findSimilar[MyInt](MyInt(2), list))
  }

  /**
   * Lower type bounds declare a type to be a super type of another type.
   * The term T >: A expresses that the type parameter T or the abstract type T refer to a supertype of type A.
   */
  test("Lower Type Bounds example") {

    trait Similar {
      def isSimilar(x: Any): Boolean
    }

    case class MyInt(x: Int) extends Similar {
      def isSimilar(m: Any): Boolean = m.isInstanceOf[MyInt] && m.asInstanceOf[MyInt].x == x
    }
    //Without the 'upper type bound - <: Similar'  annotation it would not be possible to call method isSimilar
    def findSimilar[T <: Similar](e: T, xs: List[T]): Boolean =
      if (xs.isEmpty) false
      else if (e.isSimilar(xs.head)) true
      else findSimilar[T](e, xs.tail)


    val list: List[MyInt] = List(MyInt(1), MyInt(2), MyInt(3))
    println("sim = " + findSimilar[MyInt](MyInt(4), list))
    println("sim = " + findSimilar[MyInt](MyInt(2), list))
  }

  //Store function to the variable
  val lambda = new Function1[Int, Int] {
    def apply(v1: Int): Int = v1 + 1
  }

  //Passes function to the function
  def summation(x: Int, y: Int => Int) = y(x)

  //Execution
  val result0 = summation(10, (x: Int) => x + 1)
  val result1 = summation(10, lambda)


  //take a function and return another function -> (f2: Int => Int)
  def sum(f: Int => Int): (Int, Int) => Int = {
    def sumF(a: Int, b: Int): Int =
      if (a > b) 0
      else f(a) + sumF(a + 1, b)
    sumF
  }


  /**
   * Show how to execute function with different 'matcher' function
   * @param strings
   * @param query
   * @param matcher
   * @return
   */
  def filesMatching(strings: List[String],
                    query: String,
                    matcher: (String, String) => Boolean) = {

    for (str <- strings; if matcher(str, query))
    yield str
  }

  def function(i: String) = "hello"


  /**
   * Curried function with partially applied function
   */
  def multiplyCurried(x: Int)(y: Int) = x * y

  def multiply2 = multiplyCurried(2) _

  def multiply4 = multiplyCurried(4) _

  test("Curried multiplication") {
    println(multiply2(2))
    println(multiply4(2))

    println(multiplyCurried(5)(5))
  }

  /**
   * High order function example 2
   * Function return function
   */
  def sumInts(a: Int, b: Int) = sum(x => x)

  def sumCubes(a: Int, b: Int) = sum(x => x * x * x)

  test("Curried function") {
    val f = sum(x => x * x)
    println(f(1, 2))
  }

}
