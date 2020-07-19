package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * This class is a Controller class for the fxml file called start.fxml in the resource folder.
 * It lets you start a server from a GUI and transfers you to a page with the Console of the server.
 *
 * @author Jonathan Turriff
 * @version JT.1.0
 */
public class StartController {

    //GUI variables
    /**
     *This is the equivalent of textField1 in start.fxml, retrieves the port.
     */
    public TextField textField;

    @FXML
    /**
     *This button is the Start button, when pressed it activated the handleAction method which starts the server.
     */
    Button button1;


    /**
     * This method is activated when the start button is pressed. This function
     * changes the window from the start window to the server window as well as starts the server
     * and awaits the connection of a client.
     * @param event is the action of pressing the button
     * @throws IOException because it is creating new instances.
     */
    @FXML
    public void handleAction(ActionEvent event) throws IOException {
        try {

            /**
             * Creates a new FXML loader which load the fxml files to change stages and scenes.
             */
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../resource/console.fxml"));
            Parent rooted = loader.load();

            /**
             * Lets me transfer the port information from one scene to another using the transferPort function.
             */
            ConsoleController controller = loader.getController();
            controller.transferPort(Integer.parseInt(textField.getText()));

            /**
             * The rest of the code changes the scene size and the stage so it can change from the Login to the ChatBox.
             */
            Scene scene = new Scene(rooted, 600, 400);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setTitle("SimpleChat");
            window.setScene(scene);
            window.show();
            controller.star();
        }
        catch(IOException ex){
            System.err.println(ex);
        }


    }

    /**
     * This button is basically the X button of my application, this way I can exit the GUI safely.
     * @param event is the action of pressing the close button
     */
    @FXML
    public void handleClose(ActionEvent event){
        System.exit(0);
    }

}
