package eg.edu.alexu.csd.oop.draw.cs04;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

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
    public void draw (Object canvas) {
        ((GraphicsContext) canvas).setStroke((javafx.scene.paint.Paint) this.getColor());
        ((GraphicsContext) canvas).setFill((Paint) this.getFillColor());
        ((GraphicsContext) canvas).strokeRect(upleft.x,upleft.y,downright.x-upleft.x,downright.y-upleft.y);
        ((GraphicsContext) canvas).fillRect(upleft.x,upleft.y,downright.x-upleft.x,downright.y-upleft.y);
    }
}
