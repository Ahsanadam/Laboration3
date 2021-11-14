package se.iths.java21;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import se.iths.java21.shape.Shape;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Svg {

    static FileChooser fileChooser = new FileChooser();

    public static void saveSvg(Model model) {
        setUpFileChooser();
        Path path = Path.of(fileChooser.showSaveDialog(new Stage()).getPath());

        List<String> svgString = new ArrayList<>();

        svgString.add(startOfSvgString());

        for (Shape shape : model.getShapes()) {
            svgString.add(shape.addToSvg());
        }

        svgString.add("</svg>");

        try {
            Files.write(path, svgString);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static String startOfSvgString() {
        return "<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" width=\"600.0\" height=\"600.0\">";


    }

    public static void setUpFileChooser() {
        fileChooser.setTitle("Save to SVG");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("SVG files", "*.svg"));


    }


}
