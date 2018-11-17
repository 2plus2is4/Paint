package eg.edu.alexu.csd.oop.draw.cs04;

import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs04.*;
import javafx.scene.canvas.GraphicsContext;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public class DrawingEngine implements eg.edu.alexu.csd.oop.draw.DrawingEngine {

    private ArrayList<ArrayList<Shape>> History = new ArrayList<>();
    private GraphicsContext gc;
    private int i=-1;
    private int ID=1;

    public GraphicsContext getGc() {
        return gc;
    }

    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }

    public DrawingEngine() {
        /*
        for (ArrayList<Shape> shapes : History) {
            shapes = new ArrayList<>();
        }
        */
    }

    @Override
    public void refresh(Object canvas) {
        for (Shape x : History.get(i)) {
            x.draw(canvas);
        }
    }

    @Override
    public void addShape(Shape shape) {
        if (History.size() >= 20) {
            History.remove(0);
        }
        ArrayList<Shape> t = new ArrayList<>();
        if (History.size() > 0) {
            try {
                copy(t);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        ((eg.edu.alexu.csd.oop.draw.cs04.Shape) shape).setIndex(ID++);
        t.add(shape);
        History.add(++i,t);
    }

    @Override
    public void removeShape(Shape shape) {
        if (History.size() >= 20) {
            History.remove(0);
        }
        ArrayList<Shape> temp = new ArrayList<>();
        if (History.size() > 0) {
            try {
                copy(temp);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        temp.remove(shape);
        History.add(++i,temp);
    }

    @Override
    public void updateShape(Shape oldShape, Shape newShape) {
        if (History.size() >= 20) {
            History.remove(0);
        }
        ArrayList<Shape> t = new ArrayList<>();
        if (History.size() > 0) {
            try {
                copy(t);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        ((eg.edu.alexu.csd.oop.draw.cs04.Shape) newShape).setIndex(ID++);
        t.add(newShape);
        t.remove(oldShape);
        History.add(++i,t);
    }

    @Override
    public Shape[] getShapes() {
        Shape[] x = new Shape[History.get(i).size()];
        return History.get(i).toArray(x);
    }

    @Override
    public List<Class<? extends Shape>> getSupportedShapes() {
        ArrayList<eg.edu.alexu.csd.oop.draw.cs04.Shape> temp = new ArrayList<>();
        return null;
    }

    @Override
    public void undo() {
        i--;
        refresh(gc);
    }

    @Override
    public void redo() {
        i++;
        refresh(gc);
    }

    @Override
    public void save(String path) {

    }

    @Override
    public void load(String path) {

    }

    private void copy(ArrayList<Shape> t) throws CloneNotSupportedException {
        ArrayList<Shape> o = History.get(i);
        for (Shape shape : o) {
            Shape x = ((Shape) shape.clone());
            t.add(x);
        }
    }
}
