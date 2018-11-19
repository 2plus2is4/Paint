package eg.edu.alexu.csd.oop.draw.cs04;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args){
        MyDrawingEngine engine = new MyDrawingEngine();
        Point p = new Point(0,0);
        Circle c1 = new Circle(1.0,p);
        Circle c2 = new Circle(2.0,p);
        Circle c3 = new Circle(3.0,p);
        Circle c4 = new Circle(4.0,p);
        engine.addShape(c1);
        engine.addShape(c2);
        engine.addShape(c3);
        engine.removeShape(c3);
        engine.undo();
        engine.updateShape(c3,c4);
        engine.undo();
        engine.removeShape(c1);
        System.out.println(engine.getShapes().length);
        //engine.addShape(c4);
    }
}
