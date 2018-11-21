package eg.edu.alexu.csd.oop.draw.cs04_06;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;


public class Square extends Rectangle {
    private Point upleft, downright;

    @Override
    public void setUpleft(Point upleft) {
        this.upleft = upleft;
        this.getProperties().put("upleftx",(double)upleft.x);
        this.getProperties().put("uplefty",(double)upleft.y);
    }

    @Override
    public void setDownright(Point downright) {
        this.downright = downright;
        this.getProperties().put("downrightx",(double)downright.x);
        this.getProperties().put("downrighty",(double)downright.y);
    }

    public Square(Point downright , Point position){
        this.upleft = position;
        this.downright = downright;
        this.setPosition(new Point(upleft.x,upleft.y));
        Map<String,Double> temp=new HashMap<>();
        temp.put("upleftx",(double)upleft.x);
        temp.put("uplefty",(double)upleft.y);
        temp.put("downrightx",(double)downright.x);
        temp.put("downrighty",(double)downright.y);
        temp.put("t3x",null);
        temp.put("t3y",null);
        temp.put("shapeNo",5.0);
        this.setProperties(temp);
    }

    public Square() {
        this.upleft =new Point(0,0);
        this.downright = new Point(0,0);
        this.setPosition(new Point(0,0));
        Map<String,Double>temp=new HashMap<>();
        temp.put("upleftx",(double)upleft.x);
        temp.put("uplefty",(double)upleft.y);
        temp.put("downrightx",(double)downright.x);
        temp.put("downrighty",(double)downright.y);
        temp.put("t3x",null);
        temp.put("t3y",null);
        temp.put("shapeNo",5.0);
        this.setProperties(temp);
    }

    @Override
    public Object clone(){
        Square s = new Square();
        s.setColor(this.getColor());
        s.setFillColor(this.getFillColor());
        Point p = new Point(((Point) this.getPosition()).x,((Point) this.getPosition()).y);
        s.setPosition(p);
        Map<String,Double> temp = new HashMap<>();
        int[] x = new int[0];
        for(Map.Entry<String,Double> ss:this.getProperties().entrySet()){
            temp.put(ss.getKey(),ss.getValue());
        }
        s.setProperties(temp);
        return s;
    }

    @Override
    public void draw (Object canvas) {
        ((GraphicsContext) canvas).setStroke((javafx.scene.paint.Paint) this.getColor());
        ((GraphicsContext) canvas).setFill((Paint) this.getFillColor());
        ((GraphicsContext) canvas).strokeRect(upleft.x,upleft.y,downright.x-upleft.x,downright.x-upleft.x);
        ((GraphicsContext) canvas).fillRect(upleft.x,upleft.y,downright.x-upleft.x,downright.x-upleft.x);
    }
}
