import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.net.URL;
import java.util.*;

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

    private Question currentQ;
    private Pane currentP;

    private int score;

    public GameController()
    {
        this.generator = new QuestionGenerator();
    }

    public void setGameMode(GameMode mode)
    {
        this.mode = mode;
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
        score = 0;
        //scoreLabel.textProperty().bind(new SimpleIntegerProperty(score).asString());
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
            /*Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("YOU DONE");

            alert.showAndWait();
            System.exit(1);*/
        }
        int minutes = this.timeRemaining / 60;
        int seconds = this.timeRemaining % 60;
        String secondsFormat = seconds < 10 ? "0" + seconds : "" + seconds;
        //this.timeLabel.setText("Time: " + minutes + ":" + secondsFormat);
    }

    private void renderOneDim(OneDimQuestion q)
    {
        currentQ = q;
        this.questionLabel.setText(q.question);
        OneDimPane pane = new OneDimPane(q);
        currentP = pane;
        this.renderPane.getChildren().setAll(pane);
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
        this.renderPane.getChildren().setAll(pane);
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

    public void nextQuestion(){//Hello
        //if(true); //put check answers here
        nextButton.setOnAction(e -> {
            //System.out.println(check());
            if(check()) {
                score++;
                scoreLabel.setText("Score: " + score);
                renderPane.getChildren().clear();
                newQuestion();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("YOU MADE A MISTAKE");

                alert.showAndWait();
                System.exit(1);
            }
        });
        //nextButton.setOnAction(e -> newQuestion());
    }

    private boolean check() {
        ArrayList<Index> selected = new ArrayList<>();

        for (Node node: currentP.getChildren()){
            if(((IndexButton)(node)).getButton().isSelected()){
                selected.add(((IndexButton)(node)).getIndex());
            }
        }
         return currentQ.checkAnswer(selected);

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