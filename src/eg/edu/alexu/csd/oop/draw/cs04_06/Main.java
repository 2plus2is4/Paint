package eg.edu.alexu.csd.oop.draw.cs04_06;

import eg.edu.alexu.csd.oop.draw.contr;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
//        primaryStage.setTitle("Drawing Operations Test");
//        FXMLLoader fxmlLoader =
//        BorderPane root = new BorderPane();
//        primaryStage.setScene(new Scene(root));
//        Canvas canvas = new Canvas(700, 700);
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//        drawShapes(gc);
//        root.getChildren().add(canvas);
//        drawShapes2(gc);
//        primaryStage.show();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("file.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        primaryStage.setTitle("Drawing Operations Test");
        primaryStage.setResizable(false);
        Scene scene = new Scene(root, 700, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args){
        Circle circle = new Circle();
        /*
        Frame f = new Frame("DrawOOP");
        f.setSize(800,800);
        f.setResizable(false);
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        f.setVisible(true);
        Map<String,Double> temp = new HashMap<>();
        temp.put("radius",0.5);
        circle.setProperties(temp);
        */
        MyDrawingEngine de = new MyDrawingEngine();
        Circle circle1 = new Circle();
        Circle circle2 = new Circle();
        Circle circle3 = new Circle();
        Circle circle4 = new Circle();
        Canvas canvas = new Canvas(700,700);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        de.setGc(graphicsContext);
        de.refresh(graphicsContext);
        de.undo();
        de.redo();
        de.redo();
        de.addShape(circle);
        de.addShape(circle1);
        de.undo();
        de.addShape(circle2);
        de.redo();
        de.undo();
        de.redo();
        Triangle t=new Triangle();
    }
}
