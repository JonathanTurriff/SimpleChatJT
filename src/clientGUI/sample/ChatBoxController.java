package src.clientGUI.sample;

import src.client.ChatClient;
import src.common.ChatIF;
import javafx.event.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class is a Controller class for the fxml file called chatBox.fxml in the resource folder.
 * It controls the chatBox that pops up after you log into the
 *
 * @author Jonathan Turriff
 * @version JT.1.0
 */
public class ChatBoxController implements ChatIF {

    //Instance variables
    /**
     *This is the Chat Client by which the ChatBox will be connecting to the server.
     */
    private ChatClient client;
    /**
     * The loginID of anyone who tries to join a server or enters the SimpleChatClient.
     */
    private String loginID;
    /**
     *The host name of the server the client would be trying to join.
     */
    private String host;
    /**
     * the port at which the client would try and connect to.
     */
    private int port;

    //GUI variables
    /**
     *This is the text area where the chat gets displayed.
     */
    public TextArea text;
    /**
     *This button is the logout button that lets you exit the client and go back to the login screen.
     */
    public Button button;
    /**
     * This TextField is the area where you enter the information for it to show up in the ChatBox and to other clients.
     */
    public TextField textField;


    /**
     * This function is used to transfer the information from one controller to another.
     * So when I change scenes from the login Scene to the ChatBox scene. It calls this function
     * and transfers the information over to this one.
     * @param loginID This is the LoginID found in the first textField of the login page.
     * @param port This is the port found in the second textField of the login page.
     * @param host This is the host found in the third textField of the login page.
     */
    public void transferMessage(String loginID, int port, String host) {
        this.loginID = loginID;
        this.port = port;
        this.host = host;
    }


    /**
     * this functions initializes the ChatClient and connects it to the main server.
     */
    public void start(){
        try
        {
            client= new ChatClient(host, port, this, loginID);
            //display("Welcome to SimpleChat!");
        }
        catch(IOException exception)
        {
            System.out.println("Cannot open connection.  Awaiting command.");

        }
    }

    /**
     * This function implements ChatIF and displays anything
     * given as a string to it into the TextArea.
     * @param string is the message that is meant to be displayed in the TextArea.
     */
    public void display(String string){
        if(!text.getText().equals("")) {
            text.appendText("\n" + string);
        }else{
            text.appendText(string);
        }
    }

    /**
     * This function gives the Log out button its function.
     * It closes the connection of the client and changes windows back to the login screen.
     * @param event is the action of pressing the button.
     * @throws IOException Because it is initializing multiple different things within the function.
     */
    public void handleClick(ActionEvent event) throws IOException {
        try {
            client.closeConnection();
            Parent rooted = FXMLLoader.load(getClass().getResource("../resource/login.fxml"));
            Scene scene = new Scene(rooted, 250, 350);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setTitle("SimpleChat Login");
            window.setScene(scene);
            window.show();
        }
        catch(IOException e){
            System.err.println("Could not initialize the new window.");
        }
    }

    public void onEnter(ActionEvent ae){
        client.handleMessageFromClientUI(textField.getText());
        textField.setText("");
    }



}
