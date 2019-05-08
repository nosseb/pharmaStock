package fr.nosseb.pharmaStock.client.UI;

import fr.nosseb.pharmaStock.client.utils;
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
import javafx.stage.Modality;
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
    @FXML private TextField name;
    @FXML private TextField description;
    @FXML private TextField serialNumber;
    @FXML private Label locationName;
    @FXML private Button selectLieu1;
    @FXML private Button modifier1;

    private ModEquipment oldEquipment;
    static ModLocation selectedLocation;


    /**
     * Constructor, but with access to @FXML fields.
     * @param url
     * @param rb
     */
    @Override public void initialize(URL url, ResourceBundle rb) {
    }

    /**
     * Send modifications to previous controller
     */
    @FXML private void pressModifier() {
        // Generate oldEquipment
        ModEquipment equipementModifier ;
        equipementModifier = new ModEquipment(1, name.getText(), description.getText(), serialNumber.getText(), selectedLocation);

        // Transmit old and new oldEquipment to the previous controller.
        CtrlEquipment.editEquipment(oldEquipment, equipementModifier);

        // Reopen previous controller.
        Stage fenetrePrincipale = (Stage)modifier1.getScene().getWindow();
        // Close this window.
        fenetrePrincipale.close();
    }

    /**
     * Open te window to select a location
     */
    @FXML private void pressSelectLieu() {

        // Init
        Stage primaryStage = new Stage();
        CtrlLocation.selectionner = true;

        URL fxml = getClass().getResource("../../../../../fxml/Location.fxml");
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
     * Receive 'oldEquipment' and set the label accordingly.
     * @param oldEquipment the selected oldEquipment
     */
    void setOldEquipment(ModEquipment oldEquipment) {
        this.oldEquipment = oldEquipment;

        // Set text in textFields
        this.name.setText(this.oldEquipment.getName());
        this.description.setText(this.oldEquipment.getDescription());
        this.serialNumber.setText(this.oldEquipment.getSerialNumber());

        // Set label text
        this.locationName.setText(this.oldEquipment.getLocationName());

        // Get Location object
        selectedLocation = this.oldEquipment.getLieuObject();
    }

    // DOCUMENTATION
    CtrlEquipmentEdit caller(ModEquipment oldEquipment) {
        Stage stage = new Stage();

        // Configure stage
        stage.initModality(Modality.WINDOW_MODAL);
        utils.Holder holder = utils.sceneGenerator("fxml/EquipmentEdit.fxml");
        stage.setScene(holder.scene);
        stage.setTitle("Modifier ModEquipment");
        stage.show();

        CtrlEquipmentEdit ctrlEquipmentEdit = holder.fxmlLoader.<CtrlEquipmentEdit>getController();
        ctrlEquipmentEdit.setOldEquipment(oldEquipment);

        return ctrlEquipmentEdit;
    }
}
