package se.iths.java21.controller;

import javafx.scene.paint.Color;
import se.iths.java21.Model;
import se.iths.java21.Svg;
import se.iths.java21.shape.Circle;
import se.iths.java21.shape.Rectangle;
import se.iths.java21.shape.Shape;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.application.Platform;
import javafx.scene.input.MouseEvent;

import java.io.File;

public class PaintController {
    @FXML
    private CheckBox selectMode;
    @FXML
    private Canvas canvas;
    @FXML
    private ColorPicker colorPicker;


    @FXML
    private TextField xTextField;
    @FXML
    private TextField yTextField;
    @FXML
    private Label xLabel;
    @FXML
    private Label yLabel;

    Model model;

    public void initialize() {
        model = new Model();

        colorPicker.valueProperty().bindBidirectional(model.colorProperty());
        xTextField.textProperty().bindBidirectional(model.widthProperty());
        yTextField.textProperty().bindBidirectional(model.heightProperty());
        yLabel.visibleProperty().bindBidirectional(model.yLabelProperty());
        yTextField.visibleProperty().bindBidirectional(model.yTextFieldProperty());
        xLabel.textProperty().bindBidirectional(model.xLabelProperty());
        selectMode.selectedProperty().bindBidirectional(model.selectModeProperty());


        /* Allting sker via en listener som är hela denna koden där vad som än sker
         hos användaren upptäcks utav Controller som då skickar iväg till fxml filen.*/
    }

    public void onSave() {

        Svg.saveSvg(model);


        try {
            Image snapshot = canvas.snapshot(null, null);

            ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", new File("paint.png"));

        } catch (Exception e) {
            System.out.println("Failed to save image: " + e);
        }
    }

    public void onExit() {
        Platform.exit();
    }


    public void circleSelected() {
        model.circleSelectedProperty().setValue(true);
        model.rectangleSelectedProperty().setValue(false);

        model.setyLabel(false);
        model.setyTextField(false);
        model.setxLabel("Radius");

    }

    public void rectangleSelected() {

        model.circleSelectedProperty().setValue(false);
        model.rectangleSelectedProperty().setValue(true);

        model.setyLabel(true);
        model.setyTextField(true);
        model.setxLabel("width");


    }

    public void handleCanvasClick(MouseEvent mouseEvent) {

        double x = mouseEvent.getX();
        double y = mouseEvent.getY();

        if (model.isSelectMode()) {

            for (Shape shape : model.getShapes()) {
                if (shape.isInside(x, y)) {
                    shape.setBorderColor(Color.RED);
                    model.getSelectedShapes().add(shape);
                }
            }

        } else {
            model.addShapeAt(x, y);
        }
        executeDraw();


    }

    private void executeDraw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (Shape shape : model.getShapes()) {

            shape.draw(gc);
        }


    }

    public void deleteShape() {
        model.addToUnDoDeque();
        model.deleteShape();
        executeDraw();


    }

    public void setSize() {
        model.addToUnDoDeque();
        model.alterSizeOnSelectMode();
        executeDraw();


    }

    public void setColor() {
        model.addToUnDoDeque();
        model.alterColorOnSelectedMode();
        executeDraw();


    }


    public void undoShape() {
        model.unDo();
        executeDraw();


    }
}





