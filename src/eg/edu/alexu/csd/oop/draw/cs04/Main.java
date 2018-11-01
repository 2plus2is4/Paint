package eg.edu.alexu.csd.oop.draw.cs04;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args){
        Circle circle = new Circle();
        Map<String,Double> temp = new HashMap<>();
        temp.put("radius",0.5);
        temp.put("height",0.6);
        circle.setProperties(temp);
    }
}
