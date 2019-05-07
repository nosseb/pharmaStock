package fr.nosseb.pharmaStock.client.UI;

import fr.nosseb.pharmaStock.models.ModLocation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
public class CtrlLocationEdit implements Initializable {
    @FXML private TextField name;
    @FXML private TextField description;
    @FXML private Button modifier1;

    static ModLocation lieuAncien;

    @Override public void initialize(URL url, ResourceBundle rb) {

        name.setText(lieuAncien.getName());
        description.setText(lieuAncien.getDescription());

    }

    @FXML private void pressModifier() {
        ModLocation nouveauLieu = new ModLocation(lieuAncien.getId(), name.getText(), description.getText());

        CtrlLocation.modifierLieu(lieuAncien, nouveauLieu);

        Stage fenetrePrincipale = (Stage)modifier1.getScene().getWindow();
        fenetrePrincipale.close();
    }
}
