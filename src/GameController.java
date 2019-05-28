import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

public class GameController implements Initializable {

    @FXML
    Label scoreLabel, timeLabel, questionLabel;
    @FXML
    Button nextButton, errorButton;
    @FXML
    MenuItem newGameMenuItem;
    @FXML
    Pane renderPane;

    private GameMode mode; //1d questions or 2d questions
    private QuestionType difficulty; //Element or Range and Element questions given
    private TimerEnum timerStatus; //Is the timer on or off

    private QuestionGenerator generator;

    private final String correctSound = "CorrectSound.wav"; //Sound file for the correct answer


    private Timer timer;
    private int timeRemaining;

    private Question currentQ;
    private Pane currentP;
    private int timesRun;

    private int score;

    public Stage pStage;

    public GameController()
    {
        this.generator = new QuestionGenerator();
    }

    public void setGameMode(GameMode mode)
    {
        this.mode = mode;
    } //CHANGE THIS

    public void setDifficulty(QuestionType difficulty){
        this.difficulty = difficulty;
    }

    public void setTimerStatus(TimerEnum timerStatus){this.timerStatus = timerStatus;}

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }

    public void start()
    {

        timesRun = 0;
        this.generator = new QuestionGenerator();
        this.newQuestion();
        nextQuestion();
        score = 0;

        menuItem();

        //scoreLabel.textProperty().bind(new SimpleIntegerProperty(score).asString());
    }

    private void menuItem() {
        newGameMenuItem.setOnAction(e -> {
                //RESTART CODE
                pStage.close();
                Platform.runLater(new Runnable() {
                    @Override public void run() {

                        try{
                            Starter  s = new Starter();
                            s.start(new Stage());
                        }
                        catch (Exception e){
                            System.out.println("Wassup 2.0");
                        }
                    }
                });
        });
    }

    private void newQuestion()
    {
        switch (this.mode) {
            case OneDim:
                this.timeRemaining = this.renderOneDim();
                this.timeLabel.setText("Time: " + 0 + ":" + timeRemaining);
                break;
            case TwoDim:
                //timeGiven =
                this.timeRemaining = this.renderTwoDim();
                this.timeLabel.setText("Time: " + 0 + ":" + timeRemaining);
                break;
            case Both:
                this.timeRemaining = this.renderRandom();
                this.timeLabel.setText("Time: " + 0 + ":" + timeRemaining);
                break;
            default:
                this.timeRemaining = (this.renderOneDim());
                this.timeLabel.setText("Time: " + 0 + ":" + timeRemaining);
                break;
        }

        if(timerStatus.equals(TimerEnum.On)) {

                this.timer = new Timer();

                if (timesRun < 1) {
                    timer.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {

                            Platform.runLater(() -> {
                                decreaseTime();
                            });

                        }
                    },10, 1000);
                }
        }
        ++timesRun;

        //nextQuestion();
    }

    private void decreaseTime()
    {
        --this.timeRemaining;
        if (this.timeRemaining == 0) {
            this.timer.cancel();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("YOU DONE"+ "     Score: " + score);

            alert.showAndWait();
            System.exit(1);
        }
        int minutes = this.timeRemaining / 60;
        int seconds = this.timeRemaining % 60;
        String secondsFormat = seconds < 10 ? "0" + seconds : "" + seconds;
        this.timeLabel.setText("Time: " + minutes + ":" + secondsFormat);
    }

    private void renderOneDim(OneDimQuestion q)
    {
        currentQ = q;
        this.questionLabel.setText(q.question);
        OneDimPane pane = new OneDimPane(q);
        currentP = pane;
        this.renderPane.getChildren().setAll(pane);
    }


    private int renderOneDim()
    {
        OneDimQuestion q = ((OneDimQuestion) this.generator.generateOneDim(difficulty));
        this.renderOneDim(q);
        return q.getTimeForQuestion();
    }

    private void renderTwoDim(TwoDimQuestion q)
    {
        currentQ = q;
        this.questionLabel.setText(q.question);
        TwoDimPane pane = new TwoDimPane(q);
        currentP = pane;
        this.renderPane.getChildren().setAll(pane);
    }

    private int renderTwoDim()
    {
        TwoDimQuestion q = ((TwoDimQuestion) this.generator.generateTwoDim(difficulty));
        this.renderTwoDim(q);
        return q.getTimeForQuestion();
    }

   private int renderRandom() //Creates random question of the specified difficulty
    {
        Question q = this.generator.generateRandom(difficulty);
        if (q instanceof OneDimQuestion) {
            this.renderOneDim(((OneDimQuestion) q));
            return ((OneDimQuestion) q).getTimeForQuestion();
        } else {
            this.renderTwoDim(((TwoDimQuestion) q));
            return ((TwoDimQuestion) q).getTimeForQuestion();
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


                AudioClip note = new AudioClip(this.getClass().getResource(correctSound).toString());
                note.play();
                newQuestion();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("YOU MADE A MISTAKE" + "     Score: " + score);

                alert.showAndWait();
                System.exit(1);
            }
        });
        //nextButton.setOnAction(e -> newQuestion());
    }

    private boolean check() {//Checks if answer is correct
        ArrayList<Index> selected = new ArrayList<>();

        for (Node node: currentP.getChildren()){
            if(((IndexButton)(node)).getButton().isSelected()){
                selected.add(((IndexButton)(node)).getIndex());
                System.out.println(((IndexButton)(node)).getIndex());
            }
        }
        //System.out.println(selected.size());
        System.out.println(currentQ.checkAnswer(selected));
        return true;
        //return true;

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