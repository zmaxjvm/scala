package elementary

import org.scalatest.FunSuite

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
class TraitExample extends FunSuite {

  test("Basic operations with 'List' example") {

  }

  test("linearization") {
    class BaseClass {
      def print {
        println("BaseClass")
      }
    }

    trait Trait1 extends BaseClass {
      override def print() {
        println("Trait1")
      }

      def print2() = println("...")

      def print3()
    }

    trait Trait2 extends BaseClass {
      override def print() {
        println("Trait2")

      }
    }

  }

}

