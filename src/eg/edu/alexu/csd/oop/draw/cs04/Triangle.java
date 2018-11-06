package eg.edu.alexu.csd.oop.draw.cs04;

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
        this.position = new Point(Math.abs((p4.x-p1.x)*(2/3)),Math.abs((p4.y-p1.y)*(2/3)));
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
        this.position = new Point((p1.x+p2.x)/2,(p1.y+p2.y)/2);
        Map<String,Double>temp=new HashMap<>();
        temp.put("p1x", ((double) p1.x));
        temp.put("p1y", ((double) p1.y));
        temp.put("p2x", ((double) p2.x));
        temp.put("p2y", ((double) p2.y));
        this.setProperties(temp);
    }

    @Override
    public Object clone(){
        LineSegment l = new LineSegment();
        l.setColor(this.getColor());
        l.setFillColor(this.getFillColor());
        l.setPosition(this.getPosition());
        Map<String,Double> temp = new HashMap<>();
        int[] x = new int[0];
        for(Map.Entry<String,Double> s:this.getProperties().entrySet()){
            temp.put(s.getKey(),s.getValue());
        }
        l.setProperties(temp);
        return l;
    }

    @Override
    public void draw (Graphics canvas) {

        canvas.drawLine(p1.x,p1.y,p2.x,p2.y);
    }

}
