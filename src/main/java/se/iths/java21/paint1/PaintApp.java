package se.iths.java21.paint1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PaintApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("paint.fxml"))));
        stage.setTitle("Paint App");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}