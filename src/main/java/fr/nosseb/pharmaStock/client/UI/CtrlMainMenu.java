package fr.nosseb.pharmaStock.client.UI;


import fr.nosseb.pharmaStock.client.Launcher;
import fr.nosseb.pharmaStock.client.utils;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.Objects;


/**
 * Controller for the home page.
 *
 * @author Loan Veyer
 * @date 26/03/2019
 *
 */
public class CtrlMainMenu {
    // Links to JavFX elements.
    @FXML private Button equipment1;
    @FXML private Button Location1;
    @FXML private Button ficheSortie1;

    /**
     * Launch window to view equipment upon user request.
     */
    @FXML public void pressEquipment() {
        CtrlEquipment ctrlEquipment = CtrlEquipment.caller();
    }

    /**
     * Launch window to view locations upon user request.
     */
    @FXML public void pressLocation(){
        CtrlLocation ctrlLocation = CtrlLocation.caller();
    }

    /**
     * Launch window to fill withdraw forms
     */
    /*@FXML public void pressFicheSortie()  {
        Stage fenetrePrincipale = (Stage)ficheSortie1.getScene().getWindow();
        // OPTIMISATION: Can we close the window later on to reduce the black screen lag ?
        fenetrePrincipale.close();
        Stage primaryStage = new Stage();

        URL fxml = getClass().getResource("../../../../../fxml/FicheSortie.fxml");
        Parent root = null;
        try {
            root = FXMLLoader.load(fxml);
        } catch (IOException e) {
            // EXCEPTION: Internal resource not found, hard crash expected.
            e.printStackTrace();
        }

        // FIXME : can we avoid the "Argument 'root' might be null" message ?
        Scene scene = new Scene(root);

        scene.getStylesheets().add(getClass().getResource("../../../../../application.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Fiche de sortie");
        primaryStage.show();
    }*/


    // DOCUMENTATION
    public static void caller() {
        Launcher.commonStage.setScene(utils.sceneGenerator("fxml/MainMenu.fxml").scene);
        Launcher.commonStage.setTitle("Menu Principal");
        Launcher.commonStage.show();
    }
}
