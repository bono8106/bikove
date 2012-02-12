package stuff;

public class TryWithResources {
  public static void main(String[] args) throws Exception {
    try (OpenDoor door = new OpenDoor();OpenWindow window = new OpenWindow()  ){
               door.swing();
               window.crank();
    }
    catch(Exception e) {System.out.println("Is there a draft?"); e.printStackTrace(); }
     finally {System.out.println("I'm putting a sweater on, regardless.");}
  }
}

class OpenDoor implements AutoCloseable {
  public OpenDoor() { System.out.println("The door is open."); }
  public void swing() throws Exception {
    System.out.println("The door is becoming unhinged.");
    throw new Exception();
  }
  public void close() throws Exception {
    System.out.println("The door is closed.");
    throw new Exception("door exception");
  }
}

class OpenWindow implements AutoCloseable {
  public OpenWindow() { System.out.println("The window is open."); }
  public void crank() throws Exception {
    System.out.println("The window is overcranked!");
    throw new Exception();
  }
  public void close() throws Exception{
    System.out.println("The window is closed.");
  }
}