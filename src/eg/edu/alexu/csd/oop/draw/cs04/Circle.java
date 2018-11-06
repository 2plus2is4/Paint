package eg.edu.alexu.csd.oop.draw.cs04;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Circle extends Shape{
    private Double radius;

    public Circle(Double radius, Point position){
        this.radius = radius;
        this.position = position;
        Map<String,Double>temp=new HashMap<>();
        temp.put("radius",radius);
        this.setProperties(temp);
    }

    public Circle() {
        this.radius = 0.0;
        this.position = new Point(0,0);
        Map<String,Double>temp=new HashMap<>();
        temp.put("radius",0.0);
        this.setProperties(temp);
    }

    @Override
    public Object clone(){
        Circle c = new Circle();
        c.setColor(this.getColor());
        c.setFillColor(this.getFillColor());
        c.setPosition(this.getPosition());
        Map<String,Double> temp = new HashMap<>();
        int[] x = new int[0];
        for(Map.Entry<String,Double> s:this.getProperties().entrySet()){
            temp.put(s.getKey(),s.getValue());
        }
        c.setProperties(temp);
        return c;
    }

    @Override
    public void draw (Graphics canvas) {

        canvas.drawOval(this.position.x, this.position.y, this.radius.intValue(), this.radius.intValue());
    }

}

