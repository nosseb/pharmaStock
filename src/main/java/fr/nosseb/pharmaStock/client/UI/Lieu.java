package fr.nosseb.pharmaStock.client.UI;

import fr.nosseb.pharmaStock.client.Launcher;
//import fr.nosseb.pharmaStock.models.ModLieu;

import fr.nosseb.pharmaStock.models.ModLieu;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
public class Lieu implements Initializable {
    @FXML
    private Button retourMenuPrincipal1;

    @FXML
    private Button ajouterLieu1;

    @FXML
    private Button modifierLieu1;

    @FXML
    private Button selectionner1;

    @FXML
    private TableView<ModLieu> lieux;

    @FXML
    private TableColumn<ModLieu, String> nom;

    @FXML
    private TableColumn<ModLieu, String> description;

    @FXML
    private TableColumn<ModLieu, Integer> idLieu;

    static private ObservableList<ModLieu> data;
    static public boolean selectionner;
    static ModLieu selectedLocation;


    // DOCUMENTATION: Explain 'url" and 'rb" fields
    /**
     * Constructor, but with access to @FXML fields.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Link JavaFX table columns to actual data.
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        idLieu.setCellValueFactory(new PropertyValueFactory<>("idLieu"));

        // Get the locations from the database.
        data = ModLieu.from(Launcher.dataBase);

        // Link table 'lieux' with ObservableList 'data'.
        lieux.setItems(data);

        selectionner1.setVisible(selectionner);
    }

    /**
     * Fallback onto the main menu.
     */
    @FXML
    private void pressRetourMenu() {
        Stage location = (Stage)retourMenuPrincipal1.getScene().getWindow();
        // OPTIMISATION: Can we close the window later on to reduce the black screen lag ?
        location.close();
        Stage primaryStage = new Stage();

        // CLEANUP: Use 'FXMLLoader.setLocation()' to set the location used to resolve relative path attribute values.
        URL fxml = getClass().getResource("../../../../../fxml/MenuPrincipal.fxml");
        String css = getClass().getResource("../../../../../css/application.css").toExternalForm();

        Parent root = null;

        try {
            root = FXMLLoader.load(fxml);
        } catch (IOException e) {
            // EXCEPTION: Internal resource not found, hard crash expected.
            e.printStackTrace();
        }

        // FIXME : can we avoid the "Argument 'root' might be null" message ?
        Scene scene = new Scene(root);

        scene.getStylesheets().add(css);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Menu Principal");
        primaryStage.show();
    }

    /**
     * Launch window to add a new location.
     */
    @FXML
    private void pressAjouter() {
        Stage fenetreLieu = (Stage)ajouterLieu1.getScene().getWindow();
        // OPTIMISATION: Can we close the window later on to reduce the black screen lag ?
        fenetreLieu.close();

        // CLEANUP: Can we get rid of this or will it be activated later on ?
        //Stage location = (Stage)ajouterEquipement1.getScene().getWindow();

        Stage primaryStage = new Stage();

        URL fxml = getClass().getResource("../../../../../fxml/LieuAjouter.fxml");
        String css = getClass().getResource("../../../../../css/application.css").toExternalForm();

        Parent root = null;

        try {
            root = FXMLLoader.load(fxml);
        } catch (IOException e) {
            // EXCEPTION: Internal resource not found, hard crash expected.
            e.printStackTrace();
        }

        // FIXME : can we avoid the "Argument 'root' might be null" message ?
        Scene scene = new Scene(root);

        scene.getStylesheets().add(css);

        primaryStage.initModality(Modality.WINDOW_MODAL);
        primaryStage.initOwner(modifierLieu1.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Ajouter ModLieu");
        primaryStage.show();
    }

    /**
     * Delete a location.
     */
    @FXML
    private void pressSupprimer() {
        ModLieu select = lieux.getSelectionModel().getSelectedItem();

        if (select != null) {
            lieux.getItems().remove(select);
            select.removeFrom(Launcher.dataBase);
        }
    }

    /**
     * Edit a location.
     */
    @FXML
    private void pressModifier() {
        ModLieu select = lieux.getSelectionModel().getSelectedItem();

        if (select != null) {
            fr.nosseb.pharmaStock.client.UI.LieuModifier.lieuAncien = select;

            Stage newStage = new Stage();

            URL fxml = getClass().getResource("../../../../../fxml/LieuModifier.fxml");
            String css = getClass().getResource("../../../../../css/application.css").toExternalForm();

            Parent root = null;
            try {
                root = FXMLLoader.load(fxml);
            } catch (IOException e) {
                // EXCEPTION: Internal resource not found, hard crash expected.
                e.printStackTrace();
            }

            // FIXME : can we avoid the "Argument 'root' might be null" message ?
            Scene scene = new Scene(root);

            scene.getStylesheets().add(css);

            newStage.initModality(Modality.WINDOW_MODAL);
            newStage.initOwner(modifierLieu1.getScene().getWindow());
            newStage.setScene(scene);
            newStage.setTitle("Modifier ModLieu");
            newStage.show();
        }
    }

    @FXML
    private void pressSelectLieu() {
        ModLieu select = lieux.getSelectionModel().getSelectedItem();
        fr.nosseb.pharmaStock.client.UI.EquipementModifier.selectedLocation = select;

        Stage location = (Stage)retourMenuPrincipal1.getScene().getWindow();
        // OPTIMISATION: Can we close the window later on to reduce the black screen lag ?
        location.close();
    }

    /**
     * Aply the change of an element
     * @param nouveauLieu the updated element.
     */
    static void ajouterLieu(ModLieu nouveauLieu) {
        // TODO : Check if fields are not null

        // Add element to observableList.
        data.add(nouveauLieu);
        // Add element to Database.
        nouveauLieu.addTo(Launcher.dataBase);

        // Sync table x=with DataBase

    }

    /**
     * Edit a location
     * @param ancienLieu the old version.
     * @param lieuModifier the updated version.
     */
    static void modifierLieu(ModLieu ancienLieu, ModLieu lieuModifier) {
        // Find the old object in the observableList
        // OPTIMISATION: can we use an index as input instead of a cumbersome object ?
        int i = 0;
        for (ModLieu e : data) {

            if (e.equals(ancienLieu))
                break;

            i++;
        }

        // Exchange the element in the observableList.
        data.set(i, lieuModifier);
        // Apply the modification to the Database.
        lieuModifier.addTo(Launcher.dataBase);

    }

    static ModLieu selector() {
        // Configuration
        Lieu.selectionner = true;

        // Window initialization
        Stage stage = new Stage();

        // CLEANUP: Use 'FXMLLoader.setLocation()' to set the location used to resolve relative path attribute values.
        URL fxml = Lieu.class.getClassLoader().getResource("../../../../../fxml/ModLieu.fxml");
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
        scene.getStylesheets().add(Lieu.class.getClassLoader().getResource("../../../../../css/application.css").toExternalForm());

        // Stage configuration
        stage.setScene(scene);
        stage.setTitle("ModLieu");
        stage.show();

        // TODO: return actual value
        return lieux.getSelectionModel().getSelectedItem();
    }
}
