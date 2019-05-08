package fr.nosseb.pharmaStock.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

/**
 * @author Robinson Besson
 * @since 0.1
 */
public class utils {
    /**
     * Generate a scene (load file and handle errors.
     * @param fxmlPath the path to the fxml file describing the scene.
     */
    public static Scene sceneGenerator(String fxmlPath) {
        Scene scene;

        Parent parent = null;
        URL fxml = Launcher.class.getClassLoader().getResource(fxmlPath);
        try {
            assert fxml != null;
            parent = FXMLLoader.load(fxml);
        } catch (IOException e) {
            // EXCEPTION: Internal resource not found, crash expected.
            e.printStackTrace();
        }
        assert parent != null;
        scene = new Scene(parent);
        scene.getStylesheets().add(Objects.requireNonNull(Launcher.class.getClassLoader().getResource("css/application.css")).toExternalForm());

        return scene;
    }
}
