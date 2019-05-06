package fr.nosseb.pharmaStock.client.UI;

import fr.nosseb.pharmaStock.client.Launcher;
import fr.nosseb.pharmaStock.models.ModEquipement;
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
//import fr.nosseb.pharmaStock.models.ModEquipement;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controleur de la page qui affiche les equipements
 *
 * @author Loan Veyer
 * @date 26/03/2019
 *
 */

public class Equipement implements Initializable {
    // Links to JFX elements
    @FXML private Button retourMenuPrincipal1;
    @FXML private Button ajouterEquipement1;
    @FXML private Button supprimerEquipement1;
    @FXML private Button modifierEquipement1;
    @FXML private TableView<ModEquipement> equipements;
    @FXML private TableColumn<ModEquipement, String> nom;
    @FXML private TableColumn<ModEquipement, String> lieu;
    @FXML private TableColumn<ModEquipement, String> numeroSerie;

    // List used to fill the table with the equipments
    static private ObservableList<ModEquipement> equipementsList;


    /**
     * Constructor, but with access to @FXML fields.
     * @param url
     * @param rb
     */
    @Override public void initialize(URL url, ResourceBundle rb) {
        // Links to FXML table collumns
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        lieu.setCellValueFactory(new PropertyValueFactory<>("lieuNom"));
        numeroSerie.setCellValueFactory(new PropertyValueFactory<>("numeroSerie"));

        // Get equipments list from database
        equipementsList = ModEquipement.from(Launcher.dataBase);

        // fill table
        equipements.setItems(equipementsList);
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

        Parent root = FXMLLoader.load(getClass().getResource("../../../../../fxml/MenuPrincipal.fxml"));
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
    @FXML private void pressAjouter() throws IOException {
        // Define new stage and scene
        Stage newStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../../../fxml/EquipementAjouter.fxml"));
        // FIXME: Better manage exception.
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add(getClass().getResource("../../../../../css/application.css").toExternalForm());
        newStage.setScene(scene);

        // Configure stage
        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(modifierEquipement1.getScene().getWindow());
        newStage.setScene(scene);
        newStage.setTitle("Ajouter ModEquipement");
        newStage.show();
    }

    // DOCUMENTATION
    @FXML private void pressSupprimer() throws IOException {
        ModEquipement select = equipements.getSelectionModel().getSelectedItem();
        if (select != null)
            equipements.getItems().remove(select);
    }

    // FIXME: Better manage exception.
    /**
     * Call window to modify an equipment
     * @throws IOException
     */
    @FXML private void pressModifier() throws IOException {
        // Get the equipment selected by the user.
        ModEquipement selectedEquipment = equipements.getSelectionModel().getSelectedItem();

        // Only if an equipment is selected.
        if (selectedEquipment != null) {
            // Define new stage and scene
            Stage newStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../../../fxml/EquipementModifier.fxml"));
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(getClass().getResource("../../../../../css/application.css").toExternalForm());
            newStage.setScene(scene);

            // Send values
            fr.nosseb.pharmaStock.client.UI.EquipementModifier controller = loader.<fr.nosseb.pharmaStock.client.UI.EquipementModifier>getController();
            controller.setEquipementAncien(selectedEquipment);

            // Configure stage
            newStage.initModality(Modality.WINDOW_MODAL);
            newStage.initOwner(modifierEquipement1.getScene().getWindow());
            newStage.setScene(scene);
            newStage.setTitle("Modifier ModEquipement");
            newStage.show();
        }
    }


    // DOCUMENTATION
    static public void ajouterEquipement(ModEquipement nouvelleEquipement) {
        equipementsList.add(nouvelleEquipement);
    }

    // DOCUMENTATION
    static public void modifierEquipement(ModEquipement ancienEquipement, ModEquipement equipementModifie) {

        int i = 0;
        for (ModEquipement e : equipementsList) {

            if (e.equals(ancienEquipement))
                break;

            i++;
        }

        equipementsList.set(i, equipementModifie);
    }
}