package eg.edu.alexu.csd.oop.draw.cs04;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Triangle extends Shape {
    private Point p1,p2,p3;
    public Triangle(Point a, Point b, Point c){
        this.p1 = a;
        this.p2 = b;
        this.p3 = c;
        Point p4 =new Point((p3.x-p2.x)/2,(p3.y-p2.y)/2);
        this.setPosition(new Point(Math.min(Math.min(p2.x,p3.x),p1.x),Math.min(Math.min(p2.y,p3.y),p1.y)));
        Map<String,Double> temp=new HashMap<>();
        temp.put("p1x", ((double) p1.x));
        temp.put("p1y", ((double) p1.y));
        temp.put("p2x", ((double) p2.x));
        temp.put("p2y", ((double) p2.y));
        temp.put("p3x", ((double) p3.x));
        temp.put("p3y", ((double) p3.y));
        this.setProperties(temp);
    }

    public Triangle() {
        this.p1=new Point(0,0);
        this.p2=new Point(0,0);
        this.p3=new Point(0,0);
        this.setPosition(new Point(Math.min(Math.min(p2.x,p3.x),p1.x),Math.min(Math.min(p2.y,p3.y),p1.y)));
        Map<String,Double>temp=new HashMap<>();
        temp.put("p1x", ((double) p1.x));
        temp.put("p1y", ((double) p1.y));
        temp.put("p2x", ((double) p2.x));
        temp.put("p2y", ((double) p2.y));
        this.setProperties(temp);
    }

    @Override
    public Object clone(){
        Triangle t = new Triangle();
        t.setColor(this.getColor());
        t.setFillColor(this.getFillColor());
        Point p = new Point(((Point) this.getPosition()).x,((Point) this.getPosition()).y);
        t.setPosition(p);
        Map<String,Double> temp = new HashMap<>();
        int[] x = new int[0];
        for(Map.Entry<String,Double> s:this.getProperties().entrySet()){
            temp.put(s.getKey(),s.getValue());
        }
        t.setProperties(temp);
        return t;
    }

    @Override
    public void draw (Object canvas) {

        double[] y= {((double) p1.y), ((double) p2.y), ((double) p3.y)};
        double[] x= {((double) p1.x), ((double) p2.x), ((double) p3.x)};


        ((GraphicsContext) canvas).setStroke((Paint) this.getColor());
        ((GraphicsContext) canvas).setFill((Paint) this.getFillColor());
        ((GraphicsContext) canvas).fillPolygon( x,y,3);
        ((GraphicsContext) canvas).strokePolygon( x,y,3);
    }

}
