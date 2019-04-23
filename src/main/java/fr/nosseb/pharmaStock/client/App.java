package fr.nosseb.pharmaStock.client;

import fr.nosseb.pharmaStock.DB.DataBase;
import fr.nosseb.pharmaStock.client.UI.TextInputBox;
import fr.nosseb.pharmaStock.settings.Manager;
import javafx.application.Application;
import javafx.stage.Stage;


/**
 * @author Robinson Besson
 * @version 0.1
 * @since 0.1
 */
public class App extends Application {
    private static DataBase dataBase;


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        boolean validSettings = Manager.loadSettings(App.class, false);

        if (!validSettings) {
            if (!(Manager.checkDb_path() && Manager.checkDb_version())) {
                String path;

                path = TextInputBox.display("Chemin d'accès", "Chemin d'accès vers les fichiers du logiciel :");

                Manager.setDb_path(path);
                dataBase.build(Manager.getDb_path());
            }
        } else {
            dataBase.connect(Manager.getDb_path());
        }
    }

}
