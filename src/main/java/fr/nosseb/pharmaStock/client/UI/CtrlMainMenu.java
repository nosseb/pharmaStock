package fr.nosseb.pharmaStock.client.UI;

import fr.nosseb.pharmaStock.client.Launcher;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;


/**
 * Contrôleur de la page d'acceuil
 *
 * @author Loan Veyer
 * @date 26/03/2019
 *
 */
public class CtrlMainMenu {
    // Links to JavFX elements.
    @FXML private Button equipment1;
    @FXML private Button Location1;
    @FXML private Button ficheSortie1;

    private Stage stage;

    /**
     * Launch window to view equipment.
     */
    @FXML public void pressEquipment() {
        ClassLoader classLoader = Launcher.class.getClassLoader();
        Stage currentStage = (Stage) equipment1.getScene().getWindow();

        // Setup new stage
        URL fxml = classLoader.getResource("fxml/Equipment.fxml");
        Parent root = null;
        if (fxml == null) {
            throw new NullPointerException("'fxml' cannot be null");
        }
        try {
            root = FXMLLoader.load(fxml);
        } catch (IOException e) {
            // EXCEPTION: Internal resource not found, hard crash expected.
            e.printStackTrace();
        }

        // Check if root was properly assigned.
        if (root == null) {
            throw new NullPointerException("'root' cannot be null");
        }
        Scene scene = new Scene(root);
        URL resource = classLoader.getResource("css/application.css");
        if (resource == null) {
            throw new NullPointerException("'css' cannot be null");
        }
        scene.getStylesheets().add(resource.toExternalForm());

        currentStage.setScene(scene);
        currentStage.setTitle("Équipement");
    }

    /**
     * Launch window to view locations.
     */
    @FXML public void pressLocation(){
        Stage fenetrePrincipale = (Stage) Location1.getScene().getWindow();
        // OPTIMISATION: Can we close the window later on to reduce the black screen lag ?
        fenetrePrincipale.close();
        Stage primaryStage = new Stage();

        URL fxml = getClass().getResource("../../../../../fxml/Location.fxml");
        Parent root = null;
        try {
            root = FXMLLoader.load(fxml);
        } catch (IOException e) {
            // EXCEPTION: Internal resource not found, hard crash expected.
            e.printStackTrace();
        }

        // Check if root was properly assigned.
        if (root == null) {
            throw new NullPointerException("'root' cannot be null");
        }

        // FIXME : can we avoid the "Argument 'root' might be null" message ?
        Scene scene = new Scene(root);

        scene.getStylesheets().add(getClass().getResource("../../../../../application.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle("ModLocation");
        primaryStage.show();
        CtrlLocation.selectionner = false;
    }

    // DOCUMENTATION: Change non adapted word: "exit".
    /**
     * Launch window to fill "exit" forms
     */
    @FXML public void pressFicheSortie()  {
        Stage fenetrePrincipale = (Stage)ficheSortie1.getScene().getWindow();
        // OPTIMISATION: Can we close the window later on to reduce the black screen lag ?
        fenetrePrincipale.close();
        Stage primaryStage = new Stage();

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

        scene.getStylesheets().add(getClass().getResource("../../../../../application.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Fiche de sortie");
        primaryStage.show();
    }

    public void caller(Stage primaryStage) {
        this.stage = primaryStage;

        Parent root = null;
        Scene scene;

        // Will allow for cleaner paths when importing resources.
        URL fxml = getClass().getResource("../../../../../fxml/MainMenu.fxml");
        String css = getClass().getResource("../../../../../css/application.css").toExternalForm();

        try {
            root = FXMLLoader.load(fxml);
        } catch (java.io.IOException e) {
            // EXCEPTION: Internal resource not found, crash expected.
            System.out.println("Internal resources not found (fxml file)!");
            e.printStackTrace();
        }

        // Check if root has been properly assigned.
        if (root == null) {
            // EXCEPTION: failed to assign 'root', crash expected.
            throw new NullPointerException("'root' cannot be null");
        }

        // Scene initialization
        scene = new Scene(root);
        scene.getStylesheets().add(css);

        // Stage configuration and display
        this.stage.setScene(scene);
        // TODO: Localisation: Use resource instead of hardcoded String
        this.stage.setTitle("Menu Principal");
        this.stage.show();
    }
}
