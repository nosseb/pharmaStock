package fr.nosseb.pharmaStock.client.UI;

import fr.nosseb.pharmaStock.client.utils;
import fr.nosseb.pharmaStock.models.ModLocation;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class CtrlLocationAdd implements Initializable {
    @FXML private TextField nom;

    @FXML private TextField description;

    @FXML private Button ajouter;

    @Override public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML private void ajouter() {
        if (nom.getText() != " " && description.getText() != " ") {
            ModLocation nouveauLieu = new ModLocation(nom.getText(), description.getText());

            CtrlLocation.ajouterLieu(nouveauLieu);

            Stage fenetreAjouterLieu = (Stage)ajouter.getScene().getWindow();
            // OPTIMISATION: Can we close the window later on to reduce the black screen lag ?
            fenetreAjouterLieu.close();

            Stage primaryStage = new Stage();

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

            scene.getStylesheets().add(getClass().getResource("../../../../../application.css").toExternalForm());

            primaryStage.setScene(scene);
            primaryStage.setTitle("ModLocation");
            primaryStage.show();
        }
    }

    // DOCUMENTATION
    void caller(Stage stage) {
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(utils.sceneGenerator("fxml/LocationAdd.fxml").scene);
        stage.setTitle("Ajouter lieu");
        stage.show();
    }
}
