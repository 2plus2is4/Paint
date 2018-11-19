package eg.edu.alexu.csd.oop.draw.cs04;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;


public class Square extends Rectangle {
    private Point upleft = new Point(0,0), downright = new Point(0,0);

    public Square(Point a, Point b ,Point position){
        super(a,b,position);
    }

    public Square() {
        super();
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
        ((GraphicsContext) canvas).strokeRect(upleft.x,upleft.y,downright.x-upleft.x,downright.y-upleft.y);
        ((GraphicsContext) canvas).fillRect(upleft.x,upleft.y,downright.x-upleft.x,downright.y-upleft.y);
    }
}
