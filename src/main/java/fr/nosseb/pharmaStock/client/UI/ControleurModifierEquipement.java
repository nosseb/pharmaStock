package fr.nosseb.pharmaStock.client.UI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import fr.nosseb.pharmaStock.models.Equipement;
import fr.nosseb.pharmaStock.models.Lieu;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * 
 * @author Loan Veyer
 * @date 03/04/2019
 *
 */

public class ControleurModifierEquipement implements Initializable {
	@FXML
	private TextField nom;
	
	@FXML
	private TextField description;
	
	@FXML
	private TextField numeroSerie;
	
	@FXML
	private Button modifier1;
	
	static Equipement equipementAncien;
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
		
		nom.setText(equipementAncien.getNom());
		description.setText(equipementAncien.getDescription());
		numeroSerie.setText(equipementAncien.getNumeroSerie());
		
	}
	
	@FXML
	private void pressModifier() {
		Equipement equipementModifier = new Equipement(1, nom.getText(), description.getText(), numeroSerie.getText(), 0, "Lorient");
		
		ControleurEquipement.modifierEquipement(equipementAncien, equipementModifier);
		
		Stage fenetrePrincipale = (Stage)modifier1.getScene().getWindow();
		fenetrePrincipale.close();
	}
}
