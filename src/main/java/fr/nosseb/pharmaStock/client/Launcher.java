package fr.nosseb.pharmaStock.client;

import fr.nosseb.pharmaStock.client.UI.CtrlMainMenu;
import fr.nosseb.pharmaStock.client.UI.CtrlTextInputBox;
import fr.nosseb.pharmaStock.DB.DataBase;
import fr.nosseb.pharmaStock.settings.Settings;

import javafx.application.Application;
import javafx.stage.Stage;


/**
 * @author Robinson Besson
 * @version 0.1
 * @since 0.1
 */
public class Launcher extends Application {
    static public Stage commonStage;

    /**
     * Main method, launch JavaFX.
     * @param args Launch arguments for the software.
     */
    public static void main(String[] args) {
        // Method inherited from the "Application" abstract class.
        launch(args);
    }


    /**
     * Main method, called by JavaFX.
     * @param primaryStage Stage provided by JavaFX.
     */
    @Override
    public void start(Stage primaryStage) {
        commonStage = primaryStage;

        // TODO: ONLINE: Set client to true.

        // Load and check settings
        boolean validSettings = Settings.loadSettings(Launcher.class, false);

        // Depending on settings validity, request user input.
        if (!validSettings) {
            if (!(Settings.checkDb_path() && Settings.checkDb_version())) {
                // DB has probably never been initialized.
                // Requesting user for correct path to DB.

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

                // TODO: Check if valid URL.

                // Save path
                Settings.setDb_path(path);


                // Init the Database
                DataBase.build(Settings.getDb_path());
            }

            // TODO: ONLINE: Check online configuration and request user input accordingly.

        } else {
            // Configuration is correct, attempt to connect.
            DataBase.connect(Settings.getDb_path());
        }

        // Call Main Menu
        CtrlMainMenu ctrlMainMenu = new CtrlMainMenu();
        ctrlMainMenu.caller();
    }
}
