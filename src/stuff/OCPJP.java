package stuff;

public class OCPJP {

  /**
   * From http://www.theserverside.com/tutorial/OCPJP-and-OCAJP-Mock-Exam-Question-for-the-Java-7-Exam
   * @param args
   */
  public static void main(String[] args) {
    int x = 2;
    x += x++ * x++ * x++;
    System.out.println(x);
  }

}
