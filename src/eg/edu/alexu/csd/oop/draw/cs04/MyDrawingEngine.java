package eg.edu.alexu.csd.oop.draw.cs04;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs04.*;
import javafx.scene.canvas.GraphicsContext;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MyDrawingEngine implements DrawingEngine {

    private ArrayList<ArrayList<Shape>> History = new ArrayList<>();
    private ArrayList<Shape> shapes = new ArrayList<>();
    private GraphicsContext gc;
    private int i = -1;
    private int undoCounter = 0;
    private int redoCounter = 0;

    public GraphicsContext getGc() {
        return gc;
    }

    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }

    public MyDrawingEngine() {
        /*
        for (ArrayList<Shape> shapes : History) {
            shapes = new ArrayList<>();
        }
        */
    }

    @Override
    public void refresh(Object canvas) {
        for (Shape x : shapes) {
            x.draw(canvas);
        }
    }

    @Override
    public void addShape(Shape shape) {
        delete();
        if (History.size() == 0) {
            ArrayList<Shape> x = new ArrayList<>(0);
            i++;
            History.add(x);
        }

        shapes.add(shape);
        ArrayList<Shape> t = new ArrayList<>();
        try {
            copy(t);
        } catch (Exception e) {
            System.out.println("exception");
        }
        History.add(++i, t);
    }

    @Override
    public void removeShape(Shape shape) {
        delete();

        shapes.remove(shape);
        ArrayList<Shape> t = new ArrayList<>();
        try {
            copy(t);
        } catch (Exception e) {
            System.out.println("exception");
        }
        History.add(++i, t);
    }

    @Override
    public void updateShape(Shape oldShape, Shape newShape) {
        delete();

        int ind = shapes.indexOf(oldShape);
        shapes.add(ind, newShape);
        shapes.remove(oldShape);
        ArrayList<Shape> t = new ArrayList<>();
        try {
            copy(t);
        } catch (Exception e) {
            System.out.println("exception");
        }
        History.add(++i, t);
    }

    @Override
    public Shape[] getShapes() {
        Shape[] x = new Shape[History.get(i).size()];
        return History.get(i).toArray(x);
    }

    @Override
    public List<Class<? extends Shape>> getSupportedShapes() {
        ArrayList<Class<? extends Shape>> temp = new ArrayList<>();
        try {
            temp.add(((Class<? extends Shape>) Class.forName("eg.edu.alexu.csd.oop.draw.cs04.Circle")));
            temp.add(((Class<? extends Shape>) Class.forName("eg.edu.alexu.csd.oop.draw.cs04.Ellipse")));
            temp.add(((Class<? extends Shape>) Class.forName("eg.edu.alexu.csd.oop.draw.cs04.LineSegment")));
            temp.add(((Class<? extends Shape>) Class.forName("eg.edu.alexu.csd.oop.draw.cs04.Rectangle")));
            temp.add(((Class<? extends Shape>) Class.forName("eg.edu.alexu.csd.oop.draw.cs04.Square")));
            temp.add(((Class<? extends Shape>) Class.forName("eg.edu.alexu.csd.oop.draw.cs04.Triangle")));

        } catch (Exception e) {

        }


        return temp;
    }

    @Override
    public void undo() {
        if (i>0) {
            i--;
        }
    }

    @Override
    public void redo() {
        if (i < History.size() - 1) {
            i++;
        }
    }

    @Override
    public void save(String path) {

    }

    @Override
    public void load(String path) {

    }

    private void delete() {

        if(i<History.size()-1 && i>-1) {
            if((i<History.size()-1)|| (shapes.size()!=History.get(i).size())) {
                ArrayList<Shape> t = new ArrayList<>();
                for (int j = 1; j <= History.get(i).size(); j++) {
                    t.add(shapes.get(j));
                }
                shapes = t;
            }
        }
        /*

*/
        ArrayList<ArrayList<Shape>> h = new ArrayList<>();
        for (int ii = 0; ii <= i; ii++) {
            h.add(History.get(ii));
        }
        History = h;

        if (History.size() >= 21) {
            History.remove(0);
                i--;
        }
    }

    private void copy(ArrayList<Shape> t) throws CloneNotSupportedException {
        for (Shape shape : shapes) {
            Shape x = ((Shape) shape.clone());
            t.add(x);
        }
    }

}
