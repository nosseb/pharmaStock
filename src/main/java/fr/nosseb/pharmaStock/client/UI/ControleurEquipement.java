package fr.nosseb.pharmaStock.client.UI;

import fr.nosseb.pharmaStock.client.Launcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import fr.nosseb.pharmaStock.models.Equipement;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controleur de la page qui affiche les equipements
 * 
 * @author Loan Veyer
 * @date 26/03/2019
 *
 */

public class ControleurEquipement implements Initializable {
	
	// Fenêtre principale
	@FXML
	private Button retourMenuPrincipal1;
	
	@FXML
	private Button ajouterEquipement1;
	
	@FXML
	private Button supprimerEquipement1;
	
	@FXML
	private Button modifierEquipement1;
	
	@FXML
	private TableView<Equipement> equipements;
	
	@FXML
	private TableColumn<Equipement, String> nom;
	
	@FXML
	private TableColumn<Equipement, String> lieu;
	
	@FXML
	private TableColumn<Equipement, String> numeroSerie;
	
	static private ObservableList<Equipement> data;
	
	// Méthodes
	@Override
    public void initialize(URL url, ResourceBundle rb) {
		
		nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		lieu.setCellValueFactory(new PropertyValueFactory<>("lieuNom"));
		numeroSerie.setCellValueFactory(new PropertyValueFactory<>("numeroSerie"));
		
		//data = FXCollections.observableArrayList();

		data = Equipement.from(Launcher.dataBase);
		
		equipements.setItems(data);
	}
	
	@FXML
	private void pressRetourMenu() throws IOException {
		
		Stage location = (Stage)retourMenuPrincipal1.getScene().getWindow();
		location.close();
		
		Stage primaryStage = new Stage();
		
		Parent root = FXMLLoader.load(getClass().getResource("../../../../../fxml/MenuPrincipal.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("../../../../../css/application.css").toExternalForm());
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Menu Principal");
		primaryStage.show();
	}
	
	@FXML
	private void pressAjouter() throws IOException {
		//Stage location = (Stage)ajouterEquipement1.getScene().getWindow();
		
		Stage primaryStage = new Stage();
		
		Parent root = FXMLLoader.load(getClass().getResource("../../../../../fxml/AjouterEquipement.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("../../../../../css/application.css").toExternalForm());
		
		primaryStage.initModality(Modality.WINDOW_MODAL);
		primaryStage.initOwner(modifierEquipement1.getScene().getWindow());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Ajouter Equipement");
		primaryStage.show();
	}
	
	@FXML
	private void pressSupprimer() throws IOException {
		Equipement select = equipements.getSelectionModel().getSelectedItem();
		if (select != null)
			equipements.getItems().remove(select);
	}
	
	@FXML 
	private void pressModifier() throws IOException {
		Equipement select = equipements.getSelectionModel().getSelectedItem();
		
		if (select != null) {
			ControleurModifierEquipement.equipementAncien = select;
			
			Stage newStage = new Stage();
			
			Parent root = FXMLLoader.load(getClass().getResource("../../../../../fxml/ModifierEquipement.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../../../../../css/application.css").toExternalForm());
			
			newStage.initModality(Modality.WINDOW_MODAL);
			newStage.initOwner(modifierEquipement1.getScene().getWindow());
			newStage.setScene(scene);
			newStage.setTitle("Modifier Equipement");
			newStage.show();
		}
	}
	
	
	static public void ajouterEquipement(Equipement nouvelleEquipement) {
		data.add(nouvelleEquipement);
	}
	
	static public void modifierEquipement(Equipement ancienEquipement, Equipement equipementModifie) {
		
		int i = 0;
		for (Equipement e : data) {
			
			if (e.equals(ancienEquipement))
				break;
			
			i++;
		}
		
		data.set(i, equipementModifie);
	}
}