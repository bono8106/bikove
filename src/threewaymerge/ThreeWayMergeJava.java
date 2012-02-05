package threewaymerge;


public class ThreeWayMergeJava {

  public int getArrayLength(int[] x) {
    return x.length;
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    int[] input1 = {1, 3, 5};
    int[] input2 = {2, 4};
    int[] input3 = {0, 6, 7, 10};

    int[] res = sortn(input1, input2, new int[] {}, input3, input2, input3);
    for (int i : res) {
      System.out.println(i);
    }
  }

  private static int[] sortn(int[] ... input) {
    int len = 0;
    for (int[] xx : input) {
      len += xx.length;
    }
    int[] result = new int[len];

    int[] ii = new int[input.length];
    for (int i = 0; i < result.length; i++) {
      int min = Integer.MAX_VALUE;
      int ni = -1;
      for (int n = 0; n < ii.length; n++) {
        if (ii[n] < input[n].length && input[n][ii[n]] < min) {
          min = input[n][ii[n]];
          ni = n;
        }
      }
      assert(ni != -1);
      result[i] = min;
      ii[ni]++;
    }
    return result;
  }

}
