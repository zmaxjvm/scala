package elementary

import org.scalatest.FunSuite

import scala.collection.immutable.List

/**
 * @author zmax.
 *
 *         Lists are quite similar to arrays, but there are two important differences. First, lists are immutable.
 *         That is, elements of a list cannot be changed by assignment.
 *         Second, lists have a recursive structure (i.e., a linked list),1whereas arrays are flat
 *
 *         The elements of a list all have the same type. The type of a list that has elements of type T is written List[T].
 *
 *         The list type in Scala is covariant. This means that for each pair of
 *         types S and T, if S is a subtype of T, then List[S] is a subtype of List[T].
 *         For instance, List[String] is a subtype of List[Object]. This is natural
 *         because every list of strings can also be seen as a list of objects.
 *
 *         All lists are built from two fundamental building blocks, Nil and :: (pronounced “cons”). Nil represents the empty list.
 *
 *
 *         All operations on lists can be expressed in terms of the following three:
 *
 *         head returns the first element of a list
 *         tail returns a list consisting of all elements except the first
 *         isEmpty returns true if the list is empty
 *
 *         Concatenation, written ‘:::’. Unlike ::, ::: takes two lists as operands.
 *
 *         Lists are a real work horse in Scala, so you will benefit from knowing how to use them.
 *
 */
class ListExample extends FunSuite {

  val abcde = List[Char]('a', 'b', 'c', 'd', 'e')
  val words = List("the", "quick", "brown", "fox")
  val numbers = List(1, 2, 3, 4, 5)

  test("Basic operations with 'List' example") {

    /**
     * Accessing to the list elements
     */
    assert(abcde.head == 'a')
    assert(abcde.last == 'e')
    assert(abcde.init == List('a', 'b', 'c', 'd'))
    assert(abcde.tail == List('b', 'c', 'd', 'e'))

    /**
     * Element selection: apply and indices
     */
    println(abcde apply 2) //Char = c
    println(abcde(2)) //Char = c

    /**
     * Flattening a list of lists: flatten
     */
    println(List(List(1, 2), List(3), List(), List(4, 5)).flatten) //List[Int] = List(1, 2, 3, 4, 5)

    /**
     * Zipping lists: zip and unzip
     * The zip operation takes two lists and forms a list of pairs:
     */
    println(abcde zip List(1, 2, 3)) //zipped: List[(Char, Int)] = List((a,1), (b,2), (c,3))

    /**
     * Displaying lists: toString and mkString
     */
    println(abcde.toString) //String = List(a, b, c, d, e)
    println(abcde mkString "") //String = abcde
    println(abcde mkString("[", ",", "]")) //String = [a,b,c,d,e]

    /**
     * Mapping over lists: map, flatMap and foreach
     */
    println(List(1, 2, 3) map (_ + 1)) //List(2, 3, 4)
    println(words.map(_.length)) //List(3, 5, 5, 3)
    println(words map (_.toList.reverse.mkString)) //List(eht, kciuq, nworb, xof)

    /**
     * flatMap and map, "flatMap == 'map' and 'flatten'
     */
    println(words map (_.toList)) // List(List(t, h, e), List(q, u, i, c, k), List(b, r, o, w, n), List(f, o, x))
    println(words flatMap (_.toList)) // List(t, h, e, q, u, i, c, k, b, r, o, w,  n, f, o, x)

    /**
     * Filtering lists: filter, partition, find, takeWhile, dropWhile, and span
     */
    println(numbers filter (_ % 2 == 0)) // List(2, 4)
    println(words filter (_.length == 3)) // List(the, fox)

    /**
     * find
     * The find method is also similar to filter but it returns the first element satisfying a given predicate
     */
    println(List(1, 2, 3, 4, 5) find (_ % 2 == 0)) //Option[Int] = Some(2)

    /**
     * The takeWhile and dropWhile operators also take a predicate as their right
     * operand. The operation xs takeWhile p takes the longest prefix of list xs
     * such that every element in the prefix satisfies p.
     */
    println(List(1, 2, 3, -4, 5) takeWhile (_ > 0)) //List[Int] = List(1, 2, 3)
    println(words dropWhile (_ startsWith "t")) // List[java.lang.String] = List(quick, brown, fox)

    /**
     * span
     * span equals (xs takeWhile p, xs dropWhile p)
     * Like splitAt, span avoids traversing the list xs twice:
     */
    println(List(1, 2, 3, -4, 5) span (_ > 0)) //(List[Int], List[Int]) = (List(1, 2, 3),List(-4, 5))

    /**
     * forall
     * means: true if 1, 2, and 3 are less than 3, false otherwise.
     */
    println(List(1, 2, 3).forall(x => x < 4)) //true
    println(List(1, 2, 3).forall(x => x < 1)) //false

    /**
     * There is a similar method exists that returns true if there is at least one element x in the collection such that p(x) is true.
     */
    println(List(1, 2, 3).exists(x => x < 3)) //true

    /**
     * sortWith
     */
    println(List(1, 3, 4, 2, 6) sortWith (_ < _)) //List(1, 2, 3, 4, 6)

    /**
     * Creating a range of numbers: List.range
     */
    println(List.range(1, 8)) //List(1, 2, 3, 4, 5, 6, 7)
    println(List.range('a', 'z')) //List(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y)

    /**
     * Creating uniform lists: List.fill
     */
    println(List.fill(5)('a')) //List(a, a, a, a, a)
    println(List.fill(3)("hello")) //List(hello, hello, hello)
    println(List.fill(2, 3)('b')) //List(List(b, b, b), List(b, b, b))

    /**
     * Processing multiple lists together
     */
    println((List(10, 20), List(2, 2, 3)).zipped.map(_ * _)) //List(20, 40)
  }


