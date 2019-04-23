package fr.nosseb.pharmaStock.client.UI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import fr.nosseb.pharmaStock.models.Lieu;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * 
 * @author Loan Veyer
 * @date 03/04/2019
 *
 */

public class ControleurAjouterLieu implements Initializable {
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
			Lieu nouveauLieu = new Lieu(nom.getText(), description.getText());
			
			ControleurLieu.ajouterLieu(nouveauLieu);
		}
	}
}
