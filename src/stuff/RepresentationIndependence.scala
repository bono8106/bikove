package stuff

/**
 * An experiment in representation-independent code.
 *
 * See the Dataless Programming paper from 1967:
 * http://www.rand.org/pubs/research_memoranda/2007/RM5290.pdf
 */
object RepresentationIndependence extends App {

  import scala.collection._

  type Puttable = {
    def update(x: Int, y: Int): Unit
    def apply(x: Int): Int
  }

  val x: Puttable = Array.ofDim[Int](10)
  val y: Puttable = mutable.ArraySeq[Int](10).padTo(10, 0)
  val z: Puttable = mutable.LinkedList[Int](10).padTo(10, 0)
  val m: Puttable = mutable.Map[Int, Int]()

  x(0) = 5

  y(1) = 7

  z(2) = 12

  m(5) = 8

  val n: Int = m(5)

  printf("x = %s%ny = %s%nz = %s%nm = %s%n", x, y, z, m)

}