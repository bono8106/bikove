package stuff

object SlotPlusMethod extends App {

  var slot = 1

  val x2 = if (slot == 1) "a" else "b"

  val x = 1 + 2

  val `hello world` = "hello world"

  println(`hello world`)

  println(x2)

  //def slot = 0
  //def slot_=(x: Int) {/* empty */ }

  class Parent {
    var slot = 1
  }

  class Child extends Parent {
  }

}