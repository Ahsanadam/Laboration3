package se.iths.java21.shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Shape {
    private double x;
    private double y;
    private Color color;
    private Color borderColor;


    public Shape(double x, double y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.borderColor = Color.TRANSPARENT;
    }

    public Shape(Shape shape) {
        this.x = shape.getX();
        this.y = shape.getY();
        this.color = shape.getColor();
        this.borderColor = Color.TRANSPARENT;

    }


    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract void draw(GraphicsContext graphicsContext);

    public abstract boolean isInside(double x, double y);

    public abstract Shape copyOf();

    public abstract String addToSvg();


}
