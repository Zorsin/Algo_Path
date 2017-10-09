package pathfinding;

import java.awt.*;

/**
 * 07.10.2017
 *
 * @author SWirries
 */
public class Main {
    public static void main(String[] args) {

        int n = 10;
        Point start = new Point(0,0);
        Point end = new Point (n,0);

        Pathfinder pathfinder = new Pathfinder(start,end);
        pathfinder.findPath();
    }
}
