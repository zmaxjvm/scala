package elementary

import org.scalatest.FunSuite

/**
 * @author zmax.
 */
class FunctionalCombinators extends FunSuite {

  val abcde = List('a', 'b', 'c', 'd', 'e')
  val words = List("the", "quick", "brown", "fox")

  test("Basic operations with 'fold' example") {

    /**
     * Reduce
     */
    println(words.reduce(concat))

    /**
     * The fold method for a List takes two arguments; the start value and a function. This function also takes two arguments; the accumulated value and the current item in
     */
    println(List(1, 2, 3).fold(0) { (z, i) => z + i }) //6. equals to summ
    println(applyTransformations(words, concat)) //the=3quick=10brown=18fox=24

  }

  /**
   * Map works by applying a function to each element in the list.
   */
  test("map") {
    val l = List(1, 2, 3, 4)
    assert(l.map(e => e * 2) == List(2, 4, 6, 8))

    def f(x: Int) = if (x % 2 == 0) -1 else x
    assert(l.map(x => f(x)) == List(1, -1, 3, -1))
  }

  /**
   * FlatMap - works applying a function that returns a sequence for each element in the list,
   * and flattening the results into the original list.
   */
  test("flatMap") {
    val l = List(List(1, 2, 3), List(4, 5, 6))

    assert(l.flatMap(e => e) == List(1, 2, 3, 4, 5, 6))
    l.flatMap(e => e).map(x => x)
    l.flatMap(e => List(e.head))
    //assert(l.flatMap(e => e.head) == List(1,2,3,4,5,6))
  }

  test("Basic operations with filter") {

    val res0 = List(1, 2, 3, 4, 5).filter(x => x % 2 == 0)

    val init = List("Bd", "C", "s", "f");
    //Scala
    val res = init
      .filter(s => s.length < 5)
      .filter(s => s.exists(_.isUpper))

    res.length

  }

  test("zip") {
    val r0 = List(1, 2, 3).zip(List("a", "b", "c")).zip(List(1, 2, 3, 4, 5))
    r0.toString()

    val r1 = List(1, 2).zip(List("a", "b", "c"))
    r1.toString()
  }

  /**
   * Folding operation
   * For example we have List(1,2,4). foldLeft(“X”)((b,a) => b + a). For the first item, 1, the function we define would add string “X”
   * to Int 1, returning string “X1″.
   * For the second list item, 2, the function would add string “X1″ to Int 2, returning “X12″.
   * And for the final list item, 3, the function would add “X12″ to 3 and return “X123″.
   *
   */
  def concat(a: String, b: String) = a + b + "=" + (a + b).length

  def applyTransformations(initial: List[String], f: (String, String) => String) = initial.foldLeft("") { (cur, transform) => f(cur, transform) }

}

