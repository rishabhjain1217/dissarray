import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.*;

public class GameController implements Initializable {

    @FXML
    Label scoreLabel, timeLabel, questionLabel;
    @FXML
    JFXButton nextButton;
    @FXML
    MenuItem newGameMenuItem;
    @FXML
    StackPane renderPane;
    @FXML
    BorderPane GamePane;

    @FXML
    VBox centerVBox;

    private GameMode mode; //1d questions or 2d questions
    private QuestionType difficulty; //Element or Range and Element questions given
    private TimerEnum timerStatus; //Is the timer on or off
    private SoundEnum soundStatus; // Is sound on or off

    private boolean ended = false;


    private QuestionGenerator generator;

    private final String correctSound = "CorrectSound.wav"; //Sound file for the correct answer
    private final String failSound = "IncorrectSound.wav"; //Sound file for the incorrect answer

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

    public void setSound(SoundEnum se){
        soundStatus = se;
    }

    public void setTimerStatus(TimerEnum timerStatus){this.timerStatus = timerStatus;}

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        this.centerVBox.setAlignment(Pos.CENTER);
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
                restart();
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

        if((timerStatus.equals(TimerEnum.Off))){
            this.timeLabel.setText("Time: N/A");
        }

        if(timerStatus.equals(TimerEnum.On)) {

                this.timer = new Timer();

                if (timesRun < 1) {
                    timer.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {

                            Platform.runLater(() -> {
                                if(!ended) {
                                    decreaseTime();
                                }
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
            timeLabel.setText("DONE");
           /* Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("YOU DONE"+ "     Score: " + score);

            alert.showAndWait();
            restart();*/
           endGame();
        }
        int minutes = this.timeRemaining / 60;
        int seconds = this.timeRemaining % 60;
        String secondsFormat = seconds < 10 ? "0" + seconds : "" + seconds;
        this.timeLabel.setText("Time: " + minutes + ":" + secondsFormat);
    }


    private void renderOneDim(OneDimQuestion q)
    {
        double paddingX = (600.0 - q.getArrayLength()*IndexButton.BUTTON_SIZE) / 2;
        double paddingY = (400.0 - IndexButton.BUTTON_SIZE) / 2;
        currentQ = q;
        this.questionLabel.setText(q.question);
        OneDimPane pane = new OneDimPane(q);
        currentP = pane;
        this.renderPane.getChildren().setAll(pane);
        this.renderPane.setPadding(new Insets(paddingY, paddingX, paddingY, paddingX));
    }


    private int renderOneDim()
    {
        OneDimQuestion q = ((OneDimQuestion) this.generator.generateOneDim(difficulty));
        this.renderOneDim(q);
        return q.getTimeForQuestion();
    }

    private void renderTwoDim(TwoDimQuestion q)
    {
        double paddingX = (600.0 - q.getCols()*IndexButton.BUTTON_SIZE) / 2;
        double paddingY = (400.0 - q.getRows()*IndexButton.BUTTON_SIZE) / 2;
        currentQ = q;
        this.questionLabel.setText(q.question);
        /*
        questionLabel.setStyle("-fx-font: 32 Nirmala_UI;" +
                "-fx-text-fill: black;");
        */
        if(q.getDifficulty().equals(QuestionType.Range)){
            questionLabel.setStyle("-fx-font: 32 Nirmala_UI;" +
                    "-fx-text-fill: black;");
        }
        else{
            questionLabel.setStyle("-fx-font: 43 Nirmala_UI;" +
                    "-fx-text-fill: black;");
        }
        TwoDimPane pane = new TwoDimPane(q);
        currentP = pane;

        this.renderPane.getChildren().setAll(pane);
        //this.renderPane.setPadding(new Insets(paddingY, paddingX, paddingY, paddingX));
        this.renderPane.setPadding(new Insets(10, 10, 20, 10));
    }

    private int renderTwoDim()
    {
        TwoDimQuestion q = ((TwoDimQuestion) this.generator.generateTwoDim(difficulty));
        /*if(q.getDifficulty().equals(QuestionType.Range)){
            questionLabel.setStyle("-fx-font: 38 Nirmala_UI;" +
                    "-fx-text-fill: black;");
        }
        else{
            questionLabel.setStyle("-fx-font: 43 Nirmala_UI;" +
                    "-fx-text-fill: black;");
        }*/
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

                if(soundStatus.equals(SoundEnum.On)) {
                    AudioClip note = new AudioClip(this.getClass().getResource(correctSound).toString());
                    note.play(); //Plays sound of correctness
                }
                newQuestion();
            }
            else{
                if(soundStatus.equals(SoundEnum.On)) {
                    AudioClip note = new AudioClip(this.getClass().getResource(failSound).toString());
                    note.play(); //Plays song of you being wrong
                }
                /*Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("YOU MADE A MISTAKE" + "     Score: " + score);

                alert.showAndWait();
                restart();
                 */
                endGame();
            }
        });
        //nextButton.setOnAction(e -> newQuestion());
    }

    private boolean check() {//Checks if answer is correct
        ArrayList<Index> selected = new ArrayList<>();

        for (Node node: currentP.getChildren()){
            if(((IndexButton)(node)).getButton().isSelected()){
                selected.add(((IndexButton)(node)).getIndex());
                //System.out.println(((IndexButton)(node)).getIndex());
            }
        }
        //System.out.println(selected.size());
        //System.out.println(currentQ.checkAnswer(selected));
        return currentQ.checkAnswer(selected);
        //return true;
    }

    public void restart(){
        //pStage.close();
        //timer = null;
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
        pStage.close();
    }

    private void endGame(){
        ended = true;
            FadeTransition fadeOutTransition = new FadeTransition(Duration.millis(1500), GamePane);
            fadeOutTransition.setFromValue(1.0);
            fadeOutTransition.setToValue(0.0);
            fadeOutTransition.play();
            fadeOutTransition.setOnFinished((ActionEvent event) -> {
                finish();
            });
    }

    private void finish() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            BorderPane gamePane = fxmlLoader.load(getClass().getResource("EndGame.fxml").openStream());
            EndGameController endGameController = (EndGameController) fxmlLoader.getController();
            endGameController.pStage = pStage;
            endGameController.score = score;
            endGameController.start();
            Scene scene = new Scene(gamePane, 600, 400);
            pStage.setTitle("Diss-Array V1.0");
            pStage.setScene(scene);

            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            pStage.setX((primScreenBounds.getWidth() - scene.getWidth()) / 2);
            pStage.setY((primScreenBounds.getHeight() - scene.getHeight()) / 2);

            pStage.show();
        } catch (Exception ex) {
            System.out.println("Wassup");
        }

    }


}