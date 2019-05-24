import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Duration;


import java.util.*;
import java.util.TimerTask;

public class MainPane extends GridPane {

    private Text timer, score, question;
    private Button error, next;
    private GridPane buttons;

    private static final Integer STARTTIME = 15;
    private Timeline timeline;
    private IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);

    public MainPane()
    {
        super();
        this.initialize();
        this.mountComponents();
    }

    private void initialize()
    {
        this.timer = new Text(STARTTIME.toString());
        this.score = new Text("0");
        this.question = new Text("Question");
        this.error = new Button();
        this.next = new Button();
        this.buttons = new GridPane();
        timer.textProperty().bind(timeSeconds.asString());
        error.setOnAction(e -> {
            timer();
        });

    }

    private void mountComponents()
    {
        this.add(this.timer, 0,0);
        this.add(this.score, 2, 0);
        this.add(this.question, 1, 0);
        this.buttons.add(this.error, 0, 0);
        this.buttons.add(this.next, 1, 0);
        this.add(this.buttons,2, 1);
    }

    public void changeText(){
        question.setText("THIS TEXT HAS BEEN CHANGED");
    }

    public void timer() {
        if (timeline != null) {
            timeline.stop();
        }
        timeSeconds.set(STARTTIME);
        timeline = new Timeline();
        KeyValue kv = new KeyValue(timeSeconds, 0);
        KeyFrame kf = new KeyFrame(Duration.seconds(STARTTIME + 1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.playFromStart();

        timeline.setOnFinished(e -> finish());
        //rr
    }

    public void finish(){
        System.out.println("YOU ARE DONE");
    }

}
