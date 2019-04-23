package fr.nosseb.pharmaStock.client.UI;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controleur de la page d'acceuil
 * 
 * @author Loan Veyer
 * @date 26/03/2019
 *
 */

public class ControleurMenuPrincipale{
	
	@FXML
	private Button equipement1;
	
	@FXML
	private Button lieu1;
	
	@FXML
	private Button ficheSortie1;
	
	@FXML
	public void pressEquipement() throws IOException {
		Stage fenetrePrincipale = (Stage)equipement1.getScene().getWindow();
		fenetrePrincipale.close();
		
		Stage primaryStage = new Stage();
		
		Parent root = FXMLLoader.load(getClass().getResource("../../../../../fxml/Equipement.fxml"));
		Scene scene = new Scene(root);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Equipement");
		primaryStage.show();
	}

	@FXML
	public void pressLieu() throws IOException {
		Stage fenetrePrincipale = (Stage)lieu1.getScene().getWindow();
		fenetrePrincipale.close();
		
		Stage primaryStage = new Stage();
		
		Parent root = FXMLLoader.load(getClass().getResource("../../../../../fxml/Lieu.fxml"));
		Scene scene = new Scene(root);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Lieu");
		primaryStage.show();
	}
	
	@FXML
	public void pressFicheSortie() throws IOException {
		Stage fenetrePrincipale = (Stage)ficheSortie1.getScene().getWindow();
		fenetrePrincipale.close();
		
		Stage primaryStage = new Stage();
		
		Parent root = FXMLLoader.load(getClass().getResource("../../../../../fxml/FicheSortie.fxml"));
		Scene scene = new Scene(root);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Fiche de sortie");
		primaryStage.show();
	}

//	@Override
//	public void initialize(URL url, ResourceBundle rb) {
//		// TODO : Cleanup debug
//		System.out.println("START ControleurEquipement");
//	}
}
