package fr.nosseb.pharmaStock.client.UI;

import fr.nosseb.pharmaStock.models.ModLieu;
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
public class LieuModifier implements Initializable {
    @FXML
    private TextField nom;

    @FXML
    private TextField description;

    @FXML
    private Button modifier1;

    static ModLieu lieuAncien;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        nom.setText(lieuAncien.getNom());
        description.setText(lieuAncien.getDescription());

    }

    @FXML
    private void pressModifier() {
        ModLieu nouveauLieu = new ModLieu(lieuAncien.getIdLieu(), nom.getText(), description.getText());

        fr.nosseb.pharmaStock.client.UI.Lieu.modifierLieu(lieuAncien, nouveauLieu);

        Stage fenetrePrincipale = (Stage)modifier1.getScene().getWindow();
        fenetrePrincipale.close();
    }
}
