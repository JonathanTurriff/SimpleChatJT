package sample;

import client.ChatClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginController {


    public TextField testField2;
    @FXML
    public TextField textField1;

    public TextField testField3;

    @FXML
    Button button1;

    @FXML
    public void handleAction(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../resource/chatBox.fxml"));

            Parent rooted = loader.load();

            ChatBoxController controller = loader.getController();
            controller.transferMessage(textField1.getText(),Integer.parseInt(testField2.getText()), testField3.getText());
            controller.start();
            Scene scene = new Scene(rooted, 600, 400);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setTitle("SimpleChat");
            window.setScene(scene);
            window.show();
        }
        catch(IOException ex){
            System.err.println(ex);
        }


    }

}
