package eg.edu.alexu.csd.oop.draw.cs04;

import eg.edu.alexu.csd.oop.draw.Shape;

public class Morph {
    private Shape shape;
    private char operation;
    private int index;
    private Shape sec;

    public Shape getSec() {
        return sec;
    }

    public void setSec(Shape sec) {
        this.sec = sec;
    }

    public Morph(Shape shape, char operation, int index) {
        this.shape = shape;
        this.operation = operation;
        this.index = index;
    }

    public Shape getShape() {
        return shape;
    }

    public char getOperation() {
        return operation;
    }

    public int getIndex() {
        return index;
    }
}
