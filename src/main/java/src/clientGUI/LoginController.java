package src.clientGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import src.clientGUI.ChatBoxController;

import java.io.IOException;
import java.net.ConnectException;


public class LoginController {

    //GUI variables
    /**
     *This is the equivalent of textField1 in login.fxml
     */
    public TextField textField1;
    /**
     *This is the equivalent of textField3 in login.fxml
     */
    public TextField textField3;
    /**
     *This is the equivalent of textField2 in login.fxml
     */
    public TextField textField2;

    @FXML
    /**
     *This button is the Login button, when pressed it activated the handleAction method.
     */
    Button button1;


    /**
     * This method is activated when the login button is pressed. This function
     * changes the window from the Login window to the Chatbox window as well as connects the chatBox
     * window to the ServerConsole.
     * @param event is the action of pressing the button
     * @throws IOException because it is creating new instances.
     */
    @FXML
    public void handleAction(ActionEvent event) throws IOException, ConnectException, NumberFormatException{
        try {
            /**
             * Creates a new FXML loader which load the fxml files to change stages and scenes.
             */
            FXMLLoader loader = new FXMLLoader(getClass().getResource("chatBox.fxml"));
            Parent rooted = loader.load();
            /**
             * this Function initializes the controller to the Chatbox and ties it to the variable controller.
             * from there I can access the other class as well as pass the information from the first window
             * to the next. If someone doesnt enter a loginID, it is User as default.
             */
            ChatBoxController controller = loader.getController();
            try {
                if (textField1.getText().equals("")) {
                    controller.transferMessage("User", Integer.parseInt(textField2.getText()), textField3.getText());
                } else {
                    controller.transferMessage(textField1.getText(), Integer.parseInt(textField2.getText()), textField3.getText());
                }
            }
            catch(NumberFormatException n){
                AlertBox.display("Error", "Enter a valid port.");
                return;
            }
            /**
             * This line initializes the ClientChat variable inside of ChatBoxController so it can start talking to the server.
             */

            controller.start();
            if(controller.getError()){
                controller.setError(false);
                return;
            }


            /**
             * The rest of the code changes the scene size and the stage so it can change from the Login to the ChatBox.
             */
            Scene scene = new Scene(rooted, 600, 400);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setTitle("SimpleChat");
            window.setScene(scene);
            window.show();
        }
        catch(IOException e){
            System.err.println(e);

        }



    }

    @FXML
    public void handleClose(ActionEvent event){
        System.exit(0);
    }

}
