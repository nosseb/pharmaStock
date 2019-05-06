package fr.nosseb.pharmaStock.client.UI;

import fr.nosseb.pharmaStock.models.ModEquipement;
import fr.nosseb.pharmaStock.models.ModLieu;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;

import java.util.ResourceBundle;

/**
 *
 * @author Loan Veyer
 * @date 03/04/2019
 *
 */
public class EquipementAjouter implements Initializable {
    // Links to FXML elements
    @FXML private TextField nom;
    @FXML private TextField description;
    @FXML private TextField numeroSerie;
    @FXML private Label labelNomLieu;
    @FXML private Button modLieu1;
    @FXML private Button ajouter1;

    static ModLieu selectedLocation;

    /**
     * Constructor, but with access to @FXML fields.
     * @param url
     * @param rb
     */
    @Override public void initialize(URL url, ResourceBundle rb) {
        // Nothing to do
        // Initialisation is performed when we get the 'equipementAncien' value.
    }

    /**
     * Send modifications to previous controller
     */
    @FXML private void pressAjouter1() {
        // Generate equipment
        ModEquipement equipementModifier ;
        equipementModifier = new ModEquipement(1, nom.getText(), description.getText(), numeroSerie.getText(), selectedLocation);

        // Transmit old and new equipment to the previous controller.
        fr.nosseb.pharmaStock.client.UI.Equipement.ajouterEquipement(equipementModifier);

        // Reopen previous controller.
        Stage fenetrePrincipale = (Stage)ajouter1.getScene().getWindow();
        // Close this window.
        fenetrePrincipale.close();
    }

    /**
     * Open te window to select a location
     */
    @FXML private void pressModLieu1() {
        ModLieu lieu = fr.nosseb.pharmaStock.client.UI.Lieu.selector();
    }
}
