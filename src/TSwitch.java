/**
 * Created by Rishabh Jain AKA CodeGod on 05 28, 2019 at 10:50
 */

import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;



public class TSwitch extends Parent {

    private BooleanProperty switchedOn = new SimpleBooleanProperty(false);

    private TranslateTransition translateAnimation = new TranslateTransition(Duration.seconds(0.25));
    private FillTransition fillAnimation = new FillTransition(Duration.seconds(0.25));

    private ParallelTransition animation = new ParallelTransition(translateAnimation, fillAnimation);

    public BooleanProperty switchedOnProperty() {
        return switchedOn;
    }

    public TSwitch() {
        Rectangle background = new Rectangle(50, 25);
        background.setArcWidth(25);
        background.setArcHeight(25);
        background.setFill(Color.WHITE);
        background.setStroke(Color.LIGHTGRAY);

        Circle trigger = new Circle(10);
        trigger.setCenterX(12);
        trigger.setCenterY(13);
        trigger.setFill(Color.WHITE);
        trigger.setStroke(Color.LIGHTGRAY);

        DropShadow shadow = new DropShadow();
        shadow.setRadius(1);
        trigger.setEffect(shadow);

        translateAnimation.setNode(trigger);
        fillAnimation.setShape(background);

        getChildren().addAll(background, trigger);

        switchedOn.addListener((obs, oldState, newState) -> {
            boolean isOn = newState.booleanValue();
            translateAnimation.setToX(isOn ? 50 - 24 : 0);
            fillAnimation.setFromValue(isOn ? Color.WHITE : Color.LIGHTGREEN);
            fillAnimation.setToValue(isOn ? Color.LIGHTGREEN : Color.WHITE);

            animation.play();
        });

        setOnMouseClicked(event -> {
            switchedOn.set(!switchedOn.get());
        });
    }
}