package src;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * This is the main class where the Client Starts,
 * All it does is launch the first GUI being login.fxml
 *
 * @author Jonathan Turriff
 * @version JT.1.0
 */
public class ClientConsole extends Application{



    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("clientGUI/resource/login.fxml"));
        primaryStage.setTitle("SimpleChat Login");
        primaryStage.setScene(new Scene(root, 250, 350));
        primaryStage.show();

    }


    public static void main(String[] args) {

        launch(args);
    }
}