  test("Functional combinators. Find out the names of all pairs of mothers and their children in that list") {

    val res0 = persons.filter(p => !p.isMale).flatMap(p => p.children.map(c => (p.name, c.name)))
    println("filter -> " + res0)

    val res1 = persons.withFilter(p => !p.isMale).flatMap(p => p.children.map(c => (p.name, c.name)))
    println("withFilter -> " + res1)

    val res2 = for (p <- persons; if !p.isMale; c <- p.children) yield (p.name, c.name)
    println("for -> " + res2)
  }

  /**
   * for {
   * p <- persons // a generator
   * n = p.name // a definition
   * if (n startsWith "To") // a filter
   * } yield n //accumulator
   */
  test("For expressions -> for ( seq ) yield expr") {
    //"pat <- expr" The expression expr typically returns a list
    println(for (p <- persons) yield p)
    println(for (p <- persons; n = p.name; if (n startsWith "L")) yield n)
    println(for (p <- persons; if p.name startsWith "L") yield p.name)
  }


  /**
   * The for notation is essentially equivalent to common operations of database query languages
   *
   * var sum = 0
   * for (xs <- xss; x <- xs) sum += x
   *
   * This loop is translated into two nested foreach applications:
   * var sum = 0
   * xss foreach (xs => xs foreach (x => sum += x))
   *
   */
  test("Querying with for expressions") {
    println(for (b <- books; a <- b.authors if a.contains("Gosling")) yield b)
  }

  /**
   * Every for expression can be expressed in terms of the three higher-order functions map, flatMap, and withFilter.
   *
   * for (x <expr1) yield expr2 == expr1.map(x => expr2)
   * for (x <expr1; y <expr2; seq) yield expr3
   * expr1.flatMap(x => for (y <expr2; seq) yield expr3)
   *
   *
   */
  /*  test("Translation of for expressions" {

    }*/
  val lara = Person("Lara", false)
  val bob = Person("Bob", true)
  val julie = Person("Julie", false, lara, bob)
  val persons = List(lara, bob, julie)
  val books: List[Book] =
    List(
      Book(
        "Structure and Interpretation of Computer Programs",
        "Abelson, Harold", "Sussman, Gerald J."
      ),
      Book(
        "Principles of Compiler Design",
        "Aho, Alfred", "Ullman, Jeffrey"
      ),
      Book(
        "Programming in Modula2",
        "Wirth, Niklaus"
      ),
      Book(
        "Elements of ML Programming",
        "Ullman, Jeffrey"
      ),
      Book(
        "The Java Language Specification",
        "Gosling, James", "Joy, Bill", "Steele, Guy", "Bracha, Gilad"
      )
    )

  /**
   * Nil represents the empty list. The infix operator, ::,
   * expresses list extension at the front. That is, x :: xs represents a list whose
   * first element is x,
   * @param x
   * @param xs
   * @return
   */
  def insert(x: Int, xs: List[Int]): List[Int] = xs match {
    case List() => List(x)
    case y :: ys => if (x <= y) x :: xs
    else y :: insert(x, ys)
  }

  /**
   * Auxiliary data
   * @param name
   * @param isMale
   * @param children
   */
  case class Person(name: String, isMale: Boolean, children: Person*)

  case class Book(title: String, authors: String*)


}

