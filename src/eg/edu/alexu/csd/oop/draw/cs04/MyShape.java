package eg.edu.alexu.csd.oop.draw.cs04;

import eg.edu.alexu.csd.oop.draw.Shape;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.paint.Color;

public class MyShape implements Shape {
    public void setPosition(Point position) {
        this.position = position;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setFillcolor(Color fillcolor) {
        this.fillcolor = fillcolor;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    private Point position;
    private Map<String, Double> properties;
    private Color color;

    public Color getFillcolor() {
        return fillcolor;
    }

    public int getIndex() {
        return index;
    }

    private Color fillcolor;
    private int index;

    public MyShape(Point position, Map<String, Double> properties, Color color, Color fillcolor) {
        this.position = position;
        this.properties = properties;
        this.color = color;
        this.fillcolor = fillcolor;
    }

    public MyShape() {
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
        this.color = ((javafx.scene.paint.Color) color);
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
        MyShape c = new MyShape();
        c.setColor(this.getColor());
        c.setFillColor(this.getFillColor());
        Point p = new Point(((Point) this.getPosition()).x,((Point) this.getPosition()).y);
        c.setPosition(p);
        Map<String,Double> temp = new HashMap<>();
        for(Map.Entry<String,Double> s:this.getProperties().entrySet()){
            temp.put(s.getKey(),s.getValue());
        }
        c.setProperties(temp);
        return c;
    }
}
