package eg.edu.alexu.csd.oop.draw.cs04_06;

import eg.edu.alexu.csd.oop.draw.Shape;
import javafx.scene.paint.Color;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class XML {
    //    public static void main(String[] args) {
//        writeXML();
//        readXML();
//    }
    public void writeXML(String path, ArrayList<Shape> shapes) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            //App element
            Element root = document.createElement("DrawOOP");
            document.appendChild(root);

            //Canvas element
            Element canvas = document.createElement("Canvas");
            root.appendChild(canvas);

            //WE PUT A LOOP ON SHAPES ARRAY HERE
            //Shapes element
            int ii = 0;
            for (Shape shape : shapes) {
                Element s = document.createElement("shape");
                canvas.appendChild(s);
                //String[] prob;
                //prob = new String[]{"upleftx", "uplefty", "downrightx", "downrighty", "t3x", "t3y"};
                /* set an attribute to shape element */
                /*  */
                String str = shape.getClass().getName();
                if (shape.getClass().getName() != null) {
                    String st = shape.getClass().getName();
                    s.setAttribute("name", shape.getClass().getName());
                }
                if(str.equals("eg.edu.alexu.csd.oop.draw.RoundRectangle")){
                    for(Map.Entry<String,Double> ss:shape.getProperties().entrySet()){
                        s.setAttribute(ss.getKey(),ss.getValue().toString());
                    }
                }else
                if (shape.getProperties() != null) {
                    s.setAttribute("upleftx", shape.getProperties().get("upleftx").toString());
                    s.setAttribute("uplefty", shape.getProperties().get("uplefty").toString());
                    s.setAttribute("downrightx", shape.getProperties().get("downrightx").toString());
                    s.setAttribute("downrighty", shape.getProperties().get("downrightx").toString());
                    if (shape.getProperties().get("t3x") != null) {
                        s.setAttribute("t3x", shape.getProperties().get("t3x").toString());
                        s.setAttribute("t3y", shape.getProperties().get("t3y").toString());
                    }
                }
                if (shape.getColor() != null ) {
                    if(shape.getColor() instanceof Color) {
                        s.setAttribute("R", ((Double) ((Color) shape.getColor()).getRed()).toString());
                        s.setAttribute("G", ((Double) ((Color) shape.getColor()).getGreen()).toString());
                        s.setAttribute("B", ((Double) ((Color) shape.getColor()).getBlue()).toString());
                        s.setAttribute("O", ((Double) ((Color) shape.getColor()).getOpacity()).toString());
                    }else{
                        s.setAttribute("R", ((Integer) ((java.awt.Color) shape.getColor()).getRed()).toString());
                        s.setAttribute("G", ((Integer) ((java.awt.Color) shape.getColor()).getGreen()).toString());
                        s.setAttribute("B", ((Integer) ((java.awt.Color) shape.getColor()).getBlue()).toString());
                        s.setAttribute("O", "1");
                    }
                }
                if (shape.getFillColor() != null && shape.getClass().getName()!="DummyShape") {
                    if(shape.getColor() instanceof Color) {
                        s.setAttribute("fR", ((Double) ((Color) shape.getFillColor()).getRed()).toString());
                        s.setAttribute("fG", ((Double) ((Color) shape.getFillColor()).getGreen()).toString());
                        s.setAttribute("fB", ((Double) ((Color) shape.getFillColor()).getBlue()).toString());
                        s.setAttribute("fO", ((Double) ((Color) shape.getFillColor()).getOpacity()).toString());
                    }else{
                        s.setAttribute("fR", ((Integer) ((java.awt.Color) shape.getFillColor()).getRed()).toString());
                        s.setAttribute("fG", ((Integer) ((java.awt.Color) shape.getFillColor()).getGreen()).toString());
                        s.setAttribute("fB", ((Integer) ((java.awt.Color) shape.getFillColor()).getBlue()).toString());
                        s.setAttribute("fO", "1");
                    }
                }
                ii++;
            }

            //saving the file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(path));
            transformer.transform(domSource, streamResult);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readXML(String path, ArrayList<Shape> shapes) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File(path));
            document.getDocumentElement().normalize();

            //get App
            System.out.println("Root element :" + document.getDocumentElement().getNodeName());

            //get Canvas
            NodeList nodeList = document.getElementsByTagName("shape");
            //WE PUT A LOOP ON SHAPES ARRAY HERE
            //Shapes element
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                Element e = ((Element) node);
                System.out.println(e.getAttribute("name"));
                if (e.getAttribute("name").equals("eg.edu.alexu.csd.oop.draw.cs04_06.Circle")) {
                    Circle c = new Circle();
//                    Double dd =new Double(5.0);
//                    dd.intValue();
//                   System.out.println(Double.parseDouble(d[3]));
                    c.setPosition(new Point(((int) Double.parseDouble(e.getAttribute("upleftx"))), ((int) Double.parseDouble(e.getAttribute("uplefty")))));
                    c.setColor(new Color(Double.parseDouble(e.getAttribute("R")),
                            Double.parseDouble(e.getAttribute("G")),
                            Double.parseDouble(e.getAttribute("B")),
                            Double.parseDouble(e.getAttribute("O"))));
                    c.setFillColor(new Color(Double.parseDouble(e.getAttribute("fR")),
                            Double.parseDouble(e.getAttribute("fG")),
                            Double.parseDouble(e.getAttribute("fB")),
                            Double.parseDouble(e.getAttribute("fO"))));
                    c.setUpleft(new Point((int) Double.parseDouble(e.getAttribute("upleftx")), (int) Double.parseDouble(e.getAttribute("uplefty"))));
                    c.setDownright(new Point((int) Double.parseDouble(e.getAttribute("downrightx")),
                            (int) Double.parseDouble(e.getAttribute("downrighty"))));
                    shapes.add(c);
                } else if (e.getAttribute("name").equals("eg.edu.alexu.csd.oop.draw.cs04_06.Ellipse")) {
                    Ellipse c = new Ellipse();
                    c.setPosition(new Point(((int) Double.parseDouble(e.getAttribute("upleftx"))), ((int) Double.parseDouble(e.getAttribute("uplefty")))));
                    c.setColor(new Color(Double.parseDouble(e.getAttribute("R")),
                            Double.parseDouble(e.getAttribute("G")),
                            Double.parseDouble(e.getAttribute("B")),
                            Double.parseDouble(e.getAttribute("O"))));
                    c.setFillColor(new Color(Double.parseDouble(e.getAttribute("fR")),
                            Double.parseDouble(e.getAttribute("fG")),
                            Double.parseDouble(e.getAttribute("fB")),
                            Double.parseDouble(e.getAttribute("fO"))));
                    c.setUpleft(new Point((int) Double.parseDouble(e.getAttribute("upleftx")), (int) Double.parseDouble(e.getAttribute("uplefty"))));
                    c.setDownright(new Point((int) Double.parseDouble(e.getAttribute("downrightx")),
                            (int) Double.parseDouble(e.getAttribute("downrighty"))));
                    shapes.add(c);
                } else if (e.getAttribute("name").equals("eg.edu.alexu.csd.oop.draw.cs04_06.LineSegment")) {
                    LineSegment c = new LineSegment();
                    c.setPosition(new Point(((int) Double.parseDouble(e.getAttribute("upleftx"))), ((int) Double.parseDouble(e.getAttribute("uplefty")))));
                    c.setColor(new Color(Double.parseDouble(e.getAttribute("R")),
                            Double.parseDouble(e.getAttribute("G")),
                            Double.parseDouble(e.getAttribute("B")),
                            Double.parseDouble(e.getAttribute("O"))));
                    c.setFillColor(new Color(Double.parseDouble(e.getAttribute("fR")),
                            Double.parseDouble(e.getAttribute("fG")),
                            Double.parseDouble(e.getAttribute("fB")),
                            Double.parseDouble(e.getAttribute("fO"))));
                    c.setP1(new Point((int) Double.parseDouble(e.getAttribute("upleftx")), (int) Double.parseDouble(e.getAttribute("uplefty"))));
                    c.setP2(new Point((int) Double.parseDouble(e.getAttribute("downrightx")),
                            (int) Double.parseDouble(e.getAttribute("downrighty"))));
                    shapes.add(c);
                } else if (e.getAttribute("name").equals("eg.edu.alexu.csd.oop.draw.cs04_06.Rectangle")) {
                    Rectangle c = new Rectangle();
                    c.setPosition(new Point(((int) Double.parseDouble(e.getAttribute("upleftx"))), ((int) Double.parseDouble(e.getAttribute("uplefty")))));
                    c.setColor(new Color(Double.parseDouble(e.getAttribute("R")),
                            Double.parseDouble(e.getAttribute("G")),
                            Double.parseDouble(e.getAttribute("B")),
                            Double.parseDouble(e.getAttribute("O"))));
                    c.setFillColor(new Color(Double.parseDouble(e.getAttribute("fR")),
                            Double.parseDouble(e.getAttribute("fG")),
                            Double.parseDouble(e.getAttribute("fB")),
                            Double.parseDouble(e.getAttribute("fO"))));
                    c.setUpleft(new Point((int) Double.parseDouble(e.getAttribute("upleftx")), (int) Double.parseDouble(e.getAttribute("uplefty"))));
                    c.setDownright(new Point((int) Double.parseDouble(e.getAttribute("downrightx")),
                            (int) Double.parseDouble(e.getAttribute("downrighty"))));
                    shapes.add(c);
                } else if (e.getAttribute("name").equals("eg.edu.alexu.csd.oop.draw.cs04_06.Square")) {
                    Square c = new Square();
                    c.setPosition(new Point(((int) Double.parseDouble(e.getAttribute("upleftx"))), ((int) Double.parseDouble(e.getAttribute("uplefty")))));
                    c.setColor(new Color(Double.parseDouble(e.getAttribute("R")),
                            Double.parseDouble(e.getAttribute("G")),
                            Double.parseDouble(e.getAttribute("B")),
                            Double.parseDouble(e.getAttribute("O"))));
                    c.setFillColor(new Color(Double.parseDouble(e.getAttribute("fR")),
                            Double.parseDouble(e.getAttribute("fG")),
                            Double.parseDouble(e.getAttribute("fB")),
                            Double.parseDouble(e.getAttribute("fO"))));
                    c.setUpleft(new Point((int) Double.parseDouble(e.getAttribute("upleftx")), (int) Double.parseDouble(e.getAttribute("uplefty"))));
                    c.setDownright(new Point((int) Double.parseDouble(e.getAttribute("downrightx")),
                            (int) Double.parseDouble(e.getAttribute("downrighty"))));
                    shapes.add(c);
                } else if (e.getAttribute("name").equals("eg.edu.alexu.csd.oop.draw.cs04_06.Triangle")) {
                    Triangle c = new Triangle();
                    c.setPosition(new Point(((int) Double.parseDouble(e.getAttribute("upleftx"))), ((int) Double.parseDouble(e.getAttribute("uplefty")))));
                    c.setColor(new Color(Double.parseDouble(e.getAttribute("R")),
                            Double.parseDouble(e.getAttribute("G")),
                            Double.parseDouble(e.getAttribute("B")),
                            Double.parseDouble(e.getAttribute("O"))));
                    c.setFillColor(new Color(Double.parseDouble(e.getAttribute("fR")),
                            Double.parseDouble(e.getAttribute("fG")),
                            Double.parseDouble(e.getAttribute("fB")),
                            Double.parseDouble(e.getAttribute("fO"))));
                    c.setP1(new Point((int) Double.parseDouble(e.getAttribute("upleftx")), (int) Double.parseDouble(e.getAttribute("uplefty"))));
                    c.setP2(new Point((int) Double.parseDouble(e.getAttribute("downrightx")),
                            (int) Double.parseDouble(e.getAttribute("downrighty"))));
                    c.setP3(new Point((int) Double.parseDouble(e.getAttribute("t3x")),
                            (int) Double.parseDouble(e.getAttribute("t3y"))));
                    shapes.add(c);
                }else{
                    Shape c = new MyShape();
                    shapes.add(c);
                }


//                System.out.println("Node Name :" + node.getNodeName());
//                System.out.println("Name: " + e.getAttribute("name"));
//                System.out.println("Pointx: " + e.getAttribute("Pointx"));
//                System.out.println("Pointy: " + e.getAttribute("Pointy"));
//                System.out.println("Position: " + e.getAttribute("Position"));
//                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}