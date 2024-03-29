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
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
//import javazoom.jl.decoder.JavaLayerException;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

/**
 * Created by Rishabh Jain AKA CodeGod on 05 24, 2019 at 09:26
 */
public class MenuController implements Initializable,ColorConstants, Constants {


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
    private boolean onTwo = false;
    private boolean muteText = true; //Unmute is begining statment

    public MenuController(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if(SoundLoader.getInstance().alternateSounds){
            titleText.setTextFill(Color.web(PURPLE));
        }
        if(BackgroundMusic.getInstance().alternateSounds){
            soundToggle.setToggleColor(Color.web(BLACK));
        }


        defineBeginingMute();
        titleText.setOnMouseClicked(event -> {
            ++clickCount;
            if (clickCount == 10) {
                if(on == false) {
                    SoundLoader.getInstance().useAlternate();
                    titleText.setTextFill(Color.web(PURPLE));
                    on = true;
                }
                else{
                    on = false;
                    SoundLoader.getInstance().useRegular();
                    titleText.setTextFill(Color.web(BLACK));
                }
                clickCount = 0;
            }
        });

        soundToggle.setOnMouseClicked(event -> {
            ++count;
            if (count == 10) {
                if(onTwo == false) {
                    BackgroundMusic.getInstance().useAlternate();
                    soundToggle.setToggleColor(Paint.valueOf(BLACK));
                    onTwo = true;
                }
                else{
                    onTwo = false;
                    BackgroundMusic.getInstance().useRegular();
                    soundToggle.setToggleColor(Paint.valueOf(PURPLE));

                }
                count = 0;
            }
        });
    }

    public void start(){

        ButtonLoader bl = ButtonLoader.getInstance();//new instance of Button Loader

        //Sets the toggle buttons to the previously selected states
        oneDimToggle.setSelected(bl.getOneDim());
        twoDimToggle.setSelected(bl.getTwoDim());
        timerToggle.setSelected(bl.getTimer());
        hardModeToggle.setSelected(bl.getLoops());
        soundToggle.setSelected(bl.getSound());
        arraylistToggle.setSelected(bl.getArrList());

        //Changes the ButtonLoader boolean variables when the respective toggle button is clicked
        oneDimToggle.setOnAction(e -> bl.setOneDim(!bl.getOneDim()));
        twoDimToggle.setOnAction(e -> bl.setTwoDim(!bl.getTwoDim()));
        timerToggle.setOnAction(e -> bl.setTimer(!bl.getTimer()));
        hardModeToggle.setOnAction(e -> bl.setLoops(!bl.getLoops()));
        soundToggle.setOnAction(e -> bl.setSound(!bl.getSound()));
        arraylistToggle.setOnAction(e -> bl.setArrList(!bl.getArrList()));

        menuItem();
        muteItem();
        startButton();
        quitButton();
    }
//nh
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
                pStage.setTitle(TITLE_OF_GAME);


                pStage.setScene(scene);
                pStage.show();

            } catch (Exception ex) {
                System.out.println(ex);
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
                    pStage.setTitle(TITLE_OF_GAME);

                    scene.setOnKeyPressed(b -> {
                        if (b.getCode() == KeyCode.ENTER) {
                            gameController.nextButton.fire();
                        }
                    });
                    //hi

                    pStage.setScene(scene);

                    /** Centers game window on user's screen*/
                    Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
                    pStage.setX((primScreenBounds.getWidth() - scene.getWidth()) / 2);
                    pStage.setY((primScreenBounds.getHeight() - scene.getHeight()) / 2);

                    pStage.show();
                } catch (Exception ex) {
                    System.out.println(ex);
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
        if (!oneDimToggle.isSelected() && twoDimToggle.isSelected() && arraylistToggle.isSelected())
            return GameMode.TwoList;
        if (oneDimToggle.isSelected() && !twoDimToggle.isSelected() && arraylistToggle.isSelected())
            return GameMode.OneList;
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
        oneDimToggle.setSelected(true);
        twoDimToggle.setSelected(false);
        arraylistToggle.setSelected(false);
        try
        {
            Thread.sleep(1000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        return GameMode.OneDim;
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
            muteItem.setText("Unmute");
            muteText = true;
        }
        else{
            muteItem.setText("Mute");
            muteText = false;
        }

    }


    public void defineBeginingMute(){
        if(BackgroundMusic.getInstance().isRunning()){
            muteItem.setText("Mute");
            muteText = false;
        }
        else{
            muteItem.setText("Unmute");
            muteText = true;
        }
    }
}