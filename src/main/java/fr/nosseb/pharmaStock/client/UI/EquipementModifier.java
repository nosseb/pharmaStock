package fr.nosseb.pharmaStock.client.UI;

import fr.nosseb.pharmaStock.client.Launcher;
import fr.nosseb.pharmaStock.models.ModEquipement;
import fr.nosseb.pharmaStock.models.ModLieu;
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
public class EquipementModifier implements Initializable {
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

    private ModEquipement equipementAncien;
    static ModLieu selectedLocation;


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
        ModEquipement equipementModifier ;
        equipementModifier = new ModEquipement(1, nom.getText(), description.getText(), numeroSerie.getText(), selectedLocation);

        // Transmit old and new equipment to the previous controller.
        fr.nosseb.pharmaStock.client.UI.Equipement.modifierEquipement(equipementAncien, equipementModifier);

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
        Lieu.selectionner = true;

        // CLEANUP: Use 'FXMLLoader.setLocation()' to set the location used to resolve relative path attribute values.
        URL fxml = getClass().getResource("../../../../../fxml/ModLieu.fxml");
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
        primaryStage.setTitle("ModLieu");
        primaryStage.show();
    }

    /**
     * Receive 'equipementAncien' and set the label accordingly.
     * @param equipment the selected equipment
     */
    void setEquipementAncien(ModEquipement equipment) {
        // set value
        equipementAncien = equipment;

        // Set text in textFields
        nom.setText(equipementAncien.getNom());
        description.setText(equipementAncien.getDescription());
        numeroSerie.setText(equipementAncien.getNumeroSerie());

        // Set label text
        lieu.setText(equipementAncien.getLieuNom());

        // Get Location object
        selectedLocation = equipementAncien.getLieuObject(Launcher.dataBase);
    }
}
