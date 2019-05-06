package fr.nosseb.pharmaStock.client.UI;

import fr.nosseb.pharmaStock.models.ModLieu;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class LieuAjouter implements Initializable {
    @FXML
    private TextField nom;

    @FXML
    private TextField description;

    @FXML
    private Button ajouter;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void ajouter() {
        if (nom.getText() != " " && description.getText() != " ") {
            ModLieu nouveauLieu = new ModLieu(nom.getText(), description.getText());

            fr.nosseb.pharmaStock.client.UI.Lieu.ajouterLieu(nouveauLieu);

            Stage fenetreAjouterLieu = (Stage)ajouter.getScene().getWindow();
            // OPTIMISATION: Can we close the window later on to reduce the black screen lag ?
            fenetreAjouterLieu.close();

            Stage primaryStage = new Stage();

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

            // CLEANUP: Can we get rid of this or will it be activated later on ?
//        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            primaryStage.setScene(scene);
            primaryStage.setTitle("ModLieu");
            primaryStage.show();
        }
    }
}
