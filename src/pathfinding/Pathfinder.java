package pathfinding;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.awt.*;

/**
 * 07.10.2017
 *
 * @author SWirries
 */
public class Pathfinder {
    Point start;
    Point end;
    Path[] paths;
    int trys = 10;

    public Pathfinder(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public void findPath(){
        System.out.println("END:"+end);
        Point current = start;
        int i = 0;
        do{
            /**
             * Wege zur Auswahl
             */
            //TOP
            Path top = checkWay(current,Way.TOP);
            //RIGHT
            Path right = checkWay(current,Way.RIGHT);
            //BOTTOMRIGHT
            Path bottomright = checkWay(current,Way.BOTTOMRIGHT);
            /**
             * Trifft ein weg?
             */

            Way takeWay = Way.TOP;
            if(top.isBest()){
                takeWay = Way.TOP;

            }else if(right.isBest()){
                takeWay = Way.RIGHT;

            }else if(bottomright.isBest()){
                takeWay = Way.BOTTOMRIGHT;

            }else if(top.isPossible()){
                takeWay = Way.TOP;
            }else if(right.isPossible()){
                takeWay = Way.RIGHT;
            }else if(bottomright.isPossible()){
                takeWay = Way.BOTTOMRIGHT;
            }

            switch (takeWay){
                case TOP:
                    current.y = current.y +1;
                    System.out.println("TOP");
                    break;
                case RIGHT:
                    current.x = current.x +1;
                    System.out.println("RIGHT");
                    break;
                case BOTTOMRIGHT:
                    current.x = current.x +1;
                    current.y = current.y -1;
                    System.out.println("BOTTOMRIGHT");
                    break;
            }


            System.out.println("Try:"+i+" Point:"+current.toString().substring(14));
            i++;

            if(current.x == end.x && current.y >= end.y/2) break;
        }while ((current.x != end.x || current.y != end.y) && i <= trys);
        System.out.println("END LOOP:"+current.toString().substring(14));
//        }while ((false) && i <= trys); || i <= trys

    }

    private Path checkWay(Point current, Way way){
        Path possible = new Path(false,false);
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
                possible = checkBottomWay(current);
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

    private Path checkTopWay(Point current){
        boolean possible = false;
        //Nach oben
        Point top = new Point(current.x,current.y+1);
        if(top.y <= end.x)
            possible = true;

        if(current.x > current.y){
            possible = false;
        }

        if(current.y >= (-current.x) + end.x)
            possible = false;

        return new Path(possible,checkMeet(top));
    }

    private Path checkTopRightWay(Point current){
        boolean possible = false;
        //Nach oben rechts
        Point top = new Point(current.x+1,current.y+1);
        if(top.y <= end.x && top.x <= end.x)
            possible = true;

        if(current.x < current.y){
            possible = false;
        }
        return new Path(possible,checkMeet(top));
    }

    private Path checkRightWay(Point current){
        boolean possible = false;
        //Nach oben rechts
        Point top = new Point(current.x+1,current.y);
        if(top.x <= end.x)
            possible = true;

        if(current.x < current.y){
            possible = false;
        }

        if(current.y >= (-current.x) + end.x)
            possible = false;

        return new Path(possible,checkMeet(top));
    }

    private Path checkBottomRightWay(Point current){
        boolean possible = false;
        //Nach oben rechts
        Point top = new Point(current.x+1,current.y-1);
        if(top.y <= end.x && top.x <= end.x && top.y >= 0 && top.x >= 0)
            possible = true;

        return new Path(possible,checkMeet(top));
    }

    private Path checkTopLeftWay(Point current){
        boolean possible = false;
        //Nach oben rechts
        Point top = new Point(current.x-1,current.y+1);
        if(top.y <= end.x && top.x <= end.x && top.y >= 0 && top.x >= 0)
            possible = true;

        return new Path(possible,checkMeet(top));
    }

    private Path checkBottomWay(Point current){
        return new Path(false,false);
    }

    private boolean checkMeet(Point next){
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
