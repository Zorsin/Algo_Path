package pathfinding;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author MHeiss SWirries
 */
public class PathNodes {

  private static Pathfinder pathfinder;
  private static int n;

  public static void main(String[] args) {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.print("Bitte geben sie ein gewünschtes n ein: ");
    String input = "";
    try {
      input = br.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println("n=" + input);
    try {
      n = Integer.parseInt(input);
      Point start = new Point(0,0);
      Point end = new Point (n,0);

      pathfinder = new Pathfinder(start,end);
      pathfinder.findPath();
    } catch (Exception e) {
//            e.printStackTrace();
      System.out.println("dieses n ist keine Zahl.");
    }
  }

}
