package fr.nosseb.pharmaStock.client.UI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import fr.nosseb.pharmaStock.models.Lieu;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * 
 * @author Loan Veyer
 * @date 03/04/2019
 *
 */

public class ControleurModifierLieu implements Initializable {
	@FXML
	private TextField nom;
	
	@FXML
	private TextField description;
	
	@FXML
	private Button modifier1;
	
	static Lieu lieuAncien;
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
		
		nom.setText(lieuAncien.getNom());
		description.setText(lieuAncien.getDescription());
		
	}
	
	@FXML
	private void pressModifier() {
		Lieu nouveauLieu = new Lieu(nom.getText(), description.getText());
		
		ControleurLieu.modifierLieu(lieuAncien, nouveauLieu);
		
		Stage fenetrePrincipale = (Stage)modifier1.getScene().getWindow();
		fenetrePrincipale.close();
	}
}
