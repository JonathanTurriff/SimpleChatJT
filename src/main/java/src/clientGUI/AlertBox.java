package src.clientGUI;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This class is a AlertBox class that pops up when you get a ConnectionError.
 * Closes the system once you press the "OK" Button.
 *
 * @author Jonathan Turriff
 * @version JT.1.0
 */

public class AlertBox {
    /**
     * Displays an error window and closes the system after the press of the "OK" button.
     * @param title is what the window is going to be called.
     * @param message is the error message.
     */
    public static void display(String title, String message){

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label(message);
        Button closeButton = new Button("Ok");
        closeButton.setOnAction(e -> System.exit(0));
        VBox layout = new VBox();
        layout.getChildren().addAll(label,closeButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }
}
