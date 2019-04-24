package fr.nosseb.pharmaStock.client.UI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import java.net.URL;


// DOCUMENTATION: change doc language.
/**
 * Contrôleur de la page d'acceuil
 *
 * @author Loan Veyer
 * @date 26/03/2019
 *
 */
public class ControleurMenuPrincipale{
    // CLEANUP: change language for entire class.

    // CLEANUP: wth is there a '1' at the end of that variable name ?
    @FXML
    private Button equipement1;

    // CLEANUP: wth is there a '1' at the end of that variable name ?
    @FXML
    private Button lieu1;

    // CLEANUP: wth is there a '1' at the end of that variable name ?
    @FXML
    private Button ficheSortie1;


    /**
     * Launch window to view equipment.
     */
    @FXML
    public void pressEquipement() {
        Stage fenetrePrincipale = (Stage)equipement1.getScene().getWindow();
        // OPTIMISATION: Can we close the window later on to reduce the black screen lag ?
        fenetrePrincipale.close();
        Stage primaryStage = new Stage();

        // CLEANUP: Use 'FXMLLoader.setLocation()' to set the location used to resolve relative path attribute values.
        URL fxml = getClass().getResource("../../../../../fxml/Equipement.fxml");
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
        primaryStage.setTitle("Equipement");
        primaryStage.show();
    }

    /**
     * Launch window to view locations.
     */
    @FXML
    public void pressLieu(){
        Stage fenetrePrincipale = (Stage)lieu1.getScene().getWindow();
        // OPTIMISATION: Can we close the window later on to reduce the black screen lag ?
        fenetrePrincipale.close();
        Stage primaryStage = new Stage();

        // CLEANUP: Use 'FXMLLoader.setLocation()' to set the location used to resolve relative path attribute values.
        URL fxml = getClass().getResource("../../../../../fxml/Lieu.fxml");
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
        primaryStage.setTitle("Lieu");
        primaryStage.show();
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

        // CLEANUP: Can we get rid of this or will it be activated later on ?
//        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Fiche de sortie");
        primaryStage.show();
    }
}
