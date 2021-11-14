package se.iths.java21;

import javafx.beans.property.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import se.iths.java21.shape.Circle;
import se.iths.java21.shape.Rectangle;
import se.iths.java21.shape.Shape;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Deque;

public class Model {

    List<Shape> shapes;
    List<Shape> selectedShapes;
    Deque<List<Shape>> unDo;
    BooleanProperty circleSelected;
    BooleanProperty rectangleSelected;
    ObjectProperty<Color> color;
    StringProperty width;
    StringProperty height;
    BooleanProperty yLabel;
    BooleanProperty yTextField;
    StringProperty xLabel;
    BooleanProperty selectMode;


    public Model() {
        shapes = new ArrayList<>();
        selectedShapes = new ArrayList<>();
        unDo = new ArrayDeque<>();
        circleSelected = new SimpleBooleanProperty(false);
        rectangleSelected = new SimpleBooleanProperty(true);
        color = new SimpleObjectProperty<>(Color.BLACK);
        width = new SimpleStringProperty("50");
        height = new SimpleStringProperty("25");
        yLabel = new SimpleBooleanProperty(true);
        yTextField = new SimpleBooleanProperty(true);
        xLabel = new SimpleStringProperty("width");
        selectMode = new SimpleBooleanProperty(false);

    }

    public Deque<List<Shape>> getUnDo() {
        return unDo;
    }

    public void setUnDo(Deque<List<Shape>> unDo) {
        this.unDo = unDo;
    }

    public void unDo(){

        if (unDo.isEmpty())
            return;

        shapes.clear();
        shapes.addAll(unDo.removeLast());
    }

    public void addShapeAt(double x, double y){
        addToUnDoDeque();
        if (isRectangleSelected()) {
            getShapes().add(new Rectangle(x, y, getColor(), Double.parseDouble(getWidth()), Double.parseDouble(getHeight())));
        } else {
            getShapes().add(new Circle(x, y, getColor(), Double.parseDouble(getWidth())));

        }


    }

    public List<Shape> getSelectedShapes() {
        return selectedShapes;
    }

    public void setSelectedShapes(List<Shape> selectedShapes) {
        this.selectedShapes = selectedShapes;
    }

    public boolean isSelectMode() {
        return selectMode.get();
    }

    public BooleanProperty selectModeProperty() {
        return selectMode;
    }

    public void setSelectMode(boolean selectMode) {
        this.selectMode.set(selectMode);
    }

    public boolean isyLabel() {
        return yLabel.get();
    }

    public BooleanProperty yLabelProperty() {
        return yLabel;
    }

    public void setyLabel(boolean yLabel) {
        this.yLabel.set(yLabel);
    }

    public boolean isyTextField() {
        return yTextField.get();
    }

    public BooleanProperty yTextFieldProperty() {
        return yTextField;
    }

    public void setyTextField(boolean yTextField) {
        this.yTextField.set(yTextField);
    }

    public String getxLabel() {
        return xLabel.get();
    }

    public StringProperty xLabelProperty() {
        return xLabel;
    }

    public void setxLabel(String xLabel) {
        this.xLabel.set(xLabel);
    }

    public String getWidth() {
        return width.get();
    }

    public StringProperty widthProperty() {
        return width;
    }

    public void setWidth(String width) {
        this.width.set(width);
    }

    public String getHeight() {
        return height.get();
    }

    public StringProperty heightProperty() {
        return height;
    }

    public void setHeight(String height) {
        this.height.set(height);
    }

    public Color getColor() {
        return color.get();
    }

    public ObjectProperty<Color> colorProperty() {
        return color;
    }

    public void setColor(Color color) {
        this.color.set(color);
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }

    public boolean isCircleSelected() {
        return circleSelected.get();
    }

    public BooleanProperty circleSelectedProperty() {
        return circleSelected;
    }

    public void setCircleSelected(boolean circleSelected) {
        this.circleSelected.set(circleSelected);
    }

    public boolean isRectangleSelected() {
        return rectangleSelected.get();
    }

    public BooleanProperty rectangleSelectedProperty() {
        return rectangleSelected;
    }

    public void setRectangleSelected(boolean rectangleSelected) {
        this.rectangleSelected.set(rectangleSelected);
    }

    public void alterSizeOnSelectMode() {
        for (Shape shape : selectedShapes) {

            if (shape.getClass() == Rectangle.class) {

                ((Rectangle) shape).setWidth(Double.parseDouble(getWidth()));
                ((Rectangle) shape).setHeight(Double.parseDouble(getHeight()));

            } else {
                ((Circle) shape).setRadius(Double.parseDouble(getWidth()));
            }


        }
    }

    public void alterColorOnSelectedMode() {
        for (Shape shape : selectedShapes) {

            shape.setColor(getColor());

        }


    }

    public void addToUnDoDeque() {

        List<Shape> tempList = new ArrayList<>();
        for (Shape shape : shapes) {
            tempList.add(shape.copyOf());
        }
        unDo.addLast(tempList);

    }


    public void deleteShape() {

        for (Shape shape : selectedShapes) {
            shapes.remove(shape);
        }

    }
}
