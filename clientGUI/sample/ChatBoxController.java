package sample;

import client.ChatClient;
import common.ChatIF;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class ChatBoxController implements ChatIF {
    //Class variables
    /**
     *
     */
    //Instance variables
    /**
     *
     */
    ChatClient client;
    String loginID;
    String host;
    int port;
    /**
     *
     */
    public TextArea text;
    /**
     *
     */
    public Button button;
    /**
     *
     */

    public TextField textField;

    /**
     *
     */



    public void transferMessage(String loginID, int port, String host) {
        this.loginID = loginID;
        this.port = port;
        this.host = host;
    }



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

    public void display(String string){
        if(!text.getText().equals("")) {
            text.appendText("\n" + string);
        }else{
            text.appendText(string);
        }
    }

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
            System.err.println("could not initialize the new window.");
        }
    }

    public void onEnter(ActionEvent ae){
        client.handleMessageFromClientUI(textField.getText());
        textField.setText("");
    }



}
