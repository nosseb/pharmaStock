package fr.nosseb.pharmaStock.client.UI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import fr.nosseb.pharmaStock.models.Equipement;
import fr.nosseb.pharmaStock.models.Lieu;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controleur de la page qui affiche les equipements
 * 
 * @author Loan Veyer
 * @date 26/03/2019
 *
 */

public class ControleurAjouterEquipement implements Initializable {
	@FXML
	private TextField nom;
	
	@FXML
	private TextField description;
	
	@FXML
	private TextField numeroSerie;
	
	@FXML
	private ChoiceBox<Lieu> lieu;
	
	@FXML
	private Button ajouter;
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
	}
	
	@FXML
	private void ajouter() {
		String nouveauNom = nom.getText();
		String nouvelleDescription = description.getText();
		String nouveauNumeroSerie = numeroSerie.getText();
		
		Equipement nouvelleEquipement = new Equipement(1, nouveauNom, nouvelleDescription, nouveauNumeroSerie, 0, "Lorient");
		
		ControleurEquipement.ajouterEquipement(nouvelleEquipement);
	}
}
