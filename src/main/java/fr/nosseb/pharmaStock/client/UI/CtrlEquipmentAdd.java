package fr.nosseb.pharmaStock.client.UI;

import fr.nosseb.pharmaStock.models.ModEquipment;
import fr.nosseb.pharmaStock.models.ModLocation;
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
public class CtrlEquipmentAdd implements Initializable {
    // Links to FXML elements
    @FXML private TextField name;
    @FXML private TextField description;
    @FXML private TextField serialNumber;
    @FXML private Label locationName;
    @FXML private Button slctLocation1;
    @FXML private Button add1;

    static ModLocation selectedLocation;

    /**
     * Constructor, but with access to @FXML fields.
     * @param url
     * @param rb
     */
    @Override public void initialize(URL url, ResourceBundle rb) {
        // Nothing to do
    }

    /**
     * Send modifications to previous controller
     */
    @FXML private void pressAdd1() {
        // Generate equipment
        ModEquipment editEquipment;
        editEquipment = new ModEquipment(-1, name.getText(), description.getText(), serialNumber.getText(), selectedLocation);

        // Transmit old and new equipment to the previous controller.
        CtrlEquipment.addEquipment(editEquipment);

        // Reopen previous controller.
        Stage mainWindow = (Stage) add1.getScene().getWindow();
        // Close this window.
        mainWindow.close();
    }

    /**
     * Open te window to select a location
     */
    @FXML private void pressSlctLocation1() {
        selectedLocation = CtrlLocation.selector();
    }
}
