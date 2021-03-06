package src.serverGUI;

import src.server.*;
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
 * This class is a Controller class for the fxml file called console.fxml in the resource folder.
 * It controls the console that pops up after you start the server after running ServerConsole.java
 *
 * @author Jonathan Turriff
 * @version JT.1.0
 */
public class ConsoleController implements ChatIF {

    //Instance variables
    private boolean error = false;
    /**
     *
     */
    private EchoServer server;

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
     *This is the text area where the console feedback gets displayed.
     */
    public TextArea text;
    /**
     *This button is the close button that lets you close the server and go back to the start screen.
     */
    public Button button;
    /**
     * This TextField is the area where you enter the information for it to show up in the server console and to other clients.
     */
    public TextField textField;


    /**
     *
     * @param port This is the port found in the textField of the start page.
     */
    public void transferPort(int port) {
        this.port = port;
    }


    /**
     * this functions initializes the EchoServer and lets it await Client Connections.
     */
    public void star() throws IOException {
        server = new EchoServer(port, this);
        server.listen();
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
            server.close();
            Parent rooted = FXMLLoader.load(getClass().getResource("start.fxml"));
            Scene scene = new Scene(rooted, 473, 183);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setTitle("SimpleChat Server Starter");
            window.setScene(scene);
            window.show();
        }
        catch(IOException e){
            System.err.println("Could not initialize the new window.");
        }
    }

    /**
     * Sends the message to the server when you press enter on the textField. Shows up in chatbox and clears textField
     * @param ae is the event of pressing enter
     */
    public void onEnter(ActionEvent ae){
        server.handleMessageFromServer(textField.getText());
        textField.setText("");
    }

    /**
     * Sets the error value.
     * @param err is the boolean value of the error.
     */
    public void setError(boolean err){
        error = err;
    }

    /**
     * Returns the value of error
     * @return the boolean value of error
     */
    public boolean getError(){
        return error;
    }



}
