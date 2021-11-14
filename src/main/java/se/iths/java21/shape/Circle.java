package se.iths.java21.shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Shape {
    private double radius;

    public Circle(double x, double y, Color color, double radius) {
        super(x, y, color);
        this.radius = radius;
    }

    public Circle(Shape shape) {
        super(shape);
        this.radius = ((Circle) shape).getRadius();
    }


    public double getRadius() {
        return this.radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(getBorderColor());
        graphicsContext.fillOval(getX() - (getRadius() / 2) - 2.5, getY() - (getRadius() / 2) - 2.5, getRadius() + 5, getRadius() + 5);

        graphicsContext.setFill(getColor());
        graphicsContext.fillOval(getX() - (getRadius() / 2), getY() - (getRadius() / 2), getRadius(), getRadius());
    }

    @Override
    public boolean isInside(double x, double y) {

        double distX = x - getX();
        double distY = y - getY();
        double distance = Math.sqrt((distX * distX) + (distY * distY));

        return distance <= getRadius();


    }

    @Override
    public Shape copyOf() {
        return new Circle(this);
    }

    @Override
    public String addToSvg() {
        String convertedColor = "#" + getColor().toString().substring(2, 10); // 0x000000ff JavaFX hexdecimal färg

        return "<circle cx=\"" + getX() + "\" cy=\"" + getY() + "\" r=\"" + getRadius() / 2 + "\" fill=\"" + convertedColor + "\" />";
    }


}
