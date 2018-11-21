package eg.edu.alexu.csd.oop.draw.cs04_06;


import eg.edu.alexu.csd.oop.draw.Shape;

import java.awt.*;
import java.io.*;
import java.lang.String;
import java.nio.file.Path;
import java.util.ArrayList;
import java.io.IOException;

import eg.edu.alexu.csd.oop.draw.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import sun.awt.resources.awt;

import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class json {
    ArrayList<Shape> shapes;


    json(ArrayList<Shape> s) {

        this.shapes = s;

    }

    //from each we take type then its proberties

    public void writeJson(String p) throws IOException {
        String[] prob;
        prob = new String[]{"upleftx", "uplefty", "downrightx", "downrighty", "t3x", "t3y"};
        FileWriter fileWriter = new FileWriter(p);
        fileWriter.write("{\"ShapesArray\":[\n");

        for (int ii = 0; ii < shapes.size(); ii++) {
            fileWriter.write("{\"Type\":\"");
            if(shapes.get(ii).getProperties() != null){
                if(shapes.get(ii).getClass().getName().equals("eg.edu.alexu.csd.oop.draw.RoundRectangle")){

                }else
                if (shapes.get(ii).getProperties().get("shapeNo").intValue() == 1) {
                    fileWriter.write("Circle");
                } else if (shapes.get(ii).getProperties().get("shapeNo").intValue() == 2) {
                    fileWriter.write("Ellipse");
                } else if (shapes.get(ii).getProperties().get("shapeNo").intValue() == 3) {
                    fileWriter.write("LineSegment");
                } else if (shapes.get(ii).getProperties().get("shapeNo").intValue() == 4) {
                    fileWriter.write("Rectangle");
                } else if (shapes.get(ii).getProperties().get("shapeNo").intValue() == 5) {
                    fileWriter.write("Square");
                } else if (shapes.get(ii).getProperties().get("shapeNo").intValue() == 6) {
                    fileWriter.write("Triangle");
                } else {
                    fileWriter.write("Shape");
                }
            }
            fileWriter.write("\",\"Properties\":[");

            //upleft.x  upleft.y downright.x downright.y t3.x  t3.y color  fillcolor
            if (shapes.get(ii).getProperties() != null) {

                for (int i = 0; i < shapes.get(ii).getProperties().size() - 1; i++) {
                    if (shapes.get(ii).getProperties().get(prob[i]) != null) {
                        fileWriter.write("\"" + shapes.get(ii).getProperties().get(prob[i]).toString() + "\",");
                    } else {
                        fileWriter.write("\"" + "null" + "\",");
                    }
                }
            }
//            Paint o = new Color();
//            Color.BLACK.getBlue();
            if(shapes.get(ii).getColor() instanceof Color) {
                    fileWriter.write("\"" + String.valueOf(((Color) shapes.get(ii).getColor()).getRed()) + ","
                        + String.valueOf(((Color) shapes.get(ii).getColor()).getGreen()) + ","
                        + String.valueOf(((Color) shapes.get(ii).getColor()).getBlue()) + ","
                        + String.valueOf(((Color) shapes.get(ii).getColor()).getOpacity()) + "\",");
            }else{
                if(shapes.get(ii).getColor()!=null) {
                    fileWriter.write("\"" + String.valueOf(((java.awt.Color) shapes.get(ii).getColor()).getRed()) + ","
                            + String.valueOf(((java.awt.Color) shapes.get(ii).getColor()).getGreen()) + ","
                            + String.valueOf(((java.awt.Color) shapes.get(ii).getColor()).getBlue()) + ","
                            + "1" + "\",");
                }
            }
            if(shapes.get(ii).getFillColor() instanceof Color) {
                fileWriter.write("\"" + String.valueOf(((Color) shapes.get(ii).getFillColor()).getRed()) + ","
                        + String.valueOf(((Color) shapes.get(ii).getFillColor()).getGreen()) + ","
                        + String.valueOf(((Color) shapes.get(ii).getFillColor()).getBlue()) + ","
                        + String.valueOf(((Color) shapes.get(ii).getFillColor()).getOpacity()) + "\"]}");
            }else {
                if(shapes.get(ii).getFillColor()!=null) {
                    fileWriter.write("\"" + String.valueOf(((java.awt.Color) shapes.get(ii).getFillColor()).getRed()) + ","
                            + String.valueOf(((java.awt.Color) shapes.get(ii).getFillColor()).getGreen()) + ","
                            + String.valueOf(((java.awt.Color) shapes.get(ii).getFillColor()).getBlue()) + ","
                            + "1" + "\"]}");
                }
            }
            if (ii != shapes.size() - 1) {
                fileWriter.write(",\n");
            }

            if(shapes.get(ii).getClass().getName().equals("eg.edu.alexu.csd.oop.draw.RoundRectangle")){
                continue;
            }
        }
        fileWriter.write("]}");

        fileWriter.flush();
    }

    /*
    String pattern1 = "^-?\\d+\\.?\\d+[-+*\\/]-?\\d+\\.?\\d+$";
        String pattern2 = "^-?\\d+[-+*\\/]-?\\d+$";
        Pattern p1= Pattern.compile(pattern1);
        Pattern p2= Pattern.compile(pattern2);
        Matcher m1 =p1.matcher(s);
        Matcher m2 =p2.matcher(s);
        in=lines2.size();
        boolean b=m2.matches();
        if(m1.matches()||b){
     */

    public void readJson(String p) throws IOException {

        //FileReader fileReader=new FileReader(p);
        //fileReader.read

        BufferedReader br = new BufferedReader(new FileReader(new File(p)));
        String regex = "^(\\{\\\"Type\\\"\\:\\\"(.*?)\\\"\\,\\\"Properties\\\"\\:\\[\\\"(.*?)\\\"\\,\\\"(.*?)\\\"\\,\\\"(.*?)\\\"\\,\\\"(.*?)\\\"\\,\\\"(.*?)\\\"\\,\\\"(.*?)\\\"\\,\\\"(.*?)\\\"\\,\\\"(.*?)\\\"\\]\\}){1},?";
        String regex2= "(.*),(.*),(.*),(.*)";
        String s;
        s = br.readLine();
        while (s!=null) {
            if(!s.isEmpty()) {
                s = br.readLine();
                if (s == null) {
                    continue;
                }
                if (s.equals("{\"Type\":\"\",\"Properties\":[]}")) {
                    shapes.add(new MyShape());
                    continue;
                }
                Pattern pattern1 = Pattern.compile(regex);
                Pattern pattern2 = Pattern.compile(regex2);
                Matcher matcher = pattern1.matcher(s);
                if (matcher.find()) {
                    String[] d = new String[12];
                    String[] d2 = new String[4];
                    String[] d3 = new String[4];
                    for (int i = 2; i < 11; i++) {

                        d[i] = matcher.group(i);
                    }
                    Matcher matcher2 = pattern2.matcher(d[9]);
                    Matcher matcher3 = pattern2.matcher(d[10]);
                    if (matcher2.find() && matcher3.find()) {
                        for (int i = 0; i < 4; i++) {
                            d2[i] = matcher2.group(i + 1);
                            d3[i] = matcher3.group(i + 1);
                        }
                    }

                    if (d[2].equals("Circle")) {
                        Circle c = new Circle();
//                    Double dd =new Double(5.0);
//                    dd.intValue();
//                   System.out.println(Double.parseDouble(d[3]));
                        c.setPosition(new Point(((int) Double.parseDouble(d[3])), ((int) Double.parseDouble(d[4]))));
                        c.setColor(new Color(Double.parseDouble(d2[0]),
                                Double.parseDouble(d2[1]),
                                Double.parseDouble(d2[2]),
                                Double.parseDouble(d[3])));
                        c.setFillColor(new Color(Double.parseDouble(d3[0]),
                                Double.parseDouble(d3[1]),
                                Double.parseDouble(d3[2]),
                                Double.parseDouble(d3[3])));
                        c.setUpleft(new Point((int) Double.parseDouble(d[3]), (int) Double.parseDouble(d[4])));
                        c.setDownright(new Point((int) Double.parseDouble(d[5]), (int) Double.parseDouble(d[6])));
                        this.shapes.add(c);
                    } else if (d[2].equals("Ellipse")) {
                        Ellipse c = new Ellipse();
                        c.setPosition(new Point((int) Double.parseDouble(d[3]), (int) Double.parseDouble(d[4])));
                        c.setColor(new Color(Double.parseDouble(d2[0]),
                                Double.parseDouble(d2[1]),
                                Double.parseDouble(d2[2]),
                                Double.parseDouble(d[3])));
                        c.setFillColor(new Color(Double.parseDouble(d3[0]),
                                Double.parseDouble(d3[1]),
                                Double.parseDouble(d3[2]),
                                Double.parseDouble(d3[3])));
                        c.setUpleft(new Point((int) Double.parseDouble(d[3]), (int) Double.parseDouble(d[4])));
                        c.setDownright(new Point((int) Double.parseDouble(d[5]), (int) Double.parseDouble(d[6])));
                        this.shapes.add(c);
                    } else if (d[2].equals("LineSegment")) {
                        LineSegment c = new LineSegment();
                        c.setPosition(new Point((int) Double.parseDouble(d[3]), (int) Double.parseDouble(d[4])));
                        c.setColor(new Color(Double.parseDouble(d2[0]),
                                Double.parseDouble(d2[1]),
                                Double.parseDouble(d2[2]),
                                Double.parseDouble(d2[3])));
                        c.setFillColor(new Color(Double.parseDouble(d3[0]),
                                Double.parseDouble(d3[1]),
                                Double.parseDouble(d3[2]),
                                Double.parseDouble(d3[3])));
                        c.setP1(new Point((int) Double.parseDouble(d[3]), (int) Double.parseDouble(d[4])));
                        c.setP2(new Point((int) Double.parseDouble(d[5]), (int) Double.parseDouble(d[6])));
                        this.shapes.add(c);
                    } else if (d[2].equals("Rectangle")) {
                        Rectangle c = new Rectangle();
                        c.setPosition(new Point((int) Double.parseDouble(d[3]), (int) Double.parseDouble(d[4])));
                        c.setColor(new Color(Double.parseDouble(d2[0]),
                                Double.parseDouble(d2[1]),
                                Double.parseDouble(d2[2]),
                                Double.parseDouble(d2[3])));
                        c.setFillColor(new Color(Double.parseDouble(d3[0]),
                                Double.parseDouble(d3[1]),
                                Double.parseDouble(d3[2]),
                                Double.parseDouble(d3[3])));
                        c.setUpleft(new Point((int) Double.parseDouble(d[3]), (int) Double.parseDouble(d[4])));
                        c.setDownright(new Point((int) Double.parseDouble(d[5]), (int) Double.parseDouble(d[6])));
                        this.shapes.add(c);
                    } else if (d[2].equals("Square")) {
                        Square c = new Square();
                        c.setPosition(new Point((int) Double.parseDouble(d[3]), (int) Double.parseDouble(d[4])));
                        c.setColor(new Color(Double.parseDouble(d2[0]),
                                Double.parseDouble(d2[1]),
                                Double.parseDouble(d2[2]),
                                Double.parseDouble(d2[3])));
                        c.setFillColor(new Color(Double.parseDouble(d3[0]),
                                Double.parseDouble(d3[1]),
                                Double.parseDouble(d3[2]),
                                Double.parseDouble(d3[3])));
                        c.setUpleft(new Point((int) Double.parseDouble(d[3]), (int) Double.parseDouble(d[4])));
                        c.setDownright(new Point((int) Double.parseDouble(d[5]), (int) Double.parseDouble(d[6])));
                        this.shapes.add(c);
                    } else if (d[2].equals("Triangle")) {
                        Triangle c = new Triangle();
                        c.setPosition(new Point((int) Double.parseDouble(d[3]), (int) Double.parseDouble(d[4])));
                        c.setColor(new Color(Double.parseDouble(d2[0]),
                                Double.parseDouble(d2[1]),
                                Double.parseDouble(d2[2]),
                                Double.parseDouble(d2[3])));
                        c.setFillColor(new Color(Double.parseDouble(d3[0]),
                                Double.parseDouble(d3[1]),
                                Double.parseDouble(d3[2]),
                                Double.parseDouble(d3[3])));
                        c.setP1(new Point((int) Double.parseDouble(d[3]), (int) Double.parseDouble(d[4])));
                        c.setP2(new Point((int) Double.parseDouble(d[5]), (int) Double.parseDouble(d[6])));
                        c.setP3(new Point((int) Double.parseDouble(d[7]), (int) Double.parseDouble(d[8])));
                        this.shapes.add(c);
                    } else {
                        shapes.add(new MyShape());
                    }
                }else{
                    Shape ss = new MyShape();
                    shapes.add(ss);
                }
            }
        }

    }

//    private double readouble(String s, int index) {
//        String s2 = new String();
//        double ans = 0;
//        for (int i = index + 1; i < s.length(); i++) {
//            if (s.charAt(i) != '\"') {
//                s2 = s2 + s.charAt(i);
//            } else {
//                ans = Double.parseDouble(s2);
//                return ans;
//            }
//        }
//        return ans;
//    }


}
