package eg.edu.alexu.csd.oop.draw.cs04;

import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs04.*;
import javafx.scene.canvas.GraphicsContext;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class DrawingEngine implements eg.edu.alexu.csd.oop.draw.DrawingEngine {

    private ArrayList<ArrayList<Shape>> History = new ArrayList<>();
    private ArrayList<Shape> shapes = new ArrayList<>();
    private GraphicsContext gc;
    private int i=-1;
    private int ID=1;
    private int zize = 0;

    public GraphicsContext getGc() {
        return gc;
    }

    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }

    public DrawingEngine() {
        /*
        for (ArrayList<Shape> shapes : History) {
            shapes = new ArrayList<>();
        }
        */
    }

    @Override
    public void refresh(Object canvas) {
        for (Shape x : shapes) {
            x.draw(canvas);
        }
    }

    @Override
    public void addShape(Shape shape) {
        delete();
        if(History.size()==0){
            ArrayList<Shape> x = new ArrayList<>(0);
            i++;
            History.add(x);
        }
        if (History.size() >= 21) {
            History.remove(0);

            i=19;
        }
        shapes.add(shape);
        ArrayList<Shape> t = new ArrayList<>();
            try {
                copy(t);
            } catch (Exception e) {
                System.out.println("exception");
        }
//        shape.getProperties().put("ID",(double) ID++);
//        ((eg.edu.alexu.csd.oop.draw.cs04.Shape) shape).setIndex(ID++);
//        t.add(shape);
        History.add(++i,t);
    }

    @Override
    public void removeShape(Shape shape) {
        delete();
        if (History.size() >= 21) {
            History.remove(0);
            i--;
        }
        shapes.remove(shape);
        ArrayList<Shape> t = new ArrayList<>();
            try {
                copy(t);
            } catch (Exception e) {
                System.out.println("exception");
            }
//        shape.getProperties().put("ID",(double) ID++);
//        ((eg.edu.alexu.csd.oop.draw.cs04.Shape) shape).setIndex(ID++);
//        t.add(shape);
        History.add(++i,t);
    }

    @Override
    public void updateShape(Shape oldShape, Shape newShape) {
        delete();
        if (History.size() >= 21) {
            History.remove(0);
            i--;
        }
        shapes.add(shapes.indexOf(oldShape),newShape);
        shapes.remove(oldShape);
        ArrayList<Shape> t = new ArrayList<>();
            try {
                copy(t);
            } catch (Exception e) {
                System.out.println("exception");
            }

//        shape.getProperties().put("ID",(double) ID++);
//        ((eg.edu.alexu.csd.oop.draw.cs04.Shape) shape).setIndex(ID++);
//        t.add(shape);
        History.add(++i,t);
    }

    @Override
    public Shape[] getShapes() {
        Shape[] x = new Shape[History.get(i).size()];
        return History.get(i).toArray(x);
    }

    @Override
    public List<Class<? extends Shape>> getSupportedShapes() {
        ArrayList<Class<? extends Shape>> temp = new ArrayList<>();
        try {
            temp.add(((Class<? extends Shape>) Class.forName("eg.edu.alexu.csd.oop.draw.cs04.Circle")));
            temp.add(((Class<? extends Shape>) Class.forName("eg.edu.alexu.csd.oop.draw.cs04.Ellipse")));
            temp.add(((Class<? extends Shape>) Class.forName("eg.edu.alexu.csd.oop.draw.cs04.LineSegment")));
            temp.add(((Class<? extends Shape>) Class.forName("eg.edu.alexu.csd.oop.draw.cs04.Rectangle")));
            temp.add(((Class<? extends Shape>) Class.forName("eg.edu.alexu.csd.oop.draw.cs04.Square")));
            temp.add(((Class<? extends Shape>) Class.forName("eg.edu.alexu.csd.oop.draw.cs04.Triangle")));

        }catch(Exception e){

        }


        return temp;
    }

    @Override
    public void undo() {
        if(i>0) {
            i--;
        }
    }

    @Override
    public void redo() {
        if(i<History.size()-1) {
            i++;
        }
    }

    @Override
    public void save(String path) {

    }

    @Override
    public void load(String path) {

    }

    private void delete(){
        ArrayList<ArrayList<Shape>> h = new ArrayList<>();
        for(int ii=0;ii<=i;ii++){
            h.add(History.get(ii));
        }
        History = h;
    }

    private void copy(ArrayList<Shape> t) throws CloneNotSupportedException {
        for (Shape shape : shapes) {
                Shape x = ((Shape) shape.clone());
                t.add(x);
        }
    }

}
