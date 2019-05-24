import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class GameController implements Initializable {

    public enum GameMode {
        OneDim,
        TwoDim,
        Both
    }

    @FXML
    Label scoreLabel, timeLabel, questionLabel;
    @FXML
    Button nextButton, errorButton;
    @FXML
    MenuItem newGameMenuItem;
    @FXML
    Pane renderPane;

    private GameMode mode;
    private QuestionGenerator generator;

    private Timer timer;
    private int timeRemaining;

    public GameController()
    {
        this.generator = new QuestionGenerator();
    }

    public void setGameMode(GameMode mode)
    {
        this.mode = mode.Both;
    } //CHANGE THIS

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }

    public void start()
    {
        this.generator = new QuestionGenerator();
        this.newQuestion();
        nextQuestion();
    }

    private void newQuestion()
    {
        this.timeRemaining = 20;
        this.timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                Platform.runLater(() -> {
                    decreaseTime();
                });

            }
        }, 1000, 1000);
        switch (this.mode) {
            case OneDim:
                this.renderOneDim();
                break;
            case TwoDim:
                this.renderTwoDim();
                break;
            /*case Both:
                this.renderRandom();
                break;*/
            default:
                //this.renderRandom();
                this.renderOneDim();
                break;
        }
        //nextQuestion();
    }

    private void decreaseTime()
    {
        --this.timeRemaining;
        if (this.timeRemaining == 0) {
            this.timer.cancel();
        }
        int minutes = this.timeRemaining / 60;
        int seconds = this.timeRemaining % 60;
        String secondsFormat = seconds < 10 ? "0" + seconds : "" + seconds;
        this.timeLabel.setText("Time: " + minutes + ":" + secondsFormat);
    }

    private void renderOneDim(OneDimQuestion q)
    {

        this.questionLabel.setText(q.question);
        OneDimPane pane = new OneDimPane(q);
        this.renderPane.getChildren().add(pane);
    }

    private void renderOneDim()
    {
        OneDimQuestion q = ((OneDimQuestion) this.generator.generateOneDim());
        this.renderOneDim(q);
    }

    private void renderTwoDim(TwoDimQuestion q)
    {

        this.questionLabel.setText(q.question);
        TwoDimPane pane = new TwoDimPane(q);
        this.renderPane.getChildren().add(pane);
    }

    private void renderTwoDim()
    {
        TwoDimQuestion q = ((TwoDimQuestion) this.generator.generateTwoDim());
        this.renderTwoDim(q);
    }

    private void renderRandom()
    {
        Question q = this.generator.generateRandom();
        if (q instanceof OneDimQuestion) {
            this.renderOneDim(((OneDimQuestion) q));
        } else {
            this.renderTwoDim(((TwoDimQuestion) q));
        }
    }
   // public boolean checkIndex(){

    //}

    public void nextQuestion(){
        //if(true); //put check answers here
        nextButton.setOnAction(e -> {
            renderPane.getChildren().clear();
            //newQuestion();
        });
        nextButton.setOnAction(e -> newQuestion());
    }

        /*
    public boolean isCorrect(boolean [][] userInput){ // checks to see if a question is right, does not check for
        boolean correct = true;
        for(int i = 0; i < gameBoard.length;++i){
            for(int j = 0; j < gameBoard[0].length;++j){
                if(gameBoard[i][j] != userInput[i][j]){ correct = false;}
            }
        }
      return correct;

    }

     */


}