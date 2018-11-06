package eg.edu.alexu.csd.oop.draw.cs04;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Rectangle extends Shape{
    private Point upleft, downright;

    public Rectangle(Point a, Point b ,Point position){
        this.upleft = a;
        this.downright = b;
        this.position = new Point((downright.x-upleft.x)/2,(downright.y-upleft.y)/2);
        Map<String,Double> temp=new HashMap<>();
        temp.put("upleftx",(double)upleft.x);
        temp.put("uplefty",(double)upleft.y);
        temp.put("downrightx",(double)downright.x);
        temp.put("downrighty",(double)downright.y);
        this.setProperties(temp);
    }

    public Rectangle() {
        this.upleft =new Point(0,0);
        this.downright = new Point(0,0);
        this.position = new Point(0,0);
        Map<String,Double>temp=new HashMap<>();
        temp.put("upleftx",(double)upleft.x);
        temp.put("uplefty",(double)upleft.y);
        temp.put("downrightx",(double)downright.x);
        temp.put("downrighty",(double)downright.y);
        this.setProperties(temp);
    }

    @Override
    public Object clone(){
        Rectangle r = new Rectangle();
        r.setColor(this.getColor());
        r.setFillColor(this.getFillColor());
        r.setPosition(this.getPosition());
        Map<String,Double> temp = new HashMap<>();
        int[] x = new int[0];
        for(Map.Entry<String,Double> s:this.getProperties().entrySet()){
            temp.put(s.getKey(),s.getValue());
        }
        r.setProperties(temp);
        return r;
    }
    @Override
    public void draw (Graphics canvas) {
        canvas.drawRect((downright.x-upleft.x)/2,(downright.y-upleft.y)/2,downright.x-upleft.x,downright.y-upleft.y);
    }
}