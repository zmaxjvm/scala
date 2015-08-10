package books

import org.scalatest.FunSuite

import scala.annotation.tailrec
import scala.collection.immutable.List

/**
 * @author zmax.
 *
 * Functional Programming in Scala.
 * by PAUL CHIUSANO and RÚNAR BJARNASON
 */
class FPInScalaExcersises extends FunSuite {

  val abc = List('a', 'b', 'c')
  val abcde = List("a", "b", "c", "d", "e")
  val numbers = List[Double](1, 2, 3, 4, 5)

  /**
   * $$$$$$$
   * Book exercises - Functional Programming in Scala
   * $$$$$$$
   */

  /**
   * Write a recursive function to get the nth Fibonacci number
   */
  test("EXERCISE 2.1") {

  }


  /**
   * Implement the function tail for removing the first element of a List. Note that the function takes constant time.
   */
  test("EXERCISE 3") {
    val list = List('a', 'b', 'c', 'd', 'e')
    val res = for (e <- list.zipWithIndex; if e._2 > 0) yield e._1
    assert(res == List('b', 'c', 'd', 'e'))

    //EXERCISE 3.2, 3.3, 3.4 ...
    assert(getSubList(1, 3, list) == List('b', 'c', 'd'))
    assert(getSubList(0, 3, list) == List('a', 'b', 'c', 'd'))
  }

  /**
   * Get sub list by indexes
   * @param start index
   * @param end index
   * @param list initial list
   * @return
   */
  def getSubList(start: Int, end: Int, list: List[Any]) = for (e <- list.zipWithIndex; if e._2 >= start & e._2 <= end) yield e._1

  /**
   * Write sum, product, and a function to compute the length of a list using foldLeft
   */
  test("EXERCISE 3.11") {
    assert(abc.length == 3)
    assert((abc count (x => true)) == 3)
    assert(abc.foldLeft[Int](0)((x, y) => x + 1) == 3)
  }


  /**
   * reverse of a list (given List(1,2,3) it returns List(3,2,1)). See if you can write it using a fold.
   */
  test("EXERCISE 3.12") {

    val abc = List('a', 'b', 'c')

    assert(abc.foldLeft(List[Any]())((l, e) => List(e) ++ l) == List('c', 'b', 'a'))
    assert(abc.foldLeft(List[Any]())((l, e) => e :: l) == List('c', 'b', 'a'))
    assert(abc.foldLeft(List[Any]())((l, e) => l.::(e)) == List('c', 'b', 'a'))

    println("Folding detailed")
    foldDetailed(abc)

    println("The recursive solution")
    assert(rev(abc) == List('c', 'b', 'a'))
  }

  def foldDetailed(l: List[Any]) = l.foldLeft(List[Any]()) {
    (l: List[Any], e: Any) => {
      println(e)
      println(e :: l)
      e :: l
    }
  }

  def rev(l: List[Any]): List[Any] = l match {
    case Nil => Nil
    case l => {
      println(l.tail)
      println(l.head)
      rev(l.tail) ::: List(l.head) //HOW TO ACCUMULATE IT 'l.head'
    }
  }

  /**
   * Functional combinators
   */

  /**
   * Write a function that transforms a list of integers by adding 1 to each element. (Reminder: this should be a pure function that returns a new List!)
   */
  test("EXERCISE 3.16") {
    assert(abcde.map(e => e + "1") == List("a1", "b1", "c1", "d1", "e1"))

    //  def add1(l: List[Int]): List[Int] = l.foldRight(l, Nil:List[Int])((h,t) => Cons(h+1,t))

  }

  /**
   * Write a function that turns each value in a List[Double] into a String. You can use the expression d.toString to convert some d: Double to a String.
   */
  test("EXERCISE 3.17") {
    assert(numbers.map(_ toString) == List("1.0", "2.0", "3.0", "4.0", "5.0"))
  }

  /**
   * Write a function filter that removes elements from a list unless they satisfy a given predicate. Use it to remove all odd numbers from a List[Int].
   */
  test("EXERCISE 3.19") {
    assert(numbers.filter(e => e % 2 != 0) == List(1.0, 3.0, 5.0))
  }

  /**
   * Write a function flatMap that works like map except that the function given will return a list instead of a single result, and that list should be inserted into the final resulting list.
   */
  test("EXERCISE 3.20") {
    //def flatMap[A,B](as: List[A])(f: A => List[B]): List[B]

    /*    def flatMap[A, B](as: List[A])(f: A => List[B]): List[B] = {
          List()
        }
        */
    val l = List(1, 2, 3)
    //l.flatMap()
    l.map(e => e)

    assert(numbers.filter(e => e % 2 != 0) == List(1.0, 3.0, 5.0))
  }

  /**
   * Use flatMap to implement filter.
   */
  test("EXERCISE 3.21") {

    //  val r = numbers.flatMap(e => List(e))(a => if (f(a)) List(a) else Nil)
    //println(r)


    //def filterViaFlatMap[A](l: List[A])(f: A => Boolean): List[A] = flatMap(l)(a => if (f(a)) List(a) else Nil)
    //assert(numbers.filter(e => e % 2 != 0) == List(1.0, 3.0, 5.0))
  }

  /**
   * Write a function that accepts two lists and constructs a new list by adding corresponding elements.
   * For example, List(1,2,3) and List(4,5,6) become List(5,7,9).
   */
  test("EXERCISE 3.22") {
    val l1 = List[Int](1, 2, 3)
    val l2 = List[Int](4, 5, 6)

    @tailrec
    def concatRec(i1: Iterator[Int], i2: Iterator[Int], res: List[Int]): List[Int] = {
      concatRec(i1, i2, if (i1.hasNext) res ::: List(i1.next() + i2.next()) else return res)
    }

    val r = concatRec(l1.iterator, l2.iterator, List())
    assert(r == List(5, 7, 9))
  }


}

