//package eg.edu.alexu.csd.oop.draw.cs04;
//
//import eg.edu.alexu.csd.oop.draw.Shape;
//import eg.edu.alexu.csd.oop.draw.cs04.*;
//import javafx.scene.canvas.GraphicsContext;
//
//import java.awt.Point;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//
//public class DrawingEngine implements eg.edu.alexu.csd.oop.draw.DrawingEngine {
//
//    private ArrayList<ArrayList<Shape>> History = new ArrayList<>();
//    private ArrayList<Shape> shapes = new ArrayList<>();
//    private GraphicsContext gc;
//    private int i = -1;
//    private int undoCounter = 0;
//    private int redoCounter = 0;
//
//    public GraphicsContext getGc() {
//        return gc;
//    }
//
//    public void setGc(GraphicsContext gc) {
//        this.gc = gc;
//    }
//
//    public DrawingEngine() {
//        /*
//        for (ArrayList<Shape> shapes : History) {
//            shapes = new ArrayList<>();
//        }
//        */
//    }
//
//    @Override
//    public void refresh(Object canvas) {
//        for (Shape x : shapes) {
//            x.draw(canvas);
//        }
//    }
//
//    @Override
//    public void addShape(Shape shape) {
//        delete();
//        if (History.size() == 0) {
//            ArrayList<Shape> x = new ArrayList<>(0);
//            i++;
//            History.add(x);
//        }
//
//        shapes.add(shape);
//        ArrayList<Shape> t = new ArrayList<>();
//        try {
//            copy(t);
//        } catch (Exception e) {
//            System.out.println("exception");
//        }
//        History.add(++i, t);
//    }
//
//    @Override
//    public void removeShape(Shape shape) {
//        delete();
//
//        shapes.remove(shape);
//        ArrayList<Shape> t = new ArrayList<>();
//        try {
//            copy(t);
//        } catch (Exception e) {
//            System.out.println("exception");
//        }
//        History.add(++i, t);
//    }
//
//    @Override
//    public void updateShape(Shape oldShape, Shape newShape) {
//        delete();
//
//        int ind = shapes.indexOf(oldShape);
//        shapes.add(ind, newShape);
//        shapes.remove(oldShape);
//        ArrayList<Shape> t = new ArrayList<>();
//        try {
//            copy(t);
//        } catch (Exception e) {
//            System.out.println("exception");
//        }
//        History.add(++i, t);
//    }
//
//    @Override
//    public Shape[] getShapes() {
//      Shape[] x = new Shape[History.get(i).size()];
//        return History.get(i).toArray(x);
//
//    }
//
//    @Override
//    public List<Class<? extends Shape>> getSupportedShapes() {
//        ArrayList<Class<? extends Shape>> temp = new ArrayList<>();
//        try {
//            temp.add(((Class<? extends Shape>) Class.forName("eg.edu.alexu.csd.oop.draw.cs04.Circle")));
//            temp.add(((Class<? extends Shape>) Class.forName("eg.edu.alexu.csd.oop.draw.cs04.Ellipse")));
//            temp.add(((Class<? extends Shape>) Class.forName("eg.edu.alexu.csd.oop.draw.cs04.LineSegment")));
//            temp.add(((Class<? extends Shape>) Class.forName("eg.edu.alexu.csd.oop.draw.cs04.Rectangle")));
//            temp.add(((Class<? extends Shape>) Class.forName("eg.edu.alexu.csd.oop.draw.cs04.Square")));
//            temp.add(((Class<? extends Shape>) Class.forName("eg.edu.alexu.csd.oop.draw.cs04.Triangle")));
//
//        } catch (Exception e) {
//
//        }
//
//
//        return temp;
//    }
//
//    @Override
//    public void undo() {
//        if (i>0) {
//            i--;
//        }
//    }
//
//    @Override
//    public void redo() {
//        if (i < History.size() - 1) {
//            i++;
//        }
//    }
//
//    @Override
//    public void save(String path) {
//        if(path.toLowerCase().contains(".json")){
//            json j = new json(this.History.get(History.size()-1));
//            try {
//                j.writeJson(path);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }else{
//            XML xml= new XML();
//        }
//
//    }
//
//    @Override
//    public void load(String path) {
//        ArrayList <Shape> dumm=new ArrayList<>();
//        if(path.toLowerCase().contains(".json")) {
//            json j = new json(dumm);
//            try {
//                j.readJson(path);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            History.add(dumm);
//        }else{
//            XML xml= new XML();
//        }
//    }
//
//    private void delete() {
//
//        if(i<History.size()-1 && i>-1) {
//            if((i<History.size()-1)|| (shapes.size()!=History.get(i).size())) {
//                ArrayList<Shape> t = new ArrayList<>();
//                for (int j = 1; j <= History.get(i).size(); j++) {
//                    t.add(shapes.get(j));
//                }
//                shapes = t;
//            }
//        }
//        /*
//
//*/
//        ArrayList<ArrayList<Shape>> h = new ArrayList<>();
//        for (int ii = 0; ii <= i; ii++) {
//            h.add(History.get(ii));
//        }
//        History = h;
//
//        if (History.size() >= 21) {
//            History.remove(0);
//                i--;
//        }
//    }
//
//    private void copy(ArrayList<Shape> t) throws CloneNotSupportedException {
//        for (Shape shape : shapes) {
//            Shape x = ((Shape) shape.clone());
//            t.add(x);
//        }
//    }
//
//}
package eg.edu.alexu.csd.oop.draw.cs04_06;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;
import javafx.scene.canvas.GraphicsContext;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MyDrawingEngine implements DrawingEngine {

    private ArrayList<ArrayList<Shape>> History = new ArrayList<>();
    private ArrayList<Shape> shapes = new ArrayList<>();
    private ArrayList<Morph> steps = new ArrayList<>();
    int stps = -1;
    private GraphicsContext gc;
    private int i = -1;
//    private int undoCounter = 0;
//    private int redoCounter = 0;

    public GraphicsContext getGc() {
        return gc;
    }

    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }

    public MyDrawingEngine() {
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
        if (History.size() == 0) {
            ArrayList<Shape> x = new ArrayList<>(0);
            i++;
            History.add(x);
        }
        shapes.add(shape);
        ArrayList<Shape> t = new ArrayList<>();
        try {
            copy(t);
        } catch (Exception e) {
            System.out.println("exception");
        }
        History.add(++i, t);
        steps.add(new Morph(shape, 'a', History.get(i).size()-1));
        stps++;
    }

    @Override
    public void removeShape(Shape shape) {
        delete();
        int x = shapes.indexOf(shape);
        History.get(i).remove(x);
        History.get(i).add(shape);
        shapes.remove(shape);
        ArrayList<Shape> t = new ArrayList<>();
        try {
            copy(t);
        } catch (Exception e) {
            System.out.println("exception");
        }
        History.add(++i, t);
        steps.add(new Morph(shape, 'r', x));
        stps++;
    }

    @Override
    public void updateShape(Shape oldShape, Shape newShape) {
        delete();
        int ind = shapes.indexOf(oldShape);
        shapes.add(ind, newShape);
        shapes.remove(oldShape);
        ArrayList<Shape> t = new ArrayList<>();
        try {
            copy(t);
        } catch (Exception e) {
            System.out.println("exception");
        }
        History.add(++i, t);
        Morph m = new Morph(newShape, 'u', History.get(i).size()-1);
        m.setSec(oldShape);
        steps.add(m);
        stps++;
    }

    @Override
    public Shape[] getShapes() {
//        Shape[] x = new Shape[History.get(i).size()];
//        return History.get(i).toArray(x);
        Shape[] x = new Shape[shapes.size()];
        return shapes.toArray(x);
    }

    @Override
    public List<Class<? extends Shape>> getSupportedShapes() {
        ArrayList<Class<? extends Shape>> temp = new ArrayList<>();
        try {
            temp.add(((Class<? extends Shape>) Class.forName("eg.edu.alexu.csd.oop.draw.cs04_06.Circle")));
            temp.add(((Class<? extends Shape>) Class.forName("eg.edu.alexu.csd.oop.draw.cs04_06.Ellipse")));
            temp.add(((Class<? extends Shape>) Class.forName("eg.edu.alexu.csd.oop.draw.cs04_06.LineSegment")));
            temp.add(((Class<? extends Shape>) Class.forName("eg.edu.alexu.csd.oop.draw.cs04_06.Rectangle")));
            temp.add(((Class<? extends Shape>) Class.forName("eg.edu.alexu.csd.oop.draw.cs04_06.Square")));
            temp.add(((Class<? extends Shape>) Class.forName("eg.edu.alexu.csd.oop.draw.cs04_06.Triangle")));
            temp.add(((Class<? extends Shape>) Class.forName("eg.edu.alexu.csd.oop.draw.RoundRectangle")));


        } catch (Exception e) {

        }


        return temp;
    }

    @Override
    public void undo() {
        if (i > 0) {
            i--;
            if (i < History.size() - 1 && i > -1) {
                if ((i < History.size() - 1)) {
                    //stps--;
                    Morph temp = steps.get(stps);
                    char c = temp.getOperation();
                    if(c=='a'){
                        shapes.remove(temp.getIndex());
                    }else if(c=='r'){
                        shapes.add(temp.getIndex(),temp.getShape());
                    }else{
                        shapes.remove(temp.getIndex());
                        shapes.add(temp.getIndex(),temp.getSec());
                    }
                    stps--;
//                ArrayList<Shape> t = new ArrayList<>();
//                if (History.get(i).size() > shapes.size()) {
//                    for (int j = shapes.size(); j < History.get(i).size(); j++) {
//                        shapes.add(History.get(i).get(j));
//
//                        try {
//                            History.get(i).add(j, ((Shape) History.get(i).get(j).clone()));
//                        } catch (CloneNotSupportedException e) {
//                            e.printStackTrace();
//                        }
//
//                        History.get(i).remove(j + 1);
//                    }
//                } else {
//                    for (int j = 1; j <= History.get(i).size(); j++) {
//                        t.add(shapes.get(j));
//                    }
//                    shapes = t;
//                }

                }
            }
        }

    }

    @Override
    public void redo() {
        if (i < History.size() - 1) {
            i++;
        }
        if (i <= History.size() - 1 && i > -1) {
            if ((i <= History.size() - 1)) {
                stps++;
                Morph temp = steps.get(stps);
                char c = temp.getOperation();
                if(c=='a'){
                    shapes.add(temp.getIndex(),temp.getShape());
                }else if(c=='r'){
                    shapes.remove(temp.getIndex());
                }else{
                    shapes.remove(temp.getIndex());
                    shapes.add(temp.getIndex(),temp.getShape());
                }
                //stps--;
//                ArrayList<Shape> t = new ArrayList<>();
//                if (History.get(i).size() > shapes.size()) {
//                    for (int j = shapes.size(); j < History.get(i).size(); j++) {
//                        shapes.add(History.get(i).get(j));
//
//                        try {
//                            History.get(i).add(j, ((Shape) History.get(i).get(j).clone()));
//                        } catch (CloneNotSupportedException e) {
//                            e.printStackTrace();
//                        }
//
//                        History.get(i).remove(j + 1);
//                    }
//                } else {
//                    for (int j = 1; j <= History.get(i).size(); j++) {
//                        t.add(shapes.get(j));
//                    }
//                    shapes = t;
//                }

            }
        }
    }

    @Override
    public void save(String path) {
        if(path.toLowerCase().contains(".json")){
            json j = new json(shapes);
            try {
                j.writeJson(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            XML xml= new XML();
            xml.writeXML(path,shapes);
        }

    }

    @Override
    public void load(String path) {
        ArrayList <Shape> dumm=new ArrayList<>();
        if(path.toLowerCase().contains(".json")) {
            json j = new json(dumm);
            try {
                j.readJson(path);
                steps = new ArrayList<>();
                stps=-1;
                i=0;
                History = new ArrayList<>();
                shapes = dumm;
                History.add(shapes);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //History.add(dumm);
        }else{
            XML xml= new XML();
            shapes = new ArrayList<>();
            xml.readXML(path,shapes);
            steps = new ArrayList<>();
            stps=-1;
            i=0;
            History = new ArrayList<>();
            History.add(shapes);
        }
    }

    private void delete() {

//        if (i < History.size() - 1 && i > -1) {
//            if ((i < History.size() - 1)) {
//                ArrayList<Shape> t = new ArrayList<>();
//                if (History.get(i).size() > shapes.size()) {
//                    for (int j = shapes.size(); j < History.get(i).size(); j++) {
//                        shapes.add(History.get(i).get(j));
//
//                        try {
//                            History.get(i).add(j, ((Shape) History.get(i).get(j).clone()));
//                        } catch (CloneNotSupportedException e) {
//                            e.printStackTrace();
//                        }
//
//                        History.get(i).remove(j + 1);
//                    }
//                } else {
//                    for (int j = 1; j <= History.get(i).size(); j++) {
//                        t.add(shapes.get(j));
//                    }
//                    shapes = t;
//                }
//
//            }
//        }
        /*
         */
        if(History.size()>0 && steps.size()>0) {
            ArrayList<ArrayList<Shape>> h = new ArrayList<>();
            ArrayList<Morph> s = new ArrayList<>();
            for (int ii = 0; ii <= i; ii++) {
                h.add(History.get(ii));

            }
            History = h;
            for (int ii = 0; ii <= stps; ii++) {
                s.add(steps.get(ii));
            }
            steps=s;

        }

        if (History.size() >= 21) {
            History.remove(0);
            i--;
        }
    }

    private void copy(ArrayList<Shape> t) throws CloneNotSupportedException {
        for (Shape shape : shapes) {
            Shape x = ((Shape) shape.clone());
            t.add(x);
        }
    }

}