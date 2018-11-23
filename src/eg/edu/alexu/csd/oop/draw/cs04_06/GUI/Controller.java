package eg.edu.alexu.csd.oop.draw.cs04_06.GUI;

import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs04_06.*;
import eg.edu.alexu.csd.oop.draw.cs04_06.Rectangle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.awt.*;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    @FXML
    Button c;
    @FXML
    Button e;
    @FXML
    Button r;
    @FXML
    Button s;
    @FXML
    Button t;
    @FXML
    Button l;
    @FXML
    Button safe;
    @FXML
    Button loat;
    @FXML
    Button undo;
    @FXML
    Button redo;
    @FXML
    Button nxt;
    @FXML
    Button prev;
    @FXML
    Button edit;
    @FXML
    Button add;
    @FXML
    Label label;
    @FXML
    Label status;
    @FXML
    Label csitr;
    @FXML
    ColorPicker fill;
    @FXML
    ColorPicker stroke;
    @FXML
    Pane pane;
    @FXML
    TextField x1;
    @FXML
    TextField y1;
    @FXML
    TextField x2;
    @FXML
    TextField x3;
    @FXML
    TextField y2;
    @FXML
    TextField y3;
    @FXML
    Button draw;
    @FXML
    HBox box1;
    @FXML
    HBox box2;
    @FXML
    HBox box3;
    @FXML Label p1;
    @FXML Label p2;
    @FXML Label p3;

    String currentShape;
    MyDrawingEngine engine = new MyDrawingEngine();
    GraphicsContext graphicsContext;
    boolean shapeSelected = false;
    boolean shapeupdate = false;
    Canvas canvas;
    Stage stage;
    Point fclick;
    Point sclick;
    Point tclick;
    boolean dragged;
    int triii=0;
    int csIterator;

    @FXML void initialize(){
        stroke.setValue(Color.BLACK);
//        engine = new MyDrawingEngine();
//        graphicsContext = engine.getGc();
//        canvas = new Canvas(500,500);
//        graphicsContext = canvas.getGraphicsContext2D();
//        //canvas.setStyle("-fx-background-color: white");
//        pane.getChildren().add(canvas);
//        Circle c = new Circle();
//        //System.out.println(Integer.parseInt(x1.getText()));
//        //c.setUpleft(new Point(Integer.parseInt(x1.getText()),Integer.parseInt(y1.getText())));
//        //c.setDownright(new Point(Integer.parseInt(x2.getText()),Integer.parseInt(y2.getText())));
//        c.setUpleft(new Point(20,20));
//        c.setDownright(new Point(120,120));
//        c.setColor(Color.BLACK);
//        c.setFillColor(Color.BLACK);
//        c.draw(graphicsContext);

        //canvas.setVisible(true);
        //canvas.setDisable(false);
//        canvas.setOnMouseClicked(event -> {
//            System.out.println("WTF");
//        });
    }

    public void begin(Stage stage1){
        stroke.getCustomColors();
        Canvas canvas = new Canvas(700, 700);
        //engine = new MyDrawingEngine();
        engine.setGc(canvas.getGraphicsContext2D());
        graphicsContext = engine.getGc();
        //canvas.setStyle("-fx-background-color: white");
        if(!pane.getChildren().isEmpty())
            pane.getChildren().remove(0);
        pane.getChildren().add(0,canvas);
        engine.refresh(engine.getGc());
        //drawShapes(engine.getGc());
        stage = stage1;
    }

    public void touchCanvas(MouseEvent event){
        //label.setText(event.getEventType().getName());
        String op = event.getEventType().getName();
        System.out.println(op);
        if(shapeSelected && !currentShape.equals("Triangle")){
            if(op.equals("MOUSE_PRESSED")){
                fclick = new Point(((int) event.getX()), ((int) event.getY()));
                x1.setText(((Integer) fclick.x).toString());
                y1.setText(((Integer) fclick.y).toString());
            }
            if(op.equals("MOUSE_DRAGGED")){
                dragged = true;
                sclick = new Point(((int) event.getX()), ((int) event.getY()));
            }
            if(op.equals("MOUSE_RELEASED") && dragged){
                ActionEvent event1 = new ActionEvent();
                dragged = false;
                begin(stage);
                x2.setText(((Integer) sclick.x).toString());
                y2.setText(((Integer) sclick.y).toString());
                DrAW(event1);
            }else if(dragged){
                ActionEvent event1 = new ActionEvent();
                begin(stage);
                x2.setText(((Integer) sclick.x).toString());
                y2.setText(((Integer) sclick.y).toString());
                DrAW(event1);
            }
        }else if(shapeSelected && currentShape.equals("Triangle")){
            if(op.equals("MOUSE_PRESSED")){
                triii++;
            }
            if(triii==1){
                fclick = new Point(((int) event.getX()), ((int) event.getY()));
                x1.setText(((Integer) fclick.x).toString());
                y1.setText(((Integer) fclick.y).toString());
            }else if(triii==2){
                sclick = new Point(((int) event.getX()), ((int) event.getY()));
                x2.setText(((Integer) sclick.x).toString());
                y2.setText(((Integer) sclick.y).toString());
            }else if(triii==3){
                triii=0;
                tclick = new Point(((int) event.getX()), ((int) event.getY()));
                x3.setText(((Integer) tclick.x).toString());
                y3.setText(((Integer) tclick.y).toString());
                ActionEvent event1 = new ActionEvent();
                DrAW(event1);
            }
        }
    }

    public void save(ActionEvent event){
        FileChooser fc =new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON File","*.JSON"));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML File","*.XML"));
        File f = fc.showSaveDialog(stage);
        if(f!=null) {
            engine.save(f.getAbsolutePath());
        }
    }

    public void load(ActionEvent event){
        FileChooser fc =new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON File","*.JSON"));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML File","*.XML"));
        File f = fc.showOpenDialog(stage);
        if (f != null) {
            engine.load(f.getAbsolutePath());
        }
        begin(stage);
        engine.refresh(engine.getGc());

    }

    public void next(ActionEvent event){
        if(csIterator<engine.getShapes().length-1)
            csIterator++;
        csitr.setText(Integer.toString(csIterator));
    }

    public void previous(ActionEvent event){
        if(csIterator>0)
            csIterator--;
        csitr.setText(Integer.toString(csIterator));
    }

    public void ed(ActionEvent event){
        if(engine.getShapes().length>0) {
            if (shapeupdate) {
                edit.setText("Edit");
                draw.setText("draw");
                shapeupdate = false;
                draw.setDisable(true);
            } else {
                shapeupdate = true;
                edit.setText("draw");
                draw.setText("update");
                draw.setDisable(false);
                shapeSelected = true;
                csIterator=engine.getShapes().length - 1;
            }
        }
    }

    public void up(ActionEvent e){
        MyShape c= new MyShape();
//        try {
//            neo= ((MyShape) cs.clone());
//        } catch (CloneNotSupportedException e1) {
//            e1.printStackTrace();
//        }
//        neo.setColor(stroke.getValue());
//        neo.setFillColor(fill.getValue());
//        neo.setPosition(new Point(Integer.parseInt(x1.getText()),Integer.parseInt(y1.getText())));
//        Circle c;
        if(engine.getShapes()[csIterator].getProperties().get("shapeNo").intValue()==1){
            c = new Circle();
            //System.out.println(Integer.parseInt(x1.getText()));
            ((Circle)c).setDownright(new Point(Integer.parseInt(x2.getText()),Integer.parseInt(y2.getText())));
            ((Circle)c).setUpleft(new Point(Integer.parseInt(x1.getText()),Integer.parseInt(y1.getText())));
            c.setColor(stroke.getValue());
            c.setFillColor(fill.getValue());
//            engine.addShape(c);

        }else if(engine.getShapes()[csIterator].getProperties().get("shapeNo").intValue()==2){
            c = new Ellipse();
            ((Ellipse) c).setUpleft(new Point(Integer.parseInt(x1.getText()),Integer.parseInt(y1.getText())));
            ((Ellipse) c).setDownright(new Point(Integer.parseInt(x2.getText()),Integer.parseInt(y2.getText())));
            c.setColor(stroke.getValue());
            c.setFillColor(fill.getValue());
//            engine.addShape(c);

        }else if(engine.getShapes()[csIterator].getProperties().get("shapeNo").intValue()==3){
            c = new LineSegment();
            ((LineSegment) c).setP1(new Point(Integer.parseInt(x1.getText()),Integer.parseInt(y1.getText())));
            ((LineSegment) c).setP2(new Point(Integer.parseInt(x2.getText()),Integer.parseInt(y2.getText())));
            c.setColor(stroke.getValue());
            c.setFillColor(fill.getValue());
//            engine.addShape(c);

        }else if(engine.getShapes()[csIterator].getProperties().get("shapeNo").intValue()==4){
            c = new Rectangle();
            ((Rectangle) c).setUpleft(new Point(Integer.parseInt(x1.getText()),Integer.parseInt(y1.getText())));
            ((Rectangle) c).setDownright(new Point(Integer.parseInt(x2.getText()),Integer.parseInt(y2.getText())));
            c.setColor(stroke.getValue());
            c.setFillColor(fill.getValue());
//            engine.addShape(c);

        }else if(engine.getShapes()[csIterator].getProperties().get("shapeNo").intValue()==5){
            c = new Square();
            ((Square) c).setUpleft(new Point(Integer.parseInt(x1.getText()),Integer.parseInt(y1.getText())));
            ((Square) c).setDownright(new Point(Integer.parseInt(x2.getText()),Integer.parseInt(y2.getText())));
            c.setColor(stroke.getValue());
            c.setFillColor(fill.getValue());
//            engine.addShape(c);

        }else if(engine.getShapes()[csIterator].getProperties().get("shapeNo").intValue()==6){
            c = new Triangle();
            ((Triangle) c).setP1(new Point(Integer.parseInt(x1.getText()),Integer.parseInt(y1.getText())));
            ((Triangle) c).setP2(new Point(Integer.parseInt(x2.getText()),Integer.parseInt(y2.getText())));
            ((Triangle) c).setP3(new Point(Integer.parseInt(x3.getText()),Integer.parseInt(y3.getText())));
            c.setColor(stroke.getValue());
            c.setFillColor(fill.getValue());
//            engine.addShape(c);

        }
        engine.updateShape(engine.getShapes()[csIterator],c);
        engine.refresh(engine.getGc());
    }

    public void un(ActionEvent e){
        engine.undo();
        begin(stage);
        engine.refresh(graphicsContext);
    }

    public void re(ActionEvent e){
        engine.redo();
        begin(stage);
        engine.refresh(graphicsContext);
    }


    public void pressShape(ActionEvent event){
        shapeSelected = true;
        currentShape = ((Button) event.getSource()).getText();
        if(!currentShape.equals("Triangle")){
            p3.setVisible(false);
            box3.setVisible(false);
        }else{
            p3.setVisible(true);
            box3.setVisible(true);
        }
        status.setText("Drawing " + currentShape);
    }

    public void DrAW(ActionEvent event){
        MyShape c = new MyShape();
        if(currentShape.equals("Circle")){
            c = new Circle();
            //System.out.println(Integer.parseInt(x1.getText()));
            ((Circle)c).setDownright(new Point(Integer.parseInt(x2.getText()),Integer.parseInt(y2.getText())));
            ((Circle)c).setUpleft(new Point(Integer.parseInt(x1.getText()),Integer.parseInt(y1.getText())));
            c.setColor(stroke.getValue());
            c.setFillColor(fill.getValue());
//            engine.addShape(c);
            c.draw(engine.getGc());
        }else if(currentShape.equals("Ellipse")){
            c = new Ellipse();
            ((Ellipse) c).setUpleft(new Point(Integer.parseInt(x1.getText()),Integer.parseInt(y1.getText())));
            ((Ellipse) c).setDownright(new Point(Integer.parseInt(x2.getText()),Integer.parseInt(y2.getText())));
            c.setColor(stroke.getValue());
            c.setFillColor(fill.getValue());
//            engine.addShape(c);
            c.draw(engine.getGc());
        }else if(currentShape.equals("Line")){
            c = new LineSegment();
            ((LineSegment) c).setP1(new Point(Integer.parseInt(x1.getText()),Integer.parseInt(y1.getText())));
            ((LineSegment) c).setP2(new Point(Integer.parseInt(x2.getText()),Integer.parseInt(y2.getText())));
            c.setColor(stroke.getValue());
            c.setFillColor(fill.getValue());
//            engine.addShape(c);
            c.draw(engine.getGc());
        }else if(currentShape.equals("Rectangle")){
            c = new Rectangle();
            ((Rectangle) c).setUpleft(new Point(Integer.parseInt(x1.getText()),Integer.parseInt(y1.getText())));
            ((Rectangle) c).setDownright(new Point(Integer.parseInt(x2.getText()),Integer.parseInt(y2.getText())));
            c.setColor(stroke.getValue());
            c.setFillColor(fill.getValue());
//            engine.addShape(c);
            c.draw(engine.getGc());
        }else if(currentShape.equals("Square")){
            c = new Square();
            ((Square) c).setUpleft(new Point(Integer.parseInt(x1.getText()),Integer.parseInt(y1.getText())));
            ((Square) c).setDownright(new Point(Integer.parseInt(x2.getText()),Integer.parseInt(y2.getText())));
            c.setColor(stroke.getValue());
            c.setFillColor(fill.getValue());
//            engine.addShape(c);
            c.draw(engine.getGc());
        }else if(currentShape.equals("Triangle")){
            c = new Triangle();
            ((Triangle) c).setP1(new Point(Integer.parseInt(x1.getText()),Integer.parseInt(y1.getText())));
            ((Triangle) c).setP2(new Point(Integer.parseInt(x2.getText()),Integer.parseInt(y2.getText())));
            ((Triangle) c).setP3(new Point(Integer.parseInt(x3.getText()),Integer.parseInt(y3.getText())));
            c.setColor(stroke.getValue());
            c.setFillColor(fill.getValue());
//            engine.addShape(c);
            c.draw(engine.getGc());
        }
        if(!dragged){
            engine.addShape(c);
        }
        //engine.refresh(graphicsContext);
    }

    public void enterProperties(KeyEvent event){
        if(shapeSelected) {
            String empty = "";
            Pattern pattern = Pattern.compile("\\d+");
//            Matcher m = pattern.matcher(null);
//            m = pattern.matcher(x2.getText());

//        StringBuilder sb = new StringBuilder();
//        if(!((TextField) event.getSource()).getText().equals("")) {
//            for (char x : ((TextField) event.getSource()).getText().toCharArray()) {
//                if (Character.isDigit(x)) {
//                    sb.append(x);
//                }else if()
//            }
//            ((TextField) event.getSource()).setText(sb.toString());
//        }
//            if (!x1.getText().equals(empty) && !y1.getText().equals(empty)) {
                Matcher m1 = pattern.matcher(x1.getText());
                Matcher m2 = pattern.matcher(y1.getText());
                if (m1.find() && m2.find()) {
//                    if (!x2.getText().equals(empty) && !y2.getText().equals(empty)) {
                        m1 = pattern.matcher(x2.getText());
                        m2 = pattern.matcher(y2.getText());
                        if (m1.find() && m2.find()) {
                            if (status.getText().contains("Triangle")) {
//                                if (!x3.getText().equals(empty) && !y3.getText().equals(empty)) {
                                    m1 = pattern.matcher(x3.getText());
                                    m2 = pattern.matcher(y3.getText());
                                    if (m1.find() && m2.find()) {
                                        draw.setDisable(false);
                                    }
//                                }
                            } else {
                                draw.setDisable(false);
                            }
                        }
//                    }
                }
//            }
        }
    }

    public void setColor(ActionEvent e){
        graphicsContext.setStroke(stroke.getValue());
    }

    public void setFillColor(ActionEvent e){
        graphicsContext.setFill(fill.getValue());
    }



    private void show(){
    }

    private void drawShapes(GraphicsContext gc) {
        Rectangle c = new Rectangle(new Point(2,2),new Point(200,200));
        c.setUpleft(new Point(2,2));
        c.setDownright(new Point(200,200));
        c.setFillColor(javafx.scene.paint.Color.BLACK);
        c.setColor(javafx.scene.paint.Color.YELLOW);
        c.draw(gc);
        Circle s = new Circle(new Point(5,5),new Point(30,30));
        s.setColor(javafx.scene.paint.Color.YELLOW);
        s.setFillColor(javafx.scene.paint.Color.YELLOW);
        s.draw(gc);
        /*gc.setLineWidth(5);
        gc.strokeLine(40, 10, 10, 40);
        gc.fillOval(10, 60, 30, 30);
        gc.strokeOval(10, 60, 30, 30);
        gc.fillRoundRect(110, 60, 30, 30, 10, 10);
        gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
        gc.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
        gc.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
        gc.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
        gc.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
        gc.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
        gc.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);
        gc.fillPolygon(new double[]{10, 40, 10, 40},
                new double[]{210, 210, 240, 240}, 4);
        gc.strokePolygon(new double[]{60, 90, 60, 90},
                new double[]{210, 210, 240, 240}, 4);
        gc.strokePolyline(new double[]{110, 140, 110, 140},
                new double[]{210, 210, 240, 240}, 4);*/
    }

}
