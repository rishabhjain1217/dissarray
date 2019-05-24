import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Rishabh Jain AKA CodeGod on 05 24, 2019 at 09:26
 */
public class MenuController implements Initializable {

    @FXML
    CheckBox oneCheckBox, twoCheckBox, timerCheckBox;

    @FXML
    ToggleButton easyToggle, hardToggle;

    @FXML
    Button startButton, quitButton;

    public Stage pStage;


    public MenuController(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void start(){

        startButton();
    }

    public void startButton(){
        startButton.setOnAction(e -> {
                ArrayApplication aa = new ArrayApplication();
                aa.start();
        });

    }
}
