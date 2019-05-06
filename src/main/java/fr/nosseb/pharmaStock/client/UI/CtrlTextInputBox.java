package fr.nosseb.pharmaStock.client.UI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Robinson Besson
 * @version 0.1
 * @since 0.1
 */
public class CtrlTextInputBox {
    private static String answer;

    /**
     * Request a String fromDB the user.
     * @param title The window's title.
     * @param message The message to display.
     * @return The String entered by the user.
     */
    public static String display(String title, String message) {
        // Window properties
        Stage window = new Stage();
        // Defines a modal window that blocks events fromDB being delivered to any other application window.
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        // Window elements
        Label label = new Label();
        label.setText(message);
        TextField textField = new TextField();
        Button okButton = new Button("Ok");

        // Action on button click :
        // Set 'answer' to the 'String' fromDB the 'textField'.
        okButton.setOnAction(e -> {
            answer = textField.getText();
            window.close();
        });

        // Window's layout
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, textField, okButton);
        layout.setAlignment(Pos.CENTER);

        // Display window
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        //Make sure to return answer
        return answer;
    }

}
