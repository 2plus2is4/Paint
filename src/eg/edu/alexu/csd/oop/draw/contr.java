package eg.edu.alexu.csd.oop.draw;

import eg.edu.alexu.csd.oop.draw.cs04_06.Circle;
import eg.edu.alexu.csd.oop.draw.cs04_06.MyDrawingEngine;
import eg.edu.alexu.csd.oop.draw.cs04_06.Rectangle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class contr implements Initializable {

    @FXML
    Pane pane;
    private MyDrawingEngine engine = new MyDrawingEngine();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setting(){
        Canvas canvas = new Canvas(700, 700);
        GraphicsContext gc = canvas.getGraphicsContext2D();;
        engine.setGc(canvas.getGraphicsContext2D());
        gc = engine.getGc();
        drawShapes(engine.getGc());
        pane.getChildren().add(canvas);
        drawShapes2(engine.getGc());
    }

    private void drawShapes(GraphicsContext gc) {
        Rectangle c = new Rectangle(new Point(2,2),new Point(200,200));
        c.setUpleft(new Point(2,2));
        c.setDownright(new Point(200,200));
        c.setFillColor(javafx.scene.paint.Color.BLACK);
        c.setColor(javafx.scene.paint.Color.YELLOW);
        c.draw(gc);
        Circle s = new Circle(new Point(5,5),new Point(30,30));
        s.setColor(javafx.scene.paint.Color.YELLOW);
        s.setFillColor(javafx.scene.paint.Color.YELLOW);
        s.draw(gc);
        /*gc.setLineWidth(5);
        gc.strokeLine(40, 10, 10, 40);
        gc.fillOval(10, 60, 30, 30);
        gc.strokeOval(10, 60, 30, 30);
        gc.fillRoundRect(110, 60, 30, 30, 10, 10);
        gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
        gc.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
        gc.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
        gc.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
        gc.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
        gc.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
        gc.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);
        gc.fillPolygon(new double[]{10, 40, 10, 40},
                new double[]{210, 210, 240, 240}, 4);
        gc.strokePolygon(new double[]{60, 90, 60, 90},
                new double[]{210, 210, 240, 240}, 4);
        gc.strokePolyline(new double[]{110, 140, 110, 140},
                new double[]{210, 210, 240, 240}, 4);*/
    }

    private void drawShapes2(GraphicsContext gc) {
        Rectangle c = new Rectangle(new Point(200,200),new Point(250,250));
        c.setFillColor(javafx.scene.paint.Color.GREEN);
        c.setColor(Color.YELLOW);
        c.draw(gc);
        /*gc.setLineWidth(5);
        gc.strokeLine(40, 10, 10, 40);
        gc.fillOval(10, 60, 30, 30);
        gc.strokeOval(10, 60, 30, 30);
        gc.fillRoundRect(110, 60, 30, 30, 10, 10);
        gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
        gc.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
        gc.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
        gc.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
        gc.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
        gc.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
        gc.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);
        gc.fillPolygon(new double[]{10, 40, 10, 40},
                new double[]{210, 210, 240, 240}, 4);
        gc.strokePolygon(new double[]{60, 90, 60, 90},
                new double[]{210, 210, 240, 240}, 4);
        gc.strokePolyline(new double[]{110, 140, 110, 140},
                new double[]{210, 210, 240, 240}, 4);*/
    }
}
