package eg.edu.alexu.csd.oop.draw.cs04;

import java.awt.*;
import java.util.Map;

public class Shape implements eg.edu.alexu.csd.oop.draw.Shape {

    protected Point position;
    protected Map<String, Double> properties;
    protected Color color;
    protected Color fillcolor;
    protected Graphics canvas;

    @Override
    public void setPosition(Point position) {
        this.position = position;
    }

    @Override
    public Point getPosition() {
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
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public void setFillColor(Color color) {
        this.fillcolor = color;
    }

    @Override
    public Color getFillColor() {
        return this.fillcolor;
    }

    @Override
    public void draw(Graphics canvas) {

    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return this;
    }
}
