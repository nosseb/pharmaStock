package fr.nosseb.pharmaStock.client.UI;

import fr.nosseb.pharmaStock.client.Launcher;
import fr.nosseb.pharmaStock.models.ModEquipment;
import fr.nosseb.pharmaStock.models.ModLocation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import java.net.URL;

import java.util.ResourceBundle;

/**
 *
 * @author Loan Veyer
 * @date 03/04/2019
 *
 */
public class CtrlEquipmentEdit implements Initializable {
    @FXML
    private TextField nom;

    @FXML
    private TextField description;

    @FXML
    private TextField numeroSerie;

    @FXML
    private Label lieu;

    @FXML
    private Button selectLieu1;

    @FXML
    private Button modifier1;

    private ModEquipment equipementAncien;
    static ModLocation selectedLocation;


    /**
     * Constructor, but with access to @FXML fields.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Nothing to do
        // Initialisation is performed when we get the 'equipementAncien' value.
    }

    /**
     * Send modifications to previous controller
     */
    @FXML
    private void pressModifier() {
        // Generate equipment
        ModEquipment equipementModifier ;
        equipementModifier = new ModEquipment(1, nom.getText(), description.getText(), numeroSerie.getText(), selectedLocation);

        // Transmit old and new equipment to the previous controller.
        CtrlEquipment.modifierEquipement(equipementAncien, equipementModifier);

        // Reopen previous controller.
        Stage fenetrePrincipale = (Stage)modifier1.getScene().getWindow();
        // Close this window.
        fenetrePrincipale.close();
    }

    /**
     * Open te window to select a location
     */
    @FXML
    private void pressSelectLieu() {
        // Init
        Stage primaryStage = new Stage();
        CtrlLocation.selectionner = true;

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

         // Styles, will be activated later on
        scene.getStylesheets().add(getClass().getResource("../../../../../css/application.css").toExternalForm());

        // Stage configuration
        primaryStage.setScene(scene);
        primaryStage.setTitle("ModLocation");
        primaryStage.show();
    }

    /**
     * Receive 'equipementAncien' and set the label accordingly.
     * @param equipment the selected equipment
     */
    void setEquipementAncien(ModEquipment equipment) {
        // set value
        equipementAncien = equipment;

        // Set text in textFields
        nom.setText(equipementAncien.getName());
        description.setText(equipementAncien.getDescription());
        numeroSerie.setText(equipementAncien.getSerialNumber());

        // Set label text
        lieu.setText(equipementAncien.getLocationName());

        // Get Location object
        selectedLocation = equipementAncien.getLieuObject(Launcher.dataBase);
    }
}
