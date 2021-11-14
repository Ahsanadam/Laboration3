package se.iths.java21;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.iths.java21.shape.Circle;
import se.iths.java21.shape.Rectangle;
import se.iths.java21.shape.Shape;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    Model model = new Model();
    Circle circle1 = new Circle(10,20, Color.BLACK,40);
    Rectangle rectangle1 = new Rectangle(10,20,Color.RED,30,50);


    @Test
    void addShapeIntoShapeListThenAddListToUndoDequeThenExecuteUndoCheckIfUndoDequeIsEmptyShouldReturnZero() {
        model.shapes.add(circle1);
        model.addToUnDoDeque();
        model.unDo();
        var result = model.unDo.size();
        assertEquals(0,result,"Should return a size of zero");
    }

    @Test
    void addShapeToShapeListThenAddToSelectedShapeListThenExecuteDeleteShapeShouldReturnFalse(){
        model.shapes.add(rectangle1);
        model.selectedShapes.add(rectangle1);
        model.deleteShape();
        var result = model.shapes.contains(rectangle1);
        assertFalse(result, "Should return false");
    }
}