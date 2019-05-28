import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
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

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                try {
                    BorderPane gamePane = fxmlLoader.load(getClass().getResource("GamePane.fxml").openStream());
                    GameController gameController = (GameController) fxmlLoader.getController();
                    gameController.setGameMode(GameController.GameMode.Both);
                    gameController.start();

                    Scene scene = new Scene(gamePane, 600, 400);
                    pStage.setTitle("Array Game");

                    pStage.setScene(scene);
                    pStage.show();
                } catch (Exception ex) {
                    System.out.println("Wassup");
                }
            }
        });

    }

    public void quitButton(){
        quitButton.setOnAction(e -> pStage.close());
    }
}
