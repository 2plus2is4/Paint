package eg.edu.alexu.csd.oop.draw.cs04_06.GUI;

import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs04_06.*;
import eg.edu.alexu.csd.oop.draw.cs04_06.Rectangle;
import javafx.event.ActionEvent;
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


    @FXML void initialize(){
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
        Canvas canvas = new Canvas(700, 700);
        //engine = new MyDrawingEngine();
        engine.setGc(canvas.getGraphicsContext2D());
        graphicsContext = engine.getGc();
        //canvas.setStyle("-fx-background-color: white");
        pane.getChildren().add(canvas);
        //drawShapes(engine.getGc());
        stage = stage1;
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

    public void touchCanvas(MouseEvent event){
        if(shapeSelected){

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
        engine.refresh(engine.getGc());

    }

    public void un(ActionEvent e){
        engine.undo();
        //begin(stage);
        engine.refresh(graphicsContext);
    }

    public void re(ActionEvent e){
        engine.redo();
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
        if(currentShape.equals("Circle")){
            Circle c = new Circle();
            //System.out.println(Integer.parseInt(x1.getText()));
            c.setDownright(new Point(Integer.parseInt(x1.getText()),Integer.parseInt(y1.getText())));
            c.setUpleft(new Point(Integer.parseInt(x2.getText()),Integer.parseInt(y2.getText())));
            c.setColor(stroke.getValue());
            c.setFillColor(fill.getValue());
            engine.addShape(c);
            c.draw(engine.getGc());
        }else if(currentShape.equals("Ellipse")){
            Ellipse c = new Ellipse();
            c.setUpleft(new Point(Integer.parseInt(x2.getText()),Integer.parseInt(y2.getText())));
            c.setDownright(new Point(Integer.parseInt(x1.getText()),Integer.parseInt(y1.getText())));
            c.setColor(stroke.getValue());
            c.setFillColor(fill.getValue());
            engine.addShape(c);
            c.draw(engine.getGc());
        }else if(currentShape.equals("Line")){
            LineSegment c = new LineSegment();
            c.setP1(new Point(Integer.parseInt(x1.getText()),Integer.parseInt(y1.getText())));
            c.setP2(new Point(Integer.parseInt(x2.getText()),Integer.parseInt(y2.getText())));
            c.setColor(stroke.getValue());
            c.setFillColor(fill.getValue());
            engine.addShape(c);
            c.draw(engine.getGc());
        }else if(currentShape.equals("Rectangle")){
            Rectangle c = new Rectangle();
            c.setUpleft(new Point(Integer.parseInt(x1.getText()),Integer.parseInt(y1.getText())));
            c.setDownright(new Point(Integer.parseInt(x2.getText()),Integer.parseInt(y2.getText())));
            c.setColor(stroke.getValue());
            c.setFillColor(fill.getValue());
            engine.addShape(c);
            c.draw(engine.getGc());
        }else if(currentShape.equals("Square")){
            Square c = new Square();
            c.setUpleft(new Point(Integer.parseInt(x1.getText()),Integer.parseInt(y1.getText())));
            c.setDownright(new Point(Integer.parseInt(x2.getText()),Integer.parseInt(y2.getText())));
            c.setColor(stroke.getValue());
            c.setFillColor(fill.getValue());
            engine.addShape(c);
            c.draw(engine.getGc());
        }else if(currentShape.equals("Triangle")){
            Triangle c = new Triangle();
            c.setP1(new Point(Integer.parseInt(x1.getText()),Integer.parseInt(y1.getText())));
            c.setP2(new Point(Integer.parseInt(x2.getText()),Integer.parseInt(y2.getText())));
            c.setP3(new Point(Integer.parseInt(x3.getText()),Integer.parseInt(y3.getText())));
            c.setColor(stroke.getValue());
            c.setFillColor(fill.getValue());
            engine.addShape(c);
            c.draw(engine.getGc());
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

    public void ed(ActionEvent event){
        shapeupdate = true;
        draw.setText("update");
    }

    private void show(){
    }


}
