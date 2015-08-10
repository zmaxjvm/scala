package elementary

import org.scalatest.FunSuite
import scala.collection.immutable.List

/**
 * @author zmax.
 */
class Arguments extends FunSuite {

  test("function as param reverse") {

    def addColorsWithDefaults(name: String, red: Int = 0, green: Int = 0) = {
      System.out.print(name + "red=" + red + "green=" + green)
    }

    addColorsWithDefaults("test");
    addColorsWithDefaults(name = "test");
    addColorsWithDefaults("test", 1);
    addColorsWithDefaults("test", 1, 2);
    addColorsWithDefaults("test", green = 1, red = 2);

    val l = List("d","d")
  }

}



