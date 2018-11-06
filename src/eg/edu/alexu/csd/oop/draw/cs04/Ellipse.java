package eg.edu.alexu.csd.oop.draw.cs04;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Ellipse extends Shape {
    private Double a, b;

    public Ellipse(Double a, Double b ,Point position){
        this.a = a;
        this.b = b;
        this.position = position;
        Map<String,Double> temp=new HashMap<>();
        temp.put("a",a);
        temp.put("b",b);
        this.setProperties(temp);
    }

    public Ellipse() {
        this.a = 0.0;
        this.b = 0.0;
        this.position = new Point(0,0);
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
        e.setPosition(this.getPosition());
        Map<String,Double> temp = new HashMap<>();
        int[] x = new int[0];
        for(Map.Entry<String,Double> s:this.getProperties().entrySet()){
            temp.put(s.getKey(),s.getValue());
        }
        e.setProperties(temp);
        return e;
    }

    @Override
    public void draw (Graphics canvas) {

        canvas.drawOval(this.position.x, this.position.y, this.a.intValue(), this.b.intValue());
    }

}
