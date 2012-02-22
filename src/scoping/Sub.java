package scoping;

import static java.lang.Math.sqrt;

@SuppressWarnings("unused")
public class Sub extends Sup {

  public void test() {
    sqrt(20);
  }

}
