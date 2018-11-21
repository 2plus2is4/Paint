package eg.edu.alexu.csd.oop.draw.cs04_06;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Ellipse extends MyShape {
    private Point upleft, downright;

    public void setUpleft(Point upleft) {
        this.upleft = upleft;
        this.getProperties().put("upleftx",(double)upleft.x);
        this.getProperties().put("uplefty",(double)upleft.y);
    }

    public void setDownright(Point downright) {
        this.downright = downright;
        this.getProperties().put("downrightx",(double)downright.x);
        this.getProperties().put("downrighty",(double)downright.y);
    }

    public Ellipse(Point downright, Point position){
        this.upleft = position;
        this.downright = downright;
        Map<String,Double>temp=new HashMap<>();
        temp.put("upleftx",(double)upleft.x);
        temp.put("uplefty",(double)upleft.y);
        temp.put("downrightx",(double)downright.x);
        temp.put("downrighty",(double)downright.y);
        temp.put("t3x",null);
        temp.put("t3y",null);
        temp.put("shapeNo",2.0);
        this.setProperties(temp);
    }

    public Ellipse() {
        this.upleft = new Point(0,0);
        this.downright = new Point(0,0);
        Map<String,Double>temp=new HashMap<>();
        temp.put("upleftx",(double)upleft.x);
        temp.put("uplefty",(double)upleft.y);
        temp.put("downrightx",(double)downright.x);
        temp.put("downrighty",(double)downright.y);
        temp.put("t3x",null);
        temp.put("t3y",null);
        temp.put("shapeNo",2.0);
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
        ((GraphicsContext)canvas).strokeOval((this.upleft.x), (this.upleft.y),((upleft.x-downright.x)/2), ((upleft.y-downright.y)/2));
        ((GraphicsContext)canvas).fillOval((this.upleft.x), (this.upleft.y),((upleft.x-downright.x)/2), ((upleft.y-downright.y)/2));
    }

}
