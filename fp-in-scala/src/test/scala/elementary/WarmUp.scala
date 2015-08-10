package elementary

import org.scalatest.FunSuite

import scala.collection.immutable.List

/**
 * @author zmax.
 */
class WarmUp extends FunSuite {

  val listNumbers = List(1, 2, 3, 4, 5, 6)
  val listNumbers2 = List(6, 5, 4, 3, 2, 1)
  val str = "HelloWorld"
  val strNums = "12345678"
  val listWords = List("one", "two", "three", "four", "five", "six")

  test("reverse String") {
    assert(str.reverse.equals("dlroWolleH"))
    assert(iterateReverse1(str).equals("dlroWolleH"))
    assert(iterateReverse2(str)(new String).equals("dlroWolleH"))
    assert(loopChangeTail(strNums).equals("87654321"))

    /**
     * "reverse String" -> Imperative
     */
    def iterateReverse1(init: String): String = {
      val it = init.reverseIterator
      val b = StringBuilder.newBuilder
      while (it.hasNext) {
        b.append(it.next())
      }
      b.toString()
    }

    /**
     * "reverse String" -> functional
     */
    def iterateReverse2(str: String)(res: String): String = str match {
      case _ if str.isEmpty => res
      case _ => iterateReverse2(str.init)(res ++ str.last.toString)
    }

    def iterateReverse3(str: String) {
      for (i <- 10 until 0) {
        println(i)
      }
    }

    def loopChangeTail(s: String): String = (for (i <- s.length until 0 by -1) yield s(i - 1)).mkString

  }

  test("change order in String 'n with n+1'") {
    assert(loopChange(strNums).equals("21436587"))
    //println(loopChangeTail(strNums))

    //println(changePosition(str)(""))

    def loopChange(s: String): String = {
      val r = for (i <- 0 until s.length by 2) yield s.substring(i, i + 2).reverse
      r.mkString
    }

    def changePosition(s: String)(revers: String): String = s match {
      case _ if s.isEmpty => revers
      case _ if s.size == 1 => revers + s
      case _ => changePosition(s.substring(0, s.length - 2))(revers + s.last + s.substring(s.length - 2, s.size - 1))
    }


  }

  test("function as param reverse") {

    //Scala
    def transform(s: String, f: String => String) = f(s)
    transform("hello", _.toUpperCase)


    transform("hello", s => s.toUpperCase)
    transform("hello", _.toUpperCase)

    assert(transform("hello", s => s.toUpperCase).equals("HELLO"))
    assert(transform("hello", _.toUpperCase).equals("HELLO"))

  }


  test("List reverse") {
    val reversed = reverse(Nil, listNumbers)
    println(reversed)
    assert(listNumbers.reverse.equals(List(6, 5, 4, 3, 2, 1)))
    assert(reversed.equals(List(6, 5, 4, 3, 2, 1)))
  }

  def reverse(r: List[Int], l: List[Int]): List[Int] = l match {
    case Nil => r
    case _ => reverse(r ::: List(l.last), l.init)
  }


  test("Half list") {
    val r = listNumbers.splitAt(listNumbers.length / 2)
    assert(r._1 == List(1, 2, 3))
    assert(r._2 == List(4, 5, 6))
  }

  test("Count letters in the words") {
    val r = listWords.map(_.length)
    assert(r.equals(List(3, 3, 5, 4, 4, 3)))
  }

  test("to list concatenation") {
    val r = listNumbers.zip(listNumbers2)
    val it1 = listNumbers.iterator
    val it2 = listNumbers2.iterator
    println(r)
  }

  ///////////////// Test scala feature /////////////////

  //Named and Default elementary.Arguments
  def addColorsWithDefaults(red: Int = 0, green: Int = 0, blue: Int = 0) = {
    (red, green, blue)
  }

  val myColor1 = addColorsWithDefaults(green = 0, red = 255, blue = 0)
  val myColor2 = addColorsWithDefaults(red = 255, blue = 10)
  val myColor3 = addColorsWithDefaults(green = 255)


}



