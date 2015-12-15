package stuff

object While extends App {
  
  def doWhile(cond: => Boolean)(f: => Unit): Unit = {
    while (cond) {
      f
    }
  }
  
  def test() {
    var i = 0
    doWhile (i < 10) { // equivalent to while
      println(i);
      i += 1 // not possible in Java
      if (i == 5) return; // not possible in Java
    }
  
  }
  
  test();
  
}
