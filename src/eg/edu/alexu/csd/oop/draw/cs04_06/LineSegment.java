package eg.edu.alexu.csd.oop.draw.cs04_06;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class LineSegment extends MyShape {
    private Point p1,p2;

    public void setP1(Point p1) {
        this.p1 = p1;
        this.getProperties().put("upleftx",(double)p1.x);
        this.getProperties().put("uplefty",(double)p1.y);
    }

    public void setP2(Point p2) {
        this.p2 = p2;
        this.getProperties().put("upleftx",(double)p2.x);
        this.getProperties().put("uplefty",(double)p2.y);
    }

    public LineSegment(Point a, Point b){
        this.p1 = a;
        this.p2 = b;
        this.setPosition(new Point(p1.x,p1.y));
        Map<String,Double> temp=new HashMap<>();
        temp.put("upleftx", ((double) p1.x));
        temp.put("uplefty", ((double) p1.y));
        temp.put("downrightx", ((double) p2.x));
        temp.put("downrighty", ((double) p2.y));
        temp.put("t3x",null);
        temp.put("t3y",null);
        temp.put("shapeNo",3.0);
        this.setProperties(temp);
    }

    public LineSegment() {
        this.p1=new Point(0,0);
        this.p2=new Point(0,0);
        this.setPosition(new Point(p1.x,p1.y));
        Map<String,Double>temp=new HashMap<>();
        temp.put("upleftx", ((double) p1.x));
        temp.put("uplefty", ((double) p1.y));
        temp.put("downrightx", ((double) p2.x));
        temp.put("downrighty", ((double) p2.y));
        temp.put("t3x",null);
        temp.put("t3y",null);
        temp.put("shapeNo",3.0);
        this.setProperties(temp);
    }

    @Override
    public Object clone(){
        LineSegment l = new LineSegment();
        l.setColor(this.getColor());
        l.setFillColor(this.getFillColor());
        Point p = new Point(((Point) this.getPosition()).x,((Point) this.getPosition()).y);
        l.setPosition(p);
        Map<String,Double> temp = new HashMap<>();
        int[] x = new int[0];
        for(Map.Entry<String,Double> s:this.getProperties().entrySet()){
            temp.put(s.getKey(),s.getValue());
        }
        l.setProperties(temp);
        return l;
    }

    @Override
    public void draw (Object canvas) {
        ((GraphicsContext) canvas).setStroke((javafx.scene.paint.Paint) this.getColor());
        ((GraphicsContext) canvas).setFill((Paint) this.getFillColor());
        ((GraphicsContext) canvas).strokeLine(p1.x,p1.y,p2.x,p2.y);
    }

}
