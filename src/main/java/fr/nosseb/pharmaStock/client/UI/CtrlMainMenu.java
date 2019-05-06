package fr.nosseb.pharmaStock.client.UI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;


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
        // Terminate current stage (window).
        Stage currentStage = (Stage) equipment1.getScene().getWindow();
        // OPTIMISATION: Can we close the window later on to reduce the black screen lag ?
        currentStage.close();

        // Setup new stage
        Stage newStage = new Stage();
        URL fxml = getClass().getResource("../../../../../fxml/Equipment.fxml");
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

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("../../../../../css/application.css").toExternalForm());
        newStage.setScene(scene);
        // TODO: Localization: use resources instead of hardcoded string.
        newStage.setTitle("Équipement");
        newStage.show();
    }

    /**
     * Launch window to view locations.
     */
    @FXML
    public void pressLieu(){
        Stage fenetrePrincipale = (Stage) Location1.getScene().getWindow();
        // OPTIMISATION: Can we close the window later on to reduce the black screen lag ?
        fenetrePrincipale.close();
        Stage primaryStage = new Stage();

        // CLEANUP: Use 'FXMLLoader.setLocation()' to set the location used to resolve relative path attribute values.
        URL fxml = getClass().getResource("../../../../../fxml/ModLocation.fxml");
        Parent root = null;
        try {
            root = FXMLLoader.load(fxml);
        } catch (IOException e) {
            // EXCEPTION: Internal resource not found, hard crash expected.
            e.printStackTrace();
        }

        // FIXME : can we avoid the "Argument 'root' might be null" message ?
        Scene scene = new Scene(root);

        // CLEANUP: Can we get rid of this or will it be activated later on ?
//        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle("ModLocation");
        primaryStage.show();
        CtrlLocation.selectionner = false;
    }

    // DOCUMENTATION: Change non adapted word: "exit".
    /**
     * Launch window to fill "exit" forms
     */
    @FXML
    public void pressFicheSortie()  {
        Stage fenetrePrincipale = (Stage)ficheSortie1.getScene().getWindow();
        // OPTIMISATION: Can we close the window later on to reduce the black screen lag ?
        fenetrePrincipale.close();
        Stage primaryStage = new Stage();

        // CLEANUP: Use 'FXMLLoader.setLocation()' to set the location used to resolve relative path attribute values.
        URL fxml = getClass().getResource("../../../../../fxml/ModFicheSortie.fxml");
        Parent root = null;
        try {
            root = FXMLLoader.load(fxml);
        } catch (IOException e) {
            // EXCEPTION: Internal resource not found, hard crash expected.
            e.printStackTrace();
        }

        // FIXME : can we avoid the "Argument 'root' might be null" message ?
        Scene scene = new Scene(root);

        // CLEANUP: Can we get rid of this or will it be activated later on ?
//        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Fiche de sortie");
        primaryStage.show();
    }
}
