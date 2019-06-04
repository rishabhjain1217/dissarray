import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

/**
 * Created by Rishabh Jain AKA CodeGod on 05 24, 2019 at 09:26
 */
public class MenuController implements Initializable {
//comment

    @FXML
    Label titleText;

    @FXML
    MenuItem instructionsMenuItem, muteItem;

    @FXML
    JFXButton startButton, quitButton;

    @FXML
    JFXToggleButton oneDimToggle, twoDimToggle, timerToggle, hardModeToggle, soundToggle, arraylistToggle;

    public Stage pStage;

    private int clickCount = 0;
    private boolean on = false;

    private int count = 0;
    private boolean on2 = false;
    private boolean muteText = false;

    public MenuController(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titleText.setOnMouseClicked(event -> {
            ++clickCount;
            if (clickCount == 10) {
                if(on == false) {
                    SoundLoader.getInstance().useAlternate();
                    titleText.setTextFill(Color.web("#673ab7"));
                    on = true;
                }
                else{
                    on = false;
                    SoundLoader.getInstance().useRegular();
                    titleText.setTextFill(Color.web("#000000"));
                }
                clickCount = 0;
            }
        });

        /*soundToggle.setOnMouseClicked(event -> {
            ++count;
            if (count == 10) {
                if(on2 == false) {
                    SoundLoader.getInstance().useAlternate();
                    //titleText.setTextFill(Color.web("#673ab7"));
                    //soundToggle.setToggleColor("#ffffff");
                    on2 = true;
                }
                else{
                    on2 = false;
                    SoundLoader.getInstance().useRegular();
                    //titleText.setTextFill(Color.web("#000000"));
                }
                count = 0;
            }
        });*/
    }

    public void start(){

        oneDimToggle.setSelected(true);
        soundToggle.setSelected(true);

        menuItem();
        muteItem();
        startButton();
        quitButton();
    }

    private void muteItem() {
        muteItem.setOnAction(e -> {
            try{
                mute();
            }
            catch (Exception ex){
                System.out.println("Mute Broke");
            }
        });
    }

    private void menuItem() {
        instructionsMenuItem.setOnAction(e -> {
            FXMLLoader fxmlLoader = new FXMLLoader();
            try {
                VBox instructionsPane = fxmlLoader.load(getClass().getResource("Instructions.fxml").openStream());
                InstructionsController instructionsController = (InstructionsController) fxmlLoader.getController();
                instructionsController.pStage = pStage;
                instructionsController.start();

                Scene scene = new Scene(instructionsPane, 600, 400);
                pStage.setTitle("Diss-Array V1.0");


                pStage.setScene(scene);
                pStage.show();

            } catch (Exception ex) {
                System.out.println("Game Crashed 1");
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
                    gameController.setSound(findSoundStatus());
                    gameController.pStage = pStage;
                    gameController.start();

                    Scene scene = new Scene(gamePane, 1050, 750);
                    scene.getStylesheets().add("checkBoxStyle.css");
                    pStage.setTitle("Diss-Array V1.1");


                    pStage.setScene(scene);

                    Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
                    pStage.setX((primScreenBounds.getWidth() - scene.getWidth()) / 2);
                    pStage.setY((primScreenBounds.getHeight() - scene.getHeight()) / 2);

                    pStage.show();
                } catch (Exception ex) {
                    System.out.println("Game Crashed 2");
                }
            }
        });
    }

    public void quitButton(){
        quitButton.setOnAction(e ->  pStage.close());
    }

    public GameMode findGamemode(){
        if (!oneDimToggle.isSelected() && !twoDimToggle.isSelected() && arraylistToggle.isSelected())
            return GameMode.ArrayList;
        if (oneDimToggle.isSelected() && twoDimToggle.isSelected() && arraylistToggle.isSelected())
            return GameMode.Three;
        if (oneDimToggle.isSelected() && twoDimToggle.isSelected() && !arraylistToggle.isSelected()){
            return GameMode.Both;
        }
        if(oneDimToggle.isSelected() && !twoDimToggle.isSelected() && !arraylistToggle.isSelected()) {
            return GameMode.OneDim;
        }
        if(!oneDimToggle.isSelected() && twoDimToggle.isSelected() && !arraylistToggle.isSelected()) {
            return GameMode.TwoDim;
        }
        oneDimToggle.setSelected(false);
        twoDimToggle.setSelected(false);
        try
        {
            Thread.sleep(1000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        return GameMode.ArrayList;
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

    public SoundEnum findSoundStatus(){
        if(soundToggle.isSelected()) {
            return SoundEnum.On;
        }
        else
            return SoundEnum.Off;
    }

    public void mute(){
        BackgroundMusic.getInstance().mute();
        if(!muteText){
            muteItem.setText("UnMute");
            muteText = true;
        }
        else{
            muteItem.setText("Mute");
            muteText = false;
        }

    }
}