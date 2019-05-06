package fr.nosseb.pharmaStock.client;

import fr.nosseb.pharmaStock.client.UI.CtrlTextInputBox;
import fr.nosseb.pharmaStock.DB.DataBase;
import fr.nosseb.pharmaStock.settings.Settings;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;


/**
 * @author Robinson Besson
 * @version 0.1
 * @since 0.1
 */
public class Launcher extends Application {
    // Database, used thorough the software.
    public static DataBase dataBase = new DataBase();


    /**
     * Main method, launch JavaFX.
     * @param args Launch arguments for the software.
     */
    public static void main(String[] args) {
        launch(args);
    }


    /**
     * Main method, called by JavaFX.
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        // ONLINE: Set client to true.

        // Load and check settings
        boolean validSettings = Settings.loadSettings(Launcher.class, false);

        // Depending on settings validity, request user input.
        if (!validSettings) {
            if (!(Settings.checkDb_path() && Settings.checkDb_version())) {
                // DB has probably never been initialized.
                // Requesting user for correct path to DB.

                // TODO: Localisation: Use resource instead of hardcoded strings.
                String path = CtrlTextInputBox.display("Chemin d'accès", "Chemin d'accès vers les fichiers du logiciel :");

                // Specials directories alias.
                // FIXME: check if compatible with MS Windows & Mac OSX
                switch (path.charAt(0)) {
                    case '~' :
                        path = System.getProperty("user.home") + path.substring(1);
                        break;

                    case '.' :
                        path = System.getProperty("user.dir") + path.substring(1);
                        break;
                }

                // FIXME: Check if valid URL.

                // Save path
                Settings.setDb_path(path);

                // FIXME: check for .lck files

                // Init the Database
                DataBase.build(Settings.getDb_path());
            }

            // ONLINE: Check online configuration and request user input accordingly.

        } else {
            // Configuration is correct, attempt to connect.
            // FIXME: check for .lck files
            DataBase.connect(Settings.getDb_path());
            // TODO : Close DB ?
        }

        // DOCUMENTATION: Explain wth 'Parent root' is.
        Parent root = null;
        Scene scene;

        // Will allow for cleaner paths when importing resources.
        URL fxml = getClass().getResource("../../../../fxml/MainMenu.fxml");
        String css = getClass().getResource("../../../../css/application.css").toExternalForm();

        try {
            root = FXMLLoader.load(fxml);
        } catch (java.io.IOException e) {
            // EXCEPTION: Internal resource not found, crash expected.
            System.out.println("Internal resources not found (fxml file)!");
            e.printStackTrace();
        }

        // Check if root has been properly assigned.
        if (root == null) {
            // EXCEPTION: failed to assign 'root', crash expected.
            throw new NullPointerException("'root' cannot be null");
        }

        // Scene initialization
        scene = new Scene(root);
        scene.getStylesheets().add(css);

        // Stage configuration and display
        primaryStage.setScene(scene);
        // TODO: Localisation: Use resource instead of hardcoded String
        primaryStage.setTitle("Menu Principal");
        primaryStage.show();
    }
}
