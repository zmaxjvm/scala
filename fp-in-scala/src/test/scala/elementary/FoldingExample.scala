package elementary

import org.scalatest.FunSuite

/**
 * @author zmax.
 *
 * Folding operation example
 * In general, all 6 fold functions apply a binary operator to each element of a collection. The result of each step is passed on to the next step.
 *
 * For example we have List(1,2,4). foldLeft(“X”)((b,a) => b + a). For the first item, 1, the function we define would add string “X”
 * to Int 1, returning string “X1″.
 * For the second list item, 2, the function would add string “X1″ to Int 2, returning “X12″.
 * And for the final list item, 3, the function would add “X12″ to 3 and return “X123″.
 *
 */
class FoldingExample extends FunSuite {

  val abc = List('a', 'b', 'c')

  test("folding demo") {
    println("foldLeft")
    abc.foldLeft('x')((init, curEl) => {
      println("init ->" + init)
      println("current element ->" + curEl)
      curEl
    }
    )

    println("foldRight")
    abc.foldRight('x')((curEl, init) => {
      println("init ->" + init)
      println("current element ->" + curEl)
      curEl
    }
    )
  }


  test("basic") {
    val l = List(1, 2, 3)
    val res = l.foldLeft(0)((r, c) => r + c)
    res.toString

  }

  test("Map and foldLeft") {
    val r = Map(1 -> "b", 2 -> "c").foldLeft("")((accumulator, kv) => accumulator + kv._2)
    assert(r == "bc")
  }

  val words = List("A", "B", "C")

  test("Basic operations with 'fold' example") {

    /**
     * 'reduceLeft' and 'reduceRight' cumulate a single result.
     */
    println(words.reduceLeft(concat)) //AB=2C=5

    /**
     * 'foldLeft' and 'foldRight' cumulate a single result using a start value.
     */
    println(words.foldLeft("init") { (cur, next) => concat(cur, next) }) //initA=5B=8C=11
    println(applyFoldTransform(words, concat)) //A=1B=4C=7

    /**
     * 'scanLeft' and 'scanRight' cumulate a collection of intermediate cumulative results using a start value.
     */
    println(words.scanLeft("")(concat)) //List(, A=1, A=1B=4, A=1B=4C=7)

  }


  /**
   * Folding operation
   */
  def concat(a: String, b: String) = a + b + "=" + (a + b).length

  def applyFoldTransform(initial: List[String], f: (String, String) => String) = initial.foldLeft("") { (cur, transform) => f(cur, transform) }

  /**
   * Imperative analog 'foldLeft'
   * @param initial
   * @param f
   * @return
   */
  //  def applyTransformations(initial: List[String], f: (String, String) => String) = {
  //    var cur = ""
  //    for (w <- initial) {
  //      cur = f(cur, w)
  //    }
  //    cur
  //  }

  /**
   * 'foldLeft' implementation
   * @see http://alvinalexander.com/java/jwarehouse/scala-2.11/library/scala/collection/TraversableOnce.scala.shtml
   */
  //override /*TraversableLike*/
  //def foldLeft[B](z: B)(f: (B, A) => B): B = {
  //  var acc = z
  //  var these = this
  //  while (!these.isEmpty) {
  //    acc = f(acc, these.head)
  //    these = these.tail
  //  }
  //  acc
  // }

}

