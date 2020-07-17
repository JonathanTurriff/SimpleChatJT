package sample;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ChatBoxController {

    public TextArea text;
    public Button button;
    public TextArea text1;
    public TextField textField;
    public void handleClick(){
        if(!text.getText().equals("")){
            text.appendText("\n"+text1.getText());
            text1.setText("");

        }else{
            text.appendText(text1.getText());
            text1.setText("");
        }
    }

    public void handleClick2(){
        if(!text.getText().equals("")){
            text.appendText("\n"+textField.getText());
            textField.setText("");

        }else{
            text.appendText(textField.getText());
            textField.setText("");
        }
    }


}
