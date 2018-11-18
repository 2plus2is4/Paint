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
    private GraphicsContext gc;
    private int i=-1;
    private int ID=1;

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
        for (Shape x : History.get(i)) {
            x.draw(canvas);
        }
    }

    @Override
    public void addShape(Shape shape) {
        if (History.size() >= 20) {
            History.remove(0);
        }
        ArrayList<Shape> t = new ArrayList<>();
        if (History.size() > 0) {
            try {
                copy(t,null);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
//        shape.getProperties().put("ID",(double) ID++);
        //((eg.edu.alexu.csd.oop.draw.cs04.Shape) shape).setIndex(ID++);
        t.add(shape);
        History.add(++i,t);
    }

    @Override
    public void removeShape(Shape shape) {
        if (History.size() >= 20) {
            History.remove(0);
        }
        ArrayList<Shape> t = new ArrayList<>();
        if (History.size() > 0) {
            try {
                copy(t,shape);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        //temp.remove(shape);
        History.add(++i,t);
    }

    @Override
    public void updateShape(Shape oldShape, Shape newShape) {
        if (History.size() >= 20) {
            History.remove(0);
        }
        ArrayList<Shape> t = new ArrayList<>();
        if (History.size() > 0) {
            try {
                copy(t,oldShape);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
//        newShape.getProperties().put("ID",(double) ID++);
        //((eg.edu.alexu.csd.oop.draw.cs04.Shape) newShape).setIndex(ID++);
        t.add(History.get(History.size()-1).indexOf(oldShape),newShape);
        //t.add(delete(((eg.edu.alexu.csd.oop.draw.cs04.Shape) oldShape).getIndex(),t),newShape);
        //t.remove(oldShape);
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
        i--;
        refresh(gc);
    }

    @Override
    public void redo() {
        i++;
        refresh(gc);
    }

    @Override
    public void save(String path) {

    }

    @Override
    public void load(String path) {

    }

    private int delete(int id, ArrayList<Shape> s){
        int x=0;
        for (Shape shape : s) {
            if(((eg.edu.alexu.csd.oop.draw.cs04.Shape)shape).getIndex()==id ){
                s.remove(shape);
                return x;
            }
            x++;
        }


        return -1;
    }

    private void copy(ArrayList<Shape> t,Shape delete) throws CloneNotSupportedException {
        ArrayList<Shape> o = History.get(i);
        for (Shape shape : o) {
            if(o!=shape) {
                Shape x = ((Shape) shape.clone());
                t.add(x);
            }
        }
    }
}
