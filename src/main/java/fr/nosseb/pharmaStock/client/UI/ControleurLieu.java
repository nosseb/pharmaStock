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
import fr.nosseb.pharmaStock.models.Lieu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * 
 * @author Loan Veyer
 * @date 03/04/2019
 *
 */

public class ControleurLieu implements Initializable {
	// Fenêtre principale
	@FXML
	private Button retourMenuPrincipal1;
		
	@FXML
	private Button ajouterLieu1;
		
	@FXML
	private Button supprimerLieu1;
		
	@FXML
	private Button modifierLieu1;
		
	@FXML
	private TableView<Lieu> lieux;
		
	@FXML
	private TableColumn<Lieu, String> nom;
		
	@FXML
	private TableColumn<Lieu, String> description;
		
	static private ObservableList<Lieu> data;
	
	
	// Méthodes
	@Override
	public void initialize(URL url, ResourceBundle rb) {
			
		nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		description.setCellValueFactory(new PropertyValueFactory<>("description"));
			
		data = Lieu.from(Launcher.dataBase);
			
		lieux.setItems(data);
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
		
		Parent root = FXMLLoader.load(getClass().getResource("../../../../../fxml/AjouterLieu.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("../../../../../css/application.css").toExternalForm());
		
		primaryStage.initModality(Modality.WINDOW_MODAL);
		primaryStage.initOwner(modifierLieu1.getScene().getWindow());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Ajouter Lieu");
		primaryStage.show();
	}
	
	@FXML
	private void pressSupprimer() throws IOException {
		Lieu select = lieux.getSelectionModel().getSelectedItem();
		
		if (select != null)
			lieux.getItems().remove(select);
	}
	
	@FXML 
	private void pressModifier() throws IOException {
		Lieu select = lieux.getSelectionModel().getSelectedItem();
		
		if (select != null) {
			ControleurModifierLieu.lieuAncien = select;
			
			Stage newStage = new Stage();
			
			Parent root = FXMLLoader.load(getClass().getResource("../../../../../fxml/ModifierLieu.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../../../../../css/application.css").toExternalForm());
			
			newStage.initModality(Modality.WINDOW_MODAL);
			newStage.initOwner(modifierLieu1.getScene().getWindow());
			newStage.setScene(scene);
			newStage.setTitle("Modifier Lieu");
			newStage.show();
		}
	}
	
	static public void ajouterLieu(Lieu nouveauLieu) {
		data.add(nouveauLieu);
	}
	
	static public void modifierLieu(Lieu ancienLieu, Lieu lieuModifier) {
		
		int i = 0;
		for (Lieu e : data) {
			
			if (e.equals(ancienLieu))
				break;
			
			i++;
		}
		
		data.set(i, lieuModifier);
	}

}
