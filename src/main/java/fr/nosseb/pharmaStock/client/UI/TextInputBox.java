package fr.nosseb.pharmaStock.client.UI;


import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;

/**
 * @author Robinson Besson
 * @version 0.1
 * @since 0.1
 */
public class TextInputBox {

    /**
     *
     * @param title Window Title
     * @param message Message to display
     */
    public static void display (String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        Label label = new Label();
        label.setText(message);
        Button okButton = new Button("Ok");

    }

}
