package eg.edu.alexu.csd.oop.draw.cs04_06;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

public class Main {
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

        Triangle t=new Triangle();
    }
}
