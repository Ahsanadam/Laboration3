module com.example.paint1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;
    requires javafx.swing;


    opens se.iths.java21.paint1 to javafx.fxml;
    exports se.iths.java21.paint1;
    exports se.iths.java21.controller;
    opens se.iths.java21.controller to javafx.fxml;
    exports se.iths.java21.shape;
    opens se.iths.java21.shape to javafx.fxml;
}