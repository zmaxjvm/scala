package elementary

import org.scalatest.FunSuite

import scala.collection.immutable.{HashMap, TreeSet}
import scala.collection.mutable.ListBuffer
import scala.collection.{SortedSet, mutable}

/**
 * @author zmax.
 *
 *         API provided by Traversable, but their methods, all return their own class rather than the root class Traversable.
 *         Traversable
 *         * Iterable
 *         ** Seq
 *         **** IndexedSeq
 *         ******* Vector
 *         ******* ResizableArray
 *         ******* GenericArray
 *         **** LinearSeq
 *         ******* MutableList
 *         ******* List
 *         ******* Stream
 *         **** Buffer
 *         ******* ListBuffer
 *         ******* ArrayBuffer
 *         ** Set
 *         ******* SortedSet
 *         ********** TreeSet
 *         ******* HashSet (mutable)
 *         ******* LinkedHashSet
 *         ******* HashSet (immutable)
 *         ******* BitSet
 *         ******* EmptySet, Set1, Set2, Set3, Set4
 *         ** Map
 *         ******* SortedMap
 *         ********** TreeMap
 *         ******* HashMap (mutable)
 *         ******* LinkedHashMap (mutable)
 *         ******* HashMap (immutable)
 *         ******* EmptyMap, Map1, Map2, Map3, Map4
 *
 *         ******* Main concept *******
 *
 *         Sets - Sets are Iterables that contain no duplicate elements
 *
 *         Bit sets - are sets of non-negative integer elements that are implemented in one or more words of packed bits.
 *
 *         Sorted sets - the default representation of a SortedSet is an ordered binary tree
 *
 *         Bit sets - are sets of non-negative integer elements that are implemented in one or more words of packed bits
 *
 *         Maps - are Iterables of pairs of keys and values (also named mappings or associations)
 *
 */
class CollectionExample extends FunSuite {

  /**
   * Mutable and immutable collections
   * A collection in package scala.collection.immutable is guaranteed to be immutable for everyone
   */
  test("Mutable and immutable collections example") {
    Traversable(1, 2, 3)
    Iterable("x", "y", "z")
    Map("x" -> 24, "y" -> 25, "z" -> 26)
    Set("Color.Red", "Color.Green", "Color.Blue")
    SortedSet("hello", "world")
    mutable.Buffer("x", "y", "z")
    IndexedSeq(1.0, 2.0)
    mutable.LinearSeq('a', 'b', 'c')

    //The same principle also applies for specific collection implementations:
    HashMap("x" -> 24, "y" -> 25, "z" -> 26)
  }

  val words = List("A", "B", "C")

  val m = Map(1 -> "one")
  /**
   * Class List provides fast access to the head of the list, but not the end.
   * A ListBuffer is a mutable object (contained in package scala.collection.mutable), which can help you build lists more efficiently when you need to append. ListBuffer provides constant time append
   * and prepend operations.
   */
  test("Basic operations with 'List buffers' example") {
    val buf = new ListBuffer[Int]

    assert((buf += 1) == ListBuffer(1))
    assert((buf += 2) == ListBuffer(1, 2))
  }

  /**
   * Buffers
   * An important sub-category of mutable sequences is buffers. Buffers allow
   * not only updates of existing elements but also element insertions, element
   * removals, and efficient additions of new elements at the end of the buffer.
   */


  /**
   * Sets
   * Sets are Iterables that contain NO duplicate elements.
   * The current default implementation of a mutable set uses a hash table to store the set’s elements
   *
   */
  test("Basic operations with 'Set' example") {
    val fruit = Set("apple", "orange", "peach", "banana")
    assert(fruit("peach"))
    assert(!fruit("newitem"))

    //mutable set
    var s = Set(1, 2, 3)
    s += 4
    s -= 2
    assert(s.equals(Set(1, 3, 4)))
  }

  /**
   * SortedSet
   * The default representation of a SortedSet is an ordered binary tree
   *
   */
  test("Basic operations with 'SortedSet' example") {
    val s = TreeSet("B", "C", "A")
    println(s)
    assert(s.equals(TreeSet("A", "B", "C")))
  }

  /**
   * Vectors
   * Lists are very efficient when the algorithm processing them is careful to only process their heads.
   * Accessing, adding, and removing the head of a list takes only constant time, whereas accessing or modifying elements
   * later in the list takes time linear in the depth into the list.
   *
   * !!! * Access to any elements of a vector take only “effectively constant time,”
   * Vectors are built and modified just like any other sequence:
   *
   * Vectors are immutable, so you cannot change an element of a vector in place.
   * However, with the updated method you can create a new vector that DIFFERS from a given vector only in a single element:
   *
   *
   *
   */
  test("Basic operations with 'Vector' example") {
    val vec = Vector(1, 2, 3)
    val updated = vec.updated(0, 4)

    assert(vec.equals(Vector(1, 2, 3)))
    assert(updated.equals(Vector(4, 2, 3)))

  }

  /**
   * Immutable queues
   *
   */
  test("Basic operations with 'Immutable queues' example") {
    val empty = scala.collection.immutable.Queue[Int]()
    val has1 = empty.enqueue(1)
    val has123 = has1.enqueue(List(2, 3))
    val (element, has23) = has123.dequeue

    //Ranges


    //assert(updated.equals(Vector(4, 2, 3)))

  }

  /**
   * Arrays
   * Arrays are a special kind of collection in Scala. One the one hand, Scala arrays
   * correspond one-to-one to Java arrays. That is, a Scala array Array[Int]
   * is represented as a Java int[], an Array[Double] is represented as a Java
   * double[] and an Array[String] is represented as a Java String[]. But
   * at the same time, Scala arrays offer much more their Java analogues. First,
   * Scala arrays can be generic. That is, you can have an Array[T], where T is
   * a type parameter or abstract type. Second, Scala arrays are compatible with
   * Scala sequences—you can pass an Array[T] where a Seq[T] is required.
   * Finally, Scala arrays also support all sequence operations. Here’s an example of this in action:
   *
   * Scala compiler somewhat “magically” wrapped and unwrapped arrays to and from Seq objects, when required, in a process called boxing and unboxing.
   *
   */
  test("Basic operations with 'Arrays' example") {

  }


  /**
   * Iterator
   */
  test("Basic operations with 'Iterator' example") {

  }

  /**
   * Buffered iterator
   * Class BufferedIterator is a subclass of Iterator, which provides one extra method, head.
   * Calling head on a buffered iterator will return its first element but will not advance the iterator.
   * Using a buffered iterator, skipping empty words can be written as follows.
   *
   */
  test("Basic operations with 'Buffered iterators' example") {

    val it = Iterator[String]("dd1", "dd2")
    skipEmptyWords(it.buffered)

    def skipEmptyWords(it: BufferedIterator[String]) =
      while (it.head.isEmpty) {
        println(it.next())

      }
  }

  val a1 = Array(1, 2, 3)
}

