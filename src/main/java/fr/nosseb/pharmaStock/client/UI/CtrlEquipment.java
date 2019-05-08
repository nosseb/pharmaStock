package fr.nosseb.pharmaStock.client.UI;

import fr.nosseb.pharmaStock.client.Launcher;
import fr.nosseb.pharmaStock.client.utils;
import fr.nosseb.pharmaStock.models.ModEquipment;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
//import fr.nosseb.pharmaStock.models.ModEquipment;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Controleur de la page qui affiche les equipments
 *
 * @author Loan Veyer
 * @date 26/03/2019
 *
 */

public class CtrlEquipment implements Initializable {
    // Links to JFX elements
    @FXML private Button retourMenuPrincipal1;
    @FXML private Button addEquipment1;
    @FXML private Button deleteEquipment1;
    @FXML private Button editEquipment1;
    @FXML private TableView<ModEquipment> equipments;
    @FXML private TableColumn<ModEquipment, String> name;
    @FXML private TableColumn<ModEquipment, String> locationName;
    @FXML private TableColumn<ModEquipment, String> serialNumber;

    // List used to fill the table with the equipments
    static private ObservableList<ModEquipment> equipmentsList;


    /**
     * Constructor, but with access to @FXML fields.
     * @param url
     * @param rb
     */
    @Override public void initialize(URL url, ResourceBundle rb) {
        // Links to FXML table columns
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        locationName.setCellValueFactory(new PropertyValueFactory<>("locationName"));
        serialNumber.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));

        // Get equipments list fromDB database
        equipmentsList = ModEquipment.fromDB();

        // fill table
        equipments.setItems(equipmentsList);
    }


    // DOCUMENTATION: Document method inner.
    // DOCUMENTATION: explain exeption.
    // FIXME: Can we better manage the exception ?
    /**
     * Method to return to the main menu scene
     * @throws IOException
     */
    @FXML private void pressRetourMenu() throws IOException {

        Stage location = (Stage)retourMenuPrincipal1.getScene().getWindow();
        location.close();

        Stage primaryStage = new Stage();

        Parent root = FXMLLoader.load(getClass().getResource("../../../../../fxml/MainMenu.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("../../../../../css/application.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Menu Principal");
        primaryStage.show();
    }


    // DOCUMENTATION: Explain method inner.
    /**
     * Method to add equipment
     */
    @FXML private void pressAdd() throws IOException {
        // Define new stage and scene
        Stage newStage = new Stage();
        newStage.initOwner(editEquipment1.getScene().getWindow());

        CtrlEquipmentAdd.caller(newStage);
    }

    // DOCUMENTATION
    @FXML private void pressDelete() throws IOException {
        ModEquipment select = equipments.getSelectionModel().getSelectedItem();
        if (select != null) {
            select.removefromDB();
            equipments.getItems().remove(select);
        }
    }

    // FIXME: Better manage exception.
    /**
     * Call window to modify an equipment
     * @throws IOException
     */
    @FXML private void pressEdit() throws IOException {
        // Get the equipment selected by the user.
        ModEquipment selectedEquipment = equipments.getSelectionModel().getSelectedItem();

        // Only if an equipment is selected.
        if (selectedEquipment != null) {
            // Define new stage and scene
            Stage newStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../../../fxml/EquipmentEdit.fxml"));
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(getClass().getResource("../../../../../css/application.css").toExternalForm());
            newStage.setScene(scene);

            // Send values
            CtrlEquipmentEdit controller = loader.<CtrlEquipmentEdit>getController();
            controller.setOldEquipment(selectedEquipment);

            // Configure stage
            newStage.initModality(Modality.WINDOW_MODAL);
            newStage.initOwner(editEquipment1.getScene().getWindow());
            newStage.setScene(scene);
            newStage.setTitle("Modifier ModEquipment");
            newStage.show();
        }
    }


    // DOCUMENTATION
    static public void addEquipment(ModEquipment nouvelleEquipement) {
        equipmentsList.add(nouvelleEquipement);

        nouvelleEquipement.addToDB();
    }

    // DOCUMENTATION
    static public void editEquipment(ModEquipment ancienEquipement, ModEquipment equipementModifie) {

        int i = 0;
        for (ModEquipment e : equipmentsList) {

            if (e.equals(ancienEquipement))
                break;

            i++;
        }

        equipmentsList.set(i, equipementModifie);

        equipementModifie.addToDB();
    }

    // DOCUMENTATION
    void caller() {
        Launcher.commonStage.setScene(utils.sceneGenerator("fxml/Equipment.fxml"));
        Launcher.commonStage.setTitle("Équipement");
        Launcher.commonStage.show();
    }
}