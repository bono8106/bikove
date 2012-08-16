package stuff;

public class Webalo {

  public static void main(String[] args) {
    long currentTimeValue = 1290126162373L;
    //long currentTimeValue = System.currentTimeMillis();
    System.out.println("Current Time: " + currentTimeValue);
    System.out.println("Hash Value: " + hash(currentTimeValue));
    System.out.println("Hash Value (1345158833072 = 10 ): " + hash(1345158833072L));
    System.out.println("Hash Value (1345158861342 = -13): " + hash(1345158861342L));
    System.out.println("Hash Value (1345158884148 = 18 ): " + hash(1345158884148L));
    System.out.println("Hash Value (1345158918915 = -12): " + hash(1345158918915L));
    System.out.println("Hash Value (1290126162373 = -19): " + hash(1290126162373L));
    System.out.println("Hash Value (1345158961255 = 0  ): " + hash(1345158961255L));
  }

  private static int hash(long currentTimeValue) {
    // There is technically no need to trim currentTimeVlaue to 16 bits, but oh-well.
    final int stime = (int) (currentTimeValue & 0xFFFF); // 16-bit-time
    // Thought I would have to copy stime to the left to allow getting node-bits in one shot..
    // But since starting bit in stime is always even (node-ID * 2), there is no need to do that.
    return treeHash(0, 0, stime/* | (stime << 16)*/);
  }

  /**
   *
   * @param i the node-ID
   * @param depth the depth in the tree
   * @return the value of the expression tree
   */
  private static int treeHash(int i, int depth, int stime) {
    // Get the node-bits together (since always starting at even location, bits are always present together).
    int nodeBits = (stime >> ((i * 2) & 0xF)) & 3;
    if (depth == 4) { // depth 4 has (2^(4 + 1) - 1) = 31 nodes
      return (nodeBits << 30) >> 30; // expand 2-bit two's complement number
    } else {
      // Internal
      int x = treeHash(((2 * i) + 1), depth + 1, stime);
      int y = treeHash((2 * (i + 1)), depth + 1, stime);

      switch (nodeBits) {
      case 0: // x f y
        return x + (2 * y);
      case 1: // x g y
        return (2 * x) + y;
      case 2: // h x y
        return (x + y);
      case 3:
        return (x * y);
      default:
        throw new IllegalStateException("Unexpected node-bits = " + nodeBits);
      }
    }
  }

}
