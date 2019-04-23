package fr.nosseb.pharmaStock.client;

import fr.nosseb.pharmaStock.DB.DataBase;
import fr.nosseb.pharmaStock.client.UI.TextInputBox;
import fr.nosseb.pharmaStock.settings.Manager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;


/**
 * @author Robinson Besson
 * @version 0.1
 * @since 0.1
 */
public class Launcher extends Application {
    public static DataBase dataBase = new DataBase();


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        // TODO : set client to true
        boolean validSettings = Manager.loadSettings(Launcher.class, false);

        if (!validSettings) {
            if (!(Manager.checkDb_path() && Manager.checkDb_version())) {
                String path;

                path = TextInputBox.display("Chemin d'accès", "Chemin d'accès vers les fichiers du logiciel :");

                // TODO : Cleanup debug
                //System.out.println("Old path : " + path);


                switch (path.charAt(0)) {
                    case '~' :
                        path = System.getProperty("user.home") + path.substring(1);
                        break;

                    case '.' :
                        path = System.getProperty("user.dir") + path.substring(1);
                        break;
                }

                // TODO : Cleanup debug
                //System.out.println("New path : " + path);

                Manager.setDb_path(path);
                dataBase.build(Manager.getDb_path());

                Manager.setDb_version("0.1");
            }
        } else {
            dataBase.connect(Manager.getDb_path());
        }

        // TODO : request server URL

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../../../../fxml/MenuPrincipal.fxml"));
        } catch (java.io.IOException e) {e.printStackTrace();}
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("../../../../css/application.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Menu Principal");
        primaryStage.show();

        System.out.println("END Launcher");

    }

}
