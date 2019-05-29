import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Rishabh Jain AKA CodeGod on 05 24, 2019 at 09:26
 */
public class MenuController implements Initializable {

    @FXML
    MenuItem instructionsMenuItem;

    @FXML
    JFXButton startButton, quitButton;

    @FXML
    JFXToggleButton oneDimToggle, twoDimToggle, timerToggle, hardModeToggle;

    public Stage pStage;


    public MenuController(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void start(){

        oneDimToggle.setSelected(true);

        menuItem();
        startButton();
        quitButton();
    }

    private void menuItem() {
        instructionsMenuItem.setOnAction(e -> {
               /* FXMLLoader fxmlLoader = new FXMLLoader();
                try {
                    AnchorPane instructionPane = fxmlLoader.load(getClass().getResource("HowToPlayPane.fxml").openStream());
                    HowToPlayController instructionController = (HowToPlayController) fxmlLoader.getController();
                    instructionController.pStage = pStage;
                    Scene scene = new Scene(instructionPane, 600, 400);
                    pStage.setTitle("Array Game");
                    instructionController.start();
                    pStage.setScene(scene);
                    pStage.show();
                } catch (Exception ex) {
                    System.out.println(ex);
                }*/
            FXMLLoader fxmlLoader = new FXMLLoader();
            try {
                VBox instructionsPane = fxmlLoader.load(getClass().getResource("Instructions.fxml").openStream());
                InstructionsController instructionsController = (InstructionsController) fxmlLoader.getController();
                instructionsController.pStage = pStage;
                instructionsController.start();

                Scene scene = new Scene(instructionsPane, 600, 400);
                pStage.setTitle("Array Game");


                pStage.setScene(scene);
                pStage.show();

            } catch (Exception ex) {
                System.out.println("Wassup");
            }
        });
    }

    public void startButton(){

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                try {
                    BorderPane gamePane = fxmlLoader.load(getClass().getResource("GamePane.fxml").openStream());
                    GameController gameController = (GameController) fxmlLoader.getController();
                    gameController.setGameMode(findGamemode());
                    gameController.setDifficulty(findDifficulty());
                    gameController.setTimerStatus(findTimerStatus());
                    gameController.pStage = pStage;
                    gameController.start();

                    Scene scene = new Scene(gamePane, 1200, 800);
                    pStage.setTitle("Array Game");


                    pStage.setScene(scene);
                    pStage.setFullScreen(true);
                    pStage.show();
                } catch (Exception ex) {
                    System.out.println("Wassup");
                }
            }
        });
    }

    public void quitButton(){
        quitButton.setOnAction(e ->  pStage.close());
    }

    public GameMode findGamemode(){
        if (oneDimToggle.isSelected() && twoDimToggle.isSelected())
            return GameMode.Both;
        if(oneDimToggle.isSelected() && !twoDimToggle.isSelected())
            return GameMode.OneDim;
        else
            return GameMode.TwoDim;
    }

    public QuestionType findDifficulty(){

        if (hardModeToggle.isSelected())
            return QuestionType.Range;
        else return QuestionType.Element;
    }

    public TimerEnum findTimerStatus(){
        if(timerToggle.isSelected()) {
            return TimerEnum.On;
        }
        else
            return TimerEnum.Off;
    }
}