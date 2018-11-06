package eg.edu.alexu.csd.oop.draw.cs04;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Square extends Rectangle {
    private Point upleft, downright;

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
        s.setPosition(this.getPosition());
        Map<String,Double> temp = new HashMap<>();
        int[] x = new int[0];
        for(Map.Entry<String,Double> ss:this.getProperties().entrySet()){
            temp.put(ss.getKey(),ss.getValue());
        }
        s.setProperties(temp);
        return s;
    }

    @Override
    public void draw (Graphics canvas) {
        canvas.drawRect((downright.x-upleft.x)/2,(downright.y-upleft.y)/2,downright.x-upleft.x,downright.y-upleft.y);
    }
}
