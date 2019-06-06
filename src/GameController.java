import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
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
//import javazoom.jl.decoder.JavaLayerException;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.*;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

public class GameController implements Initializable, KeyListener, Constants {

    @FXML
    Label scoreLabel, timeLabel, questionLabel;
    @FXML
    JFXButton nextButton;
    @FXML
    MenuItem newGameMenuItem, muteItem;
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
    private boolean muteText = true; //Unmute is begining statment


    private boolean ended = false;


    private QuestionGenerator generator;

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
        addKeyListener(this);
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

        defineBeginingMute();
        timesRun = 0;
        this.generator = new QuestionGenerator();
        this.newQuestion();
        nextQuestion();
        score = 0;
        muteItem();
        menuItem();

        //scoreLabel.textProperty().bind(new SimpleIntegerProperty(score).asString());
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
        newGameMenuItem.setOnAction(e -> {
                //RESTART CODE
            if(ended == false) {
                ended = true;
                pStage.close();
            }
                restart();
        });
    }//ninja "we be flossin"

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

                case ArrayList:
                    this.timeRemaining = this.renderArrayList();
                    this.timeLabel.setText("Time: " + 0 + ":" + timeRemaining);
                    break;

                case TwoList:
                    this.timeRemaining = this.renderTwoList();
                    this.timeLabel.setText("Time: " + 0 + ":" + timeRemaining);
                    break;

                case OneList:
                    this.timeRemaining = this.renderOneList();
                    this.timeLabel.setText("Time: " + 0 + ":" + timeRemaining);
                    break;

                case Three:
                    //this.timeRemaining =

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
                                decreaseTime();
                            });

                        }
                    },10, 1000);
                }
        }
        ++timesRun;

    }

    private void decreaseTime()
    {
        --this.timeRemaining;
        if (this.timeRemaining < 0) {
            timeLabel.setText("YOU DONE");
            return;
        }
        if (this.timeRemaining == 0) {
            //this.timer.cancel();
            timeLabel.setText("DONE");
            //corrected();

            //if(ended == false) {
             //   endGame();
             //   ended = true;
           // }
            nextButton.fire();
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
        OneDimQuestion q = ((OneDimQuestion) this.generator.generateOneDim(difficulty,score));
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
            questionLabel.setStyle("-fx-font-size: 32");
        }
        else{
            questionLabel.setStyle("-fx-font-size: 43");
        }
        TwoDimPane pane = new TwoDimPane(q);
        currentP = pane;

        this.renderPane.getChildren().setAll(pane);
        //this.renderPane.setPadding(new Insets(paddingY, paddingX, paddingY, paddingX));
        this.renderPane.setPadding(new Insets(10, 10, 20, 10));
    }

    private int renderTwoDim()
    {
        TwoDimQuestion q = ((TwoDimQuestion) this.generator.generateTwoDim(difficulty,score));
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

    public void renderArrayList(ArrayListQuestion q){
        double paddingX = (600.0 - q.getArrayLength()*IndexButton.BUTTON_SIZE) / 2;
        double paddingY = (400.0 - IndexButton.BUTTON_SIZE) / 2;
        currentQ = q;
        this.questionLabel.setText(q.question);
        ArrayPane pane = new ArrayPane(q) {
            @Override
            void render() {
                //this.setAlignment(Pos.CENTER);
                this.setMaxSize(400, 50);
                //this.getStylesheets().add(getClass().getResource("checkBoxStyle.css").toExternalForm());

                int length = ((ArrayListQuestion) this.question).getArrayLength();
                for (int i = 0; i < length; ++i) {
                    this.indexButtons.add(new IndexButton(new OneDimIndex(i)));
                    this.add(this.indexButtons.get(i).getButton(), 30 + i, 0);
                }
            }
        };
        currentP = pane;
        this.renderPane.getChildren().setAll(pane);
        this.renderPane.setPadding(new Insets(paddingY, paddingX, paddingY, paddingX));
    }
    public int renderArrayList(){

            ArrayListQuestion q = (ArrayListQuestion)this.generator.generateArrayList(difficulty,score);
            this.renderArrayList(q);
            return q.getTimeForQuestion();

    }

   private int renderRandom() //Creates random question of the specified difficulty
    {
        Question q = this.generator.generateRandom(difficulty,score);
        if (q instanceof OneDimQuestion) {
            this.renderOneDim(((OneDimQuestion) q));
            return ((OneDimQuestion) q).getTimeForQuestion();
        } else {
            this.renderTwoDim(((TwoDimQuestion) q));
            return ((TwoDimQuestion) q).getTimeForQuestion();
        }
    }

    private int renderOneList(){
        Question q = this.generator.generateOneList(difficulty,score);

        if (q instanceof OneDimQuestion) {
            this.renderOneDim(((OneDimQuestion) q));
            return ((OneDimQuestion) q).getTimeForQuestion();
        } else {
            this.renderArrayList(((ArrayListQuestion) q));
            return ((ArrayListQuestion) q).getTimeForQuestion();
        }
    }

    private int renderTwoList(){ //Array List and Two Dim game mode
        Question q = this.generator.generateTwoList(difficulty,score);

        if (q instanceof TwoDimQuestion) {
            this.renderTwoDim(((TwoDimQuestion) q));
            return ((TwoDimQuestion) q).getTimeForQuestion();
        } else {
            this.renderArrayList(((ArrayListQuestion) q));
            return ((ArrayListQuestion) q).getTimeForQuestion();
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
                    AudioClip note = new AudioClip(this.getClass().getResource(SoundLoader.getInstance().getCorrect()).toString());
                    note.play(); //Plays sound of correctness
                }
                newQuestion();
            }
            else{
                if(soundStatus.equals(SoundEnum.On)) {
                    AudioClip note = new AudioClip(this.getClass().getResource(SoundLoader.getInstance().getWrong()).toString());
                    note.play(); //Plays song of you being wrong
                }

                corrected();
                if(ended == false) {
                    endGame();
                    ended = true;
                }


            }
        });
        //nextButton.setOnAction(e -> newQuestion());
    }

    private void corrected() {
        nextButton.setDisable(true);
        ArrayList<Index> correct = currentQ.correctIndices;
        for (Node node : currentP.getChildren()) {
            ((IndexButton) (node)).getButton().setSelected(false);
        }
        for (Index x : correct) {
            for (Node node : currentP.getChildren()) {
                if (((IndexButton) (node)).getIndex().equals(x)) {
                    //((IndexButton) (node)).getButton().setSelected(true);
                    ((IndexButton) (node)).getButton().getStyleClass().removeAll();
                    ((IndexButton) (node)).getButton().getStyleClass().add("correct");
                   // Index i = ((IndexButton)(node)).getIndex();
                    ((IndexButton) (node)).getButton().setSelected(true);
                }
            }
        }
    }

    private boolean check() {//Checks if answer is correct
        ArrayList<Index> selected = new ArrayList<>();

        for (Node node: currentP.getChildren()){
            if(((IndexButton)(node)).getButton().isSelected()){
                selected.add(((IndexButton)(node)).getIndex());
            }
        }
        return currentQ.checkAnswer(selected);
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
                    System.out.println(e);
                }
            }
        });
        pStage.close();
    }

    private void endGame(){
        if(ended == true){
            return;
        }

        ArrayPane current = (ArrayPane) this.renderPane.getChildren().get(0);
        current.disableButtons();

        Task<Void> fiveSecDelay = new Task<Void>() {
            @Override
            protected Void call() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                }
                return null;
            }
        };
        fiveSecDelay.setOnSucceeded(event -> {
            fade();
        });

        new Thread(fiveSecDelay).start();
    }

    public void fade(){
        newGameMenuItem.setDisable(false);
        FadeTransition fadeOutTransition = new FadeTransition(Duration.millis(1500), GamePane);
        fadeOutTransition.setFromValue(1.0);
        fadeOutTransition.setToValue(0.0);
        fadeOutTransition.play();
        fadeOutTransition.setOnFinished((ActionEvent actionEvent) -> finish());
        newGameMenuItem.setDisable(true);
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
            pStage.setTitle(TITLE_OF_GAME);
            pStage.setScene(scene);

            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            pStage.setX((primScreenBounds.getWidth() - scene.getWidth()) / 2);
            pStage.setY((primScreenBounds.getHeight() - scene.getHeight()) / 2);

            pStage.show();
        } catch (Exception ex) {
            System.out.println("Something went wrong with finishing the game");
        }

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

//Testing key presses
    @Override
    public void keyTyped(KeyEvent e) {
       //System.out.println("hello1wds");
    }

    @Override
    public void keyPressed(KeyEvent e) {

        System.out.println("hello");
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_ENTER){

            System.out.println("You pressed the fire button");
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}