package fr.nosseb.pharmaStock.client.UI;

//import fr.nosseb.pharmaStock.models.ModLocation;

import fr.nosseb.pharmaStock.models.ModLocation;
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
public class CtrlLocation implements Initializable {
    @FXML private Button retourMenuPrincipal1;
    @FXML private Button ajouterLieu1;
    @FXML private Button modifierLieu1;
    @FXML private Button select1;
    @FXML private TableView<ModLocation> location;
    @FXML private TableColumn<ModLocation, String> name;
    @FXML private TableColumn<ModLocation, String> description;
    @FXML private TableColumn<ModLocation, Integer> id;

    static private ObservableList<ModLocation> data;
    static public boolean selectionner;
    static ModLocation selectedLocation;


    // DOCUMENTATION: Explain 'url" and 'rb" fields
    /**
     * Constructor, but with access to @FXML fields.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Link JavaFX table columns to actual data.
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        // Get the locations fromDB the database.
        data = ModLocation.fromDB();

        // Link table 'location' with ObservableList 'data'.
        location.setItems(data);

        select1.setVisible(selectionner);
    }

    /**
     * Fallback onto the main menu.
     */
    @FXML private void pressRetourMenu() {
        Stage location = (Stage)retourMenuPrincipal1.getScene().getWindow();
        // OPTIMISATION: Can we close the window later on to reduce the black screen lag ?
        location.close();
        Stage primaryStage = new Stage();

        URL fxml = getClass().getResource("../../../../../fxml/MainMenu.fxml");
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
    @FXML private void pressAjouter() {
        Stage fenetreLieu = (Stage)ajouterLieu1.getScene().getWindow();
        // OPTIMISATION: Can we close the window later on to reduce the black screen lag ?
        fenetreLieu.close();


        Stage primaryStage = new Stage();

        URL fxml = getClass().getResource("../../../../../fxml/LocationAdd.fxml");
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
        primaryStage.setTitle("Ajouter ModLocation");
        primaryStage.show();
    }

    /**
     * Delete a location.
     */
    @FXML private void pressSupprimer() {
        ModLocation select = location.getSelectionModel().getSelectedItem();

        if (select != null) {
            location.getItems().remove(select);
            select.removeFrom();
        }
    }

    /**
     * Edit a location.
     */
    @FXML private void pressModifier() {
        ModLocation select = location.getSelectionModel().getSelectedItem();

        if (select != null) {
            CtrlLocationEdit.lieuAncien = select;

            Stage newStage = new Stage();

            URL fxml = getClass().getResource("../../../../../fxml/LocationEdit.fxml");
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
            newStage.setTitle("Modifier ModLocation");
            newStage.show();
        }
    }

    @FXML private void pressSelectLocation() {
        ModLocation select = location.getSelectionModel().getSelectedItem();
        CtrlEquipmentEdit.selectedLocation = select;
        CtrlEquipmentAdd.selectedLocation = select;
        Stage location = (Stage)retourMenuPrincipal1.getScene().getWindow();
        location.close();
    }

    /**
     * Aply the change of an element
     * @param nouveauLieu the updated element.
     */
    static void ajouterLieu(ModLocation nouveauLieu) {
        // TODO : Check if fields are not null

        // Add element to observableList.
        data.add(nouveauLieu);
        // Add element to Database.
        nouveauLieu.addToDB();

        // Sync table x=with DataBase

    }

    /**
     * Edit a location
     * @param ancienLieu the old version.
     * @param lieuModifier the updated version.
     */
    static void modifierLieu(ModLocation ancienLieu, ModLocation lieuModifier) {
        // Find the old object in the observableList
        // OPTIMISATION: can we use an index as input instead of a cumbersome object ?
        int i = 0;
        for (ModLocation e : data) {

            if (e.equals(ancienLieu))
                break;

            i++;
        }

        // Exchange the element in the observableList.
        data.set(i, lieuModifier);
        // Apply the modification to the Database.
        lieuModifier.addToDB();

    }

    static ModLocation selector() {
        // Configuration
        CtrlLocation.selectionner = true;
        Stage stage = new Stage();
        URL fxml = CtrlLocation.class.getClassLoader().getResource("fxml/Location.fxml");

        if (fxml == null) {
            // EXCEPTION: failed to load fxml file.
            throw new NullPointerException("'fxml' cannot be null");
        }

        Parent root = null;
        try {
            root = FXMLLoader.load(fxml);
        } catch (IOException e) {
            // EXCEPTION: Internal resource not found, hard crash expected.
            e.printStackTrace();
        }

        if (root == null) {
            // EXCEPTION: failed to assign root
            throw new NullPointerException("'root' cannot be null");
        }

        // FIXME : can we avoid the "Argument 'root' might be null" message ?
        Scene scene = new Scene(root);

        // Styles
        URL url = CtrlLocation.class.getClassLoader().getResource("css/application.css");
        String css = null;
        try {
            css = url.toExternalForm();
        } catch (NullPointerException e) {
            // EXCEPTION: failed to transform url, crash expected.
            e.printStackTrace();
        }
        scene.getStylesheets().add(css);

        // Stage configuration
        stage.setScene(scene);
        stage.setTitle("ModLocation");
        stage.show();

        // TODO: return actual value
        return selectedLocation;
    }
}
