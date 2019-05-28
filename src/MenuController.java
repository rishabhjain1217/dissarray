import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
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
    MenuItem instructionsMenuItem;

    @FXML
    Button startButton, quitButton;

    public Stage pStage;
    public TSwitch tSwitch;


    public MenuController(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void start(){


        oneCheckBox.setSelected(true);


        menuItem();
        startButton();
        quitButton();
    }

    private void menuItem() {
        instructionsMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                try {
                    AnchorPane instructionPane = fxmlLoader.load(getClass().getResource("HowToPlayPane.fxml").openStream());

                    Scene scene = new Scene(instructionPane, 600, 400);
                    pStage.setTitle("Array Game");


                    pStage.setScene(scene);
                    pStage.show();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
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
        quitButton.setOnAction(e ->  pStage.close());
    }

    public GameMode findGamemode(){
        if (oneCheckBox.isSelected() && twoCheckBox.isSelected())
            return GameMode.Both;
        if(oneCheckBox.isSelected() && !twoCheckBox.isSelected())
            return GameMode.OneDim;
        else
            return GameMode.TwoDim;
    }

    public QuestionType findDifficulty(){

        if (tSwitch.switchedOnProperty().getValue())
            return QuestionType.Range;
        else return QuestionType.Element;
    }

    public TimerEnum findTimerStatus(){
        if(timerCheckBox.isSelected()) {
            return TimerEnum.On;
        }
            else
                return TimerEnum.Off;
        }
    }

