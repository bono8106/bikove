package stuff

object VirtualTypes extends App {

  class Vehicle
  type Hashable = {
    def hashCode(): Int
  }

  class Collection {
    type E <: Object;
    def insert(elm: E) { /* empty */ }
  }
  class Set extends Collection { // E inherited
    override def insert(elm: E) { /* empty */ }
  }
  class VehicleSet extends Set {type E = Vehicle}
  class HashedSet extends Set {
    type E <: Hashable;
    override def insert(elm: E) { elm.hashCode() }
  }
  class IntegerSet extends HashedSet { type E = Integer; }


  abstract class Ordered {
    type S <: Object;
    def leq(o: S): Boolean;
  }
  class Point(val x: Int, val y: Int) extends Ordered {
    type S = Point;
    def leq(o: S): Boolean = { return this.x - o.x < 0 }
  }
  abstract class OrderedSet extends Set {
    type E <: Ordered { type S = E };
    def sort() { /* compare(a,b) */ }
    def compare(a: E, b: E): Boolean = a.leq(b);
  }
  class PointSet extends OrderedSet {
    type E = Point;
    //def compare(a: E, b: E): Boolean = {
    //  return a.leq(b);
    //}
  }

}