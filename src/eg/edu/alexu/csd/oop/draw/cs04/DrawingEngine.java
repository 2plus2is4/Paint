package eg.edu.alexu.csd.oop.draw.cs04;

import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs04.*;
import javafx.scene.canvas.GraphicsContext;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public class DrawingEngine implements eg.edu.alexu.csd.oop.draw.DrawingEngine {

    ArrayList<ArrayList<Shape>> History = new ArrayList<>(20);
    GraphicsContext gc;
    int iterator = 0;

    public GraphicsContext getGc() {
        return gc;
    }

    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }

    public DrawingEngine() {
        for (ArrayList<Shape> shapes : History) {
            shapes = new ArrayList<>();
        }
        iterator = -1;
    }

    @Override
    public void refresh(Object canvas) {
        for (Shape x : History.get(iterator)) {
            x.draw(gc);
        }
    }

    @Override
    public void addShape(Shape shape) {
        iterator++;
        History.get(iterator).add(shape);
    }

    @Override
    public void removeShape(Shape shape) {
        ArrayList<Shape> temp = History.get(++iterator);
        temp = History.get(iterator-1);
        History.get(iterator).remove(shape);
    }

    @Override
    public void updateShape(Shape oldShape, Shape newShape) {
        /*History.add(shapes);
        shapes.remove(oldShape);
        shapes.add(((eg.edu.alexu.csd.oop.draw.cs04.Shape) oldShape).getIndex(), newShape);*/
    }

    @Override
    public Shape[] getShapes() {
        return null;//(Shape[]) shapes.toArray();
    }

    @Override
    public List<Class<? extends Shape>> getSupportedShapes() {
        ArrayList<eg.edu.alexu.csd.oop.draw.cs04.Shape> temp = new ArrayList<>();
        return null;
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }

    @Override
    public void save(String path) {

    }

    @Override
    public void load(String path) {

    }

}
