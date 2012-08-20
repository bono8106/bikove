package problems;

import java.util.Random;

public class Sandwiches {

  /**
   * @param args
   */
  public static void main(String[] args) {
    int[] items = new int[10];
    for (int i = 0; i < items.length; i++) {
      items[i] = i;
    }
    Random rand = new Random();
    for (int i = 0; i < items.length; i++) {
      int a = rand.nextInt(items.length);
      int b = rand.nextInt(items.length);
      int tmp = items[a];
      items[a] = items[b];
      items[b] = tmp;
    }

    print(items);
    resort(items);
    print(items);
  }

  private static void print(int[] items) {
    for (int i : items) {
      System.out.print(i);
      System.out.print(' ');
    }
    System.out.println();
  }

  public static void resort(int[] items) {
    int swaps = 0;
    for (int i = 0; i < items.length; i++) {
      int item = items[i];
      while (item != i) {
        int otherItem = items[item];
        items[item] = item;
        item = otherItem;
        swaps++;
      }
      if (items[i] != i) {
        items[i] = i;
      }
    }
    System.out.printf("Resorted with %d swaps.\n", swaps);
  }

}
