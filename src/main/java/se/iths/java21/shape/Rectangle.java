package se.iths.java21.shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(double x, double y, Color color, double width, double height) {
        super(x, y, color);
        this.width = width;
        this.height = height;
    }

    public Rectangle(Shape shape) {
        super(shape);
        this.width = ((Rectangle) shape).getWidth();
        this.height = ((Rectangle) shape).getHeight();
    }

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(getBorderColor());
        graphicsContext.fillRect(getX() - (getWidth() / 2) - 2.5, getY() - (getHeight() / 2) - 2.5, getWidth() + 5, getHeight() + 5);

        graphicsContext.setFill(getColor());
        graphicsContext.fillRect(getX() - (getWidth() / 2), getY() - (getHeight() / 2), getWidth(), getHeight());
    }

    @Override
    public boolean isInside(double x, double y) {
        double leftX = getX() - getWidth();
        double topY = getY() - getHeight();


        return x >= leftX &&
                x <= leftX + getWidth() * 2 &&
                y >= topY &&
                y <= topY + getHeight() * 2;

    }

    @Override
    public Shape copyOf() {
        return new Rectangle(this);
    }

    @Override
    public String addToSvg() {
        String convertedColor = "#" + getColor().toString().substring(2, 10); // 0x000000ff JavaFX hexdecimal färg

        return "<rect x=\"" + getX() + "\" y=\"" + getY() + "\" width=\"" + getWidth() + "\" height=\"" + getHeight() + "\" fill=\"" + convertedColor + "\" />";
    }
}
