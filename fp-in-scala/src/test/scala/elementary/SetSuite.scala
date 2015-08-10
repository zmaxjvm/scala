package elementary

/**
 * @author zmax.
 */
import org.scalatest.FunSuite


class SetSuite extends FunSuite {

  //Scala
  class Person(val name: String, var age: Int) {

  }

  val p = new Person("testP", 11)
  p.name

  test("An empty Set should have size 0") {

    ////////DELETE

    def makeUpper(xs: List[String]) = xs map {
      _.toUpperCase
    }

    def makeWhatEverYouLike(xs: List[String], sideEffect: String => String) = {
      xs map sideEffect
    }

    println(makeUpper(List("abc", "xyz", "123")))

    ///DELETE

  }

  test("Invoking head on an empty Set should produce NoSuchElementException") {
    intercept[NoSuchElementException] {
      Set.empty.head
    }
  }

}


