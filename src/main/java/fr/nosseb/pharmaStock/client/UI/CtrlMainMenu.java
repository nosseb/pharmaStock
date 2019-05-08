package fr.nosseb.pharmaStock.client.UI;

import com.sun.javafx.application.LauncherImpl;
import fr.nosseb.pharmaStock.client.Launcher;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.Objects;


/**
 * Contrôleur de la page d'acceuil
 *
 * @author Loan Veyer
 * @date 26/03/2019
 *
 */
public class CtrlMainMenu {
    // Links to JavFX elements.
    @FXML private Button equipment1;
    @FXML private Button Location1;
    @FXML private Button ficheSortie1;

    /**
     * Launch window to view equipment.
     */
    @FXML public void pressEquipment() {
        CtrlEquipment ctrlEquipment = new CtrlEquipment();
        ctrlEquipment.caller();
    }

    /**
     * Launch window to view locations.
     */
    @FXML public void pressLocation(){
        Stage fenetrePrincipale = (Stage) Location1.getScene().getWindow();
        // OPTIMISATION: Can we close the window later on to reduce the black screen lag ?
        fenetrePrincipale.close();
        Stage primaryStage = new Stage();

        URL fxml = getClass().getResource("../../../../../fxml/Location.fxml");
        Parent root = null;
        try {
            root = FXMLLoader.load(fxml);
        } catch (IOException e) {
            // EXCEPTION: Internal resource not found, hard crash expected.
            e.printStackTrace();
        }

        // Check if root was properly assigned.
        if (root == null) {
            throw new NullPointerException("'root' cannot be null");
        }

        // FIXME : can we avoid the "Argument 'root' might be null" message ?
        Scene scene = new Scene(root);

        scene.getStylesheets().add(getClass().getResource("../../../../../application.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle("ModLocation");
        primaryStage.show();
        CtrlLocation.selectionner = false;
    }

    // DOCUMENTATION: Change non adapted word: "exit".
    /**
     * Launch window to fill "exit" forms
     */
    @FXML public void pressFicheSortie()  {
        Stage fenetrePrincipale = (Stage)ficheSortie1.getScene().getWindow();
        // OPTIMISATION: Can we close the window later on to reduce the black screen lag ?
        fenetrePrincipale.close();
        Stage primaryStage = new Stage();

        URL fxml = getClass().getResource("../../../../../fxml/FicheSortie.fxml");
        Parent root = null;
        try {
            root = FXMLLoader.load(fxml);
        } catch (IOException e) {
            // EXCEPTION: Internal resource not found, hard crash expected.
            e.printStackTrace();
        }

        // FIXME : can we avoid the "Argument 'root' might be null" message ?
        Scene scene = new Scene(root);

        scene.getStylesheets().add(getClass().getResource("../../../../../application.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Fiche de sortie");
        primaryStage.show();
    }

    public void caller() {
        Parent parent = null;
        Scene scene;
        URL fxml = Launcher.class.getClassLoader().getResource("fxml/MainMenu.fxml");
        try {
            assert fxml != null;
            parent = FXMLLoader.load(fxml);
        } catch (IOException e) {
            // EXCEPTION: Internal resource not found, crash expected.
            e.printStackTrace();
        }
        assert parent != null;
        scene = new Scene(parent);
        System.out.println(Launcher.class.getClassLoader().getResource("css/application.css"));
        scene.getStylesheets().add(Objects.requireNonNull(Launcher.class.getClassLoader().getResource("css/application.css")).toExternalForm());
        Launcher.commonStage.setScene(scene);
        // TODO: Localisation: Use resource instead of hardcoded String
        Launcher.commonStage.setTitle("Menu Principal");
        Launcher.commonStage.show();
    }
}
