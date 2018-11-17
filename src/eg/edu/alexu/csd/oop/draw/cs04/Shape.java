package eg.edu.alexu.csd.oop.draw.cs04;

import java.awt.*;
import java.util.Map;

public class Shape implements eg.edu.alexu.csd.oop.draw.Shape {

    private Point position;
    private Map<String, Double> properties;
    private Paint color;
    private Paint fillcolor;


    public Shape(Point position, Map<String, Double> properties, Color color, Color fillcolor) {
        this.position = position;
        this.properties = properties;
        this.color = color;
        this.fillcolor = fillcolor;
    }

    public Shape() {
    }

    @Override
    public void setPosition(Object position) {
        this.position = ((Point) position);
    }

    @Override
    public Object getPosition() {
        return this.position;
    }

    @Override
    public void setProperties(Map<String, Double> properties) {
        this.properties = properties;
    }

    @Override
    public Map<String, Double> getProperties() {
        return this.properties;
    }

    @Override
    public void setColor(Object color) {
        this.color = ((Color) color);
    }

    @Override
    public Object getColor() {
        return this.color;
    }

    @Override
    public void setFillColor(Object color) {
        this.fillcolor = ((Color)color);
    }

    @Override
    public Object getFillColor() {
        return this.fillcolor;
    }

    @Override
    public void draw(Object canvas) {

    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return this;
    }
}
