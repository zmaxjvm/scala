package elementary

import org.scalatest.FunSuite

/**
 * @author zmax.
 *
 */
class FunctionsExample extends FunSuite {

  val abcde = List('a', 'b', 'c', 'd', 'e')
  val words = List("the", "quick", "brown", "fox")
  val numbers = List(1, 2, 3, 4, 5)


  /**
   * In computer science, a programming language is said to have first-class functions if it treats functions as first-class citizens.
   * Specifically, this means the language supports passing functions as arguments to other functions, returning them as the values from other functions,
   * and assigning them to variables or storing them in data structures
   */
  test("First-class function") {
    //Store function to the variable
    val add = (x: Int, y: Int) => x + y

    //Passes function to the function
    def exec(a: Int, b: Int, f: (Int, Int) => Int) = f(a, b)

    //Execution
    exec(2, 2, add)
    exec(2, 2, (x: Int, y: Int) => x + y)
  }



  test("Higth-Order Functions examples") {

    /**
     * set function as parameter for the function
     */
    println(applyFunction(sqr, 2)) //4
    println(applyFunction(cube, 2)) //8

    /**
     * return function as result another function
     */
    println(returnFunction(2).apply(2)) //4
    println(returnFunction(3).apply(3)) //27
    println(returnFunction(2)(2)) //4
    println(returnFunction(3)(3)) //27

  }

  /**
   * get function as parameter
   * @param f
   * @param a
   * @return function result
   */
  def applyFunction(f: Int => Int, a: Int) = f(a)


  /**
   * * get function as parameter and return function
   * @return function
   */
  def returnFunction(a: Int): (Int) => Int = {
    if (a % 2 == 0) (a) => a * a else cube
  }


  def sqr(a: Int) = a * a

  def cube(a: Int) = a * a * a


  /**
   * Hight order function by parameter
   */
  test("Function as parameter") {
    println("sum = " + calc(2, 2, sum))
    //Passing an anonymous function as a function argument
    println("sum1 = " + calc(2, 2, (a, b) => a + b))
    println(oncePerSecond(() => println("time flies like an arrow ...")))

  }


  val msg1 = "Hello again, world!"
  val msg2: java.lang.String = "Hello again, world!"

  def sum(a: Int, b: Int) = a + b


  def calc(a: Int, b: Int, f: (Int, Int) => Int): Int = {
    return f(a, b)
  }

  def calc1(a: Int, b: Int, f: (Int, Int) => Int) = f(a, b)

  // takes a function that receives no args and doesn't return anything
  def oncePerSecond(callback: () => Unit) = callback();
}

