package src;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is the main class where the Server Starts,
 * All it does is launch the first GUI being start.fxml
 *
 * @author Jonathan Turriff
 * @version JT.1.0
 */
public class ServerConsole extends Application{



    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("serverGUI/start.fxml"));
        primaryStage.setTitle("SimpleChat Server Starter");
        primaryStage.setScene(new Scene(root, 473, 183));
        primaryStage.show();

    }


    public static void main(String[] args) {

        launch(args);
    }
}
