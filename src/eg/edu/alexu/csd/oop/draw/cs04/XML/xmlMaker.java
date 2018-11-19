package eg.edu.alexu.csd.oop.draw.cs04.XML;

import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs04.Circle;
import eg.edu.alexu.csd.oop.draw.cs04.MyShape;
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
import java.io.File;
import java.util.ArrayList;
import java.util.Map;


public class xmlMaker {
    public static void writeXML(String path, ArrayList<Shape> shapes) {
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
            for (Shape shape : shapes) {
                Element s = document.createElement("shape1");
                canvas.appendChild(s);
                /* set an attribute to shape element */
                /*  */
                String str = shape.getClass().getName();
                s.setAttribute("name", str);
                for (Map.Entry<String, Double> entry : shape.getProperties().entrySet()) {
                    s.setAttribute(entry.getKey(), entry.getValue().toString());
                }
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

    public static void readXML(String path, ArrayList<Shape> shapes) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File(path));
            document.getDocumentElement().normalize();

            //get App
            System.out.println("Root element :" + document.getDocumentElement().getNodeName());

            //get Canvas
            NodeList nodeList = document.getElementsByTagName("shape1");

            //WE PUT A LOOP ON SHAPES ARRAY HERE
            //Shapes element
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                Element e = ((Element) node);

                if(e.getAttribute("name")=="Circle"){
                    Circle c = new Circle();
                }else if(e.getAttribute("name")=="Ellipse"){

                }else if(e.getAttribute("name")=="LineSeqment"){

                }else if(e.getAttribute("name")=="Rectangle"){

                }else if(e.getAttribute("name")=="Square"){

                }else if(e.getAttribute("name")=="Triangle"){

                }



//                System.out.println("Node Name :" + node.getNodeName());
//                System.out.println("Name: " + e.getAttribute("name"));
//                System.out.println("Pointx: " + e.getAttribute("Pointx"));
//                System.out.println("Pointy: " + e.getAttribute("Pointy"));
//                System.out.println("Position: " + e.getAttribute("Position"));
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
