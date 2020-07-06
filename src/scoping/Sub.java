package scoping;

import static java.lang.Math.sqrt;

@SuppressWarnings("unused")
public class Sub extends Sup {
  public int x; // OK, two fields named x

  public void test() {
    sqrt(20);
  }
}
