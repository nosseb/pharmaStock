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
    public static Holder sceneGenerator(String fxmlPath) {
        Holder holder = new Holder();
        Parent parent = null;
        URL fxml = Launcher.class.getClassLoader().getResource(fxmlPath);
        holder.fxmlLoader = new FXMLLoader(fxml);
        try {
            parent = holder.fxmlLoader.load();
        } catch (IOException e) {
            // EXCEPTION: Internal resource not found, crash expected.
            e.printStackTrace();
        }
        assert parent != null;
        holder.scene = new Scene(parent);
        holder.scene.getStylesheets().add(Objects.requireNonNull(Launcher.class.getClassLoader().getResource("css/application.css")).toExternalForm());

        return holder;
    }

    public static class Holder {
        public Scene scene;
        public FXMLLoader fxmlLoader;
    }
}
