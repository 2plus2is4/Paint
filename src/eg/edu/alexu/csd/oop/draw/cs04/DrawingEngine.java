package eg.edu.alexu.csd.oop.draw.cs04;

import eg.edu.alexu.csd.oop.draw.Shape;

import java.awt.*;
import java.util.List;

public class DrawingEngine implements eg.edu.alexu.csd.oop.draw.DrawingEngine {

    @Override
    public void refresh(Object canvas) {

    }

    @Override
    public void addShape(Shape shape) {

    }

    @Override
    public void removeShape(Shape shape) {

    }

    @Override
    public void updateShape(Shape oldShape, Shape newShape) {

    }

    @Override
    public Shape[] getShapes() {
        return new Shape[0];
    }

    @Override
    public List<Class<? extends Shape>> getSupportedShapes() {
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
