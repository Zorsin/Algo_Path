package pathfinding;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;

/**
 * 07.10.2017
 *
 * @author SWirries
 */
public class Main extends Application {

    private static Pathfinder pathfinder;
    private static int n;

    public static void main(String[] args) {

        n = 5;
        Point start = new Point(0,0);
        Point end = new Point (n,0);

        pathfinder = new Pathfinder(start,end);
        pathfinder.findPath();

        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        int wayCount = 0;
        Canvas canvas;
        GraphicsContext c;
        Scene scene;

// window setup
        primaryStage.setTitle("Algo_Path");
        CanvasPane canvasPane = new CanvasPane(1600,1000);
        canvas = canvasPane.getCanvas();
        c = canvas.getGraphicsContext2D();
        BorderPane root = new BorderPane(canvasPane);
        scene = new Scene(root, 1920, 1080);
        primaryStage.setScene(scene);

        primaryStage.show();

        ArrayList<ArrayList<Way>> paths = pathfinder.getAllPaths();


        // draw all paths

        double startX = 20;
        double startY = 20;
        double arrowLength = 16;
        int drawsPerLine = (int) (canvas.getWidth()/((n)*arrowLength+arrowLength))-2;
        int currentIndex = 1;
        System.out.println("drawsPerLine: " + drawsPerLine);
        Color background = Color.WHITE;
        c.setFill(background);
        Color arrowColor = Color.BLACK;
        Color dotColor = Color.BLUE;

        // loop this

        for(ArrayList<Way> drawPath : paths){

            // draw path
            c.setFill(arrowColor);

            double currentX = startX;
            double currentY = startY+n*arrowLength;
            double endX = 0;
            double endY = 0;

            for( Way drawWay : drawPath){

                switch (drawWay){
                    case TOP:
                        endX = currentX;
                        endY = currentY - arrowLength;
                        break;
                    case RIGHT:
                        endX = currentX + arrowLength;
                        endY = currentY;
                        break;
                    case BOTTOMRIGHT:
                        endX = currentX + arrowLength;
                        endY = currentY + arrowLength;
                        break;
                    case TOPLEFT:
                        endX = currentX - arrowLength;
                        endY = currentY - arrowLength;
                        break;
                    case TOPRIGHT:
                        endX = currentX + arrowLength;
                        endY = currentY - arrowLength;
                        break;
                }
                c.setFill(dotColor);
                c.fillOval(endX-2, endY-2, 3, 3);
                wayCount++;
                c.setFill(arrowColor);
                c.strokeLine(currentX, currentY, endX, endY);
                currentX = endX;
                currentY = endY;

            }

            c.setFill(Color.RED);
            // draw start
            c.fillOval(startX-2, startY+n*arrowLength-2, 3, 3);
            wayCount++;
            // draw end
            c.fillOval(startX+n*arrowLength-2, startY+n*arrowLength-2, 3, 3);

            if(currentIndex>drawsPerLine) {
                startY += (n+1)*arrowLength;
                currentIndex = 0;
            }
            startX = 20 + currentIndex*(n+1)*arrowLength;
            currentIndex++;
        }
        System.out.println("Way:"+wayCount);


    }

    private static class CanvasPane extends Pane {

        private final javafx.scene.canvas.Canvas canvas;

        public CanvasPane(double width, double height) {
            canvas = new javafx.scene.canvas.Canvas(width, height);
            getChildren().add(canvas);
        }

        public Canvas getCanvas() {
            return canvas;
        }

        @Override
        protected void layoutChildren() {
            final double x = snappedLeftInset();
            final double y = snappedTopInset();
            final double w = snapSize(getWidth()) - x - snappedRightInset();
            final double h = snapSize(getHeight()) - y - snappedBottomInset();
            canvas.setLayoutX(x);
            canvas.setLayoutY(y);
            canvas.setWidth(w);
            canvas.setHeight(h);
        }
    }


}
