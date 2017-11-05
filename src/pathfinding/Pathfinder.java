package pathfinding;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * 07.10.2017
 *
 * @author SWirries
 */
public class Pathfinder {
    private Point start;
    private Point end;
    private ArrayList<ArrayList<Way>> allPaths = new ArrayList<>();
    private int wayCount = 0;

    public ArrayList<ArrayList<Way>> getAllPaths() {
        return allPaths;
    }

    private ArrayList<Way> path = new ArrayList<>();
    int trys = 10;

    public Pathfinder(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public void findPath(){


        /*
        new
         */

        Point current = start;
        ArrayList<Way> possibleDirections = getAllpossibleDirections(current, Way.LEFT);

        for(Way direction : possibleDirections){
            goTo(current, direction, new ArrayList<Way>());
        }

        System.out.println("Anzahl der Pfade: " + allPaths.size());
        for(ArrayList<Way> ways : allPaths){
            wayCount += ways.size() +1;
        }
        System.out.println("PathNodes:"+ wayCount);

    }

    protected void goTo(Point currentPoint, Way direction, ArrayList<Way> path){

        Point newPoint = goDirection(currentPoint, direction);
        path.add(direction);

        if(newPoint.getX() == end.getX() && newPoint.getY() == end.getY()){
            allPaths.add(path);
//            System.out.println(allPaths.size());
        }else if(newPoint.getX() <= end.getX()){
            ArrayList<Way> possibleDirections = getAllpossibleDirections(newPoint, direction);
            for(Way dir : possibleDirections){
                ArrayList<Way> clone = (ArrayList<Way>) path.clone();
                goTo(newPoint, dir, clone);
            }
            path = null;
        }

    }

    protected Point goDirection(Point current, Way direction){
        int y = current.y;
        int x = current.x;
        switch (direction){
            case TOP:
                y = y +1;
//                System.out.println("TOP");
                break;
            case RIGHT:
                x = x +1;
//                System.out.println("RIGHT");
                break;
            case BOTTOMRIGHT:
                x = x +1;
                y = y -1;
//                System.out.println("BOTTOMRIGHT");
                break;
            case TOPLEFT:
                x = x -1;
                y = y +1;
                break;
            case TOPRIGHT:
                x = x +1;
                y = y +1;
                break;
        }
        return new Point(x,y);

    }

    protected ArrayList<Way> getAllpossibleDirections(Point p, Way direction){

        ArrayList<Way> out = new ArrayList<>();

        for(Way way : Way.values()){
            if(checkWay(p, way)){
                if(!(direction == Way.BOTTOMRIGHT && way == Way.TOPLEFT) && !(direction == Way.TOPLEFT && way == Way.BOTTOMRIGHT))
                    out.add(way);
            }
        }
        return out;

    }

//    private Path checkWay(Point current, Way way){
//        Path possible = new Path(false,false);
//        switch (way){
//            case TOP:
//                possible = checkTopWay(current);
//                break;
//            case LEFT:
//                //
//                break;
//            case RIGHT:
//                possible = checkRightWay(current);
//                break;
//            case BOTTOM:
//                //possible = checkBottomWay(current);
//                break;
//            case TOPLEFT:
//                //possible = checkTopLeftWay(current);
//                break;
//            case TOPRIGHT:
//                //possible = checkTopRightWay(current);
//                break;
//            case BOTTOMLEFT:
//                //
//                break;
//            case BOTTOMRIGHT:
//                possible = checkBottomRightWay(current);
//                break;
//        }
//
//        return possible;
//    }

    protected boolean checkWay(Point current, Way way){
        boolean possible = false;
        switch (way){
            case TOP:
                possible = checkTopWay(current);
                break;
            case LEFT:
                //
                break;
            case RIGHT:
                possible = checkRightWay(current);
                break;
            case BOTTOM:
                //possible = checkBottomWay(current);
                break;
            case TOPLEFT:
                possible = checkTopLeftWay(current);
                break;
            case TOPRIGHT:
                possible = checkTopRightWay(current);
                break;
            case BOTTOMLEFT:
                //
                break;
            case BOTTOMRIGHT:
                possible = checkBottomRightWay(current);
                break;
        }

        return possible;
    }

    protected boolean checkTopWay(Point current){
        boolean possible = false;
        //Nach oben
        Point top = new Point(current.x,current.y+1);
        if(top.y <= end.x && top.x <= end.x)
            possible = true;

        if(current.x > current.y){
            possible = false;
        }

        if(current.y >= (-current.x) + end.x)
            possible = false;

        return possible;
    }

    protected boolean checkTopRightWay(Point current){
        boolean possible = false;
        //Nach oben rechts
        Point top = new Point(current.x+1,current.y+1);
        if(top.y <= end.x && top.x <= end.x)
            possible = true;

        if(current.x > current.y){
            possible = false;
        }
        if(current.x == current.y){
            possible = false;
        }

        if(current.y >= (-current.x) + end.x)
            possible = false;

        return possible;
    }

    protected boolean checkRightWay(Point current){
        boolean possible = false;
        //Nach oben rechts
        Point top = new Point(current.x+1,current.y);
        if(top.y <= end.x || top.x <= end.x)
            possible = true;

        if(current.x < current.y){
            possible = false;
        }

        if(current.y >= (-current.x) + end.x)
            possible = false;

        return possible;
    }

    protected boolean checkBottomRightWay(Point current){
        boolean possible = false;
        //Nach oben rechts
        Point top = new Point(current.x+1,current.y-1);
        if((top.y <= end.x && top.x <= end.x) && (top.y >= 0 && top.x >= 0))
            possible = true;


        return possible;
    }

    protected boolean checkTopLeftWay(Point current){
        boolean possible = false;
        //Nach oben rechts
        Point top = new Point(current.x-1,current.y+1);
        if((top.y <= end.x || top.x <= end.x) && (top.y >= 0 && top.x >= 0))
            possible = true;

        if(current.y >= (-current.x) + end.x)
            possible = false;

        return possible;
    }

    protected boolean checkBottomWay(Point current){
        return false;
    }

    protected boolean checkMeet(Point next){
        if(next.x == end.x && next.y == end.y)
            return true;

        return false;
    }

    /**
     * Prüft alle Richtungen, wenn Richtung möglich, dann in diese Richtungen gehen und Weg merken.
     * Vom neuen Punkt aus wieder alle Richtungen prüfen (rekursiv).
     * Wenn Richtung/Punkt auf dem Ende liegt, dann gelaufenen Weg merken.
     */
}
