package eg.edu.alexu.csd.oop.draw.cs04;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Ellipse extends Shape {
    private Double a, b;
    private Point position;

    public Ellipse(Double a, Double b ,Point position){
        this.a = a;
        this.b = b;
        this.setPosition(this.position);
        Map<String,Double> temp=new HashMap<>();
        temp.put("a",a);
        temp.put("b",b);
        this.setProperties(temp);
    }

    public Ellipse() {
        this.a = 0.0;
        this.b = 0.0;
        this.setPosition(this.position);
        Map<String,Double>temp=new HashMap<>();
        temp.put("a",0.0);
        temp.put("b",0.0);
        this.setProperties(temp);
    }

    @Override
    public Object clone(){
        Ellipse e = new Ellipse();
        e.setColor(this.getColor());
        e.setFillColor(this.getFillColor());
        Point p = new Point(((Point) this.getPosition()).x,((Point) this.getPosition()).y);
        e.setPosition(p);
        Map<String,Double> temp = new HashMap<>();
        int[] x = new int[0];
        for(Map.Entry<String,Double> s:this.getProperties().entrySet()){
            temp.put(s.getKey(),s.getValue());
        }
        e.setProperties(temp);
        return e;
    }

    @Override
    public void draw (Object canvas) {
        ((GraphicsContext) canvas).setStroke((javafx.scene.paint.Paint) this.getColor());
        ((GraphicsContext) canvas).setFill((Paint) this.getFillColor());
        ((GraphicsContext)canvas).strokeOval((this.position.x), (this.position.y), this.a.intValue(), this.b.intValue());
        ((GraphicsContext)canvas).fillOval((this.position.x), (this.position.y), this.a.intValue(), this.b.intValue());
    }

}
