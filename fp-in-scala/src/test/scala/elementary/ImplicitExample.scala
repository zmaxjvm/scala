package elementary

import org.scalatest.FunSuite

/**
 * @author zmax.
 */
class ImplicitExample extends FunSuite {

  class PreferredPrompt(val preference: String)

  class PreferredDrink(val preference: String)

  class Greeter {
    def greet(name: String)(implicit prompt: PreferredPrompt, drink: PreferredDrink) {
      println("Welcome, " + name + ". The system is ready.")
      print("But while you work, ")
      println("why not enjoy a cup of " + drink.preference + "?")
      println(prompt.preference)
      println(drink.preference)
    }
  }

  /**
   * Implicit class example
   * Use this approach for add new function for the some class
   */
  object Helpers {

    implicit class GreeterReach(val s: Greeter) extends AnyRef {
      def addImplicit = println("implicited function ... ")
    }

  }

  object JoesPrefs {
    implicit val prompt = new PreferredPrompt("yes")
    implicit val drink = new PreferredDrink("tea")
  }

  test("implicit with currying example") {
    import Helpers._
    import JoesPrefs._
    val gr = new Greeter()
    gr.greet("Joe")(prompt, drink)

    //And because all the rules for implicit parameters are now met,
    //you can alternatively let the Scala compiler supply prompt and drink for you by leaving off the last parameter list:
    gr.greet("Joe")
    gr.addImplicit
  }

  test("implicit with currying 2 example") {
    class PreferredDrink(val preference: String)
    implicit val pref = new PreferredDrink("kvas")

    def enjoy(name: String)(implicit drink: PreferredDrink) {
      print("Welcome, " + name)
      print(". Enjoy a ")
      print(drink.preference)
      println("!")
    }
    enjoy("reader")
  }

  test("implicit class example") {
    import Helpers._
    val gr = new Greeter()
    gr.addImplicit
  }


  test("implicit string example") {

    object String2 {

      implicit class StringR(val s: String) {
        def addQuotes() = "''" + s + "''"
      }

    }

    //cannot resolve methods with same names.
    /*
        object String3 {
          implicit class StringR(val s: String) {
            def addQuotes() = "''" + s  +"''"
          }
        }*/

    import String2._
    //import String3._
    val r = "Hello".addQuotes()
    println(r)
  }

}

