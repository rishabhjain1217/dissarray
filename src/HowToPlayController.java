import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HowToPlayController {
    @FXML
    Button backButton;

    public Stage pStage;

    public void start() {

        goBack();
    }

    public void goBack(){

        backButton.setOnAction(new EventHandler<ActionEvent>() {

            }
    }
}



