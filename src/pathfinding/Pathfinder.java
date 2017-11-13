package pathfinding;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * 07.10.2017
 *
 * @author SWirries MHeiss
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

    /**
     * Startet die Pfadsuche
     */
    public void findPath(){
        Point current = start;//Aktueller Startpunkt
        //Rueckgabe aller moeglöichen Wege
        ArrayList<Way> possibleDirections = getAllpossibleDirections(current, Way.LEFT);
        //Fuer jede moegliche Richtung
        for(Way direction : possibleDirections){
            goTo(current, direction, new ArrayList<Way>());
        }
        //Ausgabe
        System.out.println("Anzahl der Pfade: " + allPaths.size());
        for(ArrayList<Way> ways : allPaths){
            wayCount += ways.size() +1;
        }
        System.out.println("Anzahl der Nodes:"+ wayCount);

    }

    /**
     * Geht zu von dem Punkt in die Richtung fuegt den Wege dem Pfad hinzu
     * @param currentPoint Aktueller Punkt
     * @param direction zugehende Richtung
     * @param path Liste aller Wege
     */
    protected void goTo(Point currentPoint, Way direction, ArrayList<Way> path){

        Point newPoint = goDirection(currentPoint, direction);//verschiebt den Punkt in die Richtung
        path.add(direction);//fuegt Richtung hinzu
        //Wenn Ziel erreicht wurde
        if(newPoint.getX() == end.getX() && newPoint.getY() == end.getY()){
            //Pfad der Loesung hinzufuegen
            allPaths.add(path);
//            System.out.println(allPaths.size());
        }else if(newPoint.getX() <= end.getX()){//Wenn Ende noch nicht ueberschritten wurde
            //Gibt alle moeglichen Richtungen aus
            ArrayList<Way> possibleDirections = getAllpossibleDirections(newPoint, direction);
            //Fuer jede Richtung
            for(Way dir : possibleDirections){
                //cloned den aktuellen Punkt
                ArrayList<Way> clone = (ArrayList<Way>) path.clone();
                //geht von dem aktuellen Punkt in die Richtung
                goTo(newPoint, dir, clone);
            }
            path = null;
        }

    }

    /**
     * Verschiebt den Punkt in die Richtung
     * @param current aktueller Punkt
     * @param direction zugehende Richtung
     * @return Punkt in der Richtung
     */
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

    /**
     * Gibt alle Richtungen zurueck, die von dem Punkt moeglich sind
     * @param p aktueller Punkt
     * @param direction letzte Richtung
     * @return alle verfuegbaren Richtungen
     */
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

    /**
     * Prueft ob der Weg moeglich ist
     * @param current aktueller Punkt
     * @param way zugehender Weg
     * @return wenn Weg moeglich true
     */
    protected boolean checkWay(Point current, Way way){
        boolean possible = false;
        switch (way){
            case TOP:
                possible = checkTopWay(current);
                break;
            case LEFT:
                //nicht erlaubt
                break;
            case RIGHT:
                possible = checkRightWay(current);
                break;
            case BOTTOM:
                //possible = checkBottomWay(current);
                //nicht erlaubt
                break;
            case TOPLEFT:
                possible = checkTopLeftWay(current);
                break;
            case TOPRIGHT:
                possible = checkTopRightWay(current);
                break;
            case BOTTOMLEFT:
                //nicht erlaubt
                break;
            case BOTTOMRIGHT:
                possible = checkBottomRightWay(current);
                break;
        }

        return possible;
    }

    /**
     * Prüft den Weg nach Oben
     * @param current aktueller Punkt
     * @return wenn moeglich true
     */
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

    /**
     * Prueft den Weg nach Oben-Rechts
     * @param current aktueller Punkt
     * @return wenn moeglich true
     */
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

    /**
     * Prueft den Weg nach Rechts
     * @param current aktueller Punkt
     * @return wenn moeglich true
     */
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

    /**
     * Prueft den Weg nach Unten-Rechts
     * @param current akuteller Punkt
     * @return wenn moeglich true
     */
    protected boolean checkBottomRightWay(Point current){
        boolean possible = false;
        //Nach oben rechts
        Point top = new Point(current.x+1,current.y-1);
        if((top.y <= end.x && top.x <= end.x) && (top.y >= 0 && top.x >= 0))
            possible = true;

        return possible;
    }

    /**
     * Prueft den Weg nach Oben-Links
     * @param current aktuller Punkt
     * @return wenn moeglich true
     */
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

    /**
     * Prueft den Weg nach Unten
     * @param current aktueller Punkt
     * @return wenn moeglich true
     * Ist aber nicht erlaubt daher immer false
     */
    protected boolean checkBottomWay(Point current){
        return false;
    }

    /**
     * Prueft ob der Punkt das Ende ist
     * @param next zupruefender Punkt
     * @return wenn Punkt gleich Endpunkt true
     */
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
