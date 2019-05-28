/**
 * Created by Rishabh Jain AKA CodeGod on 05 24, 2019 at 13:31
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.beans.binding.Bindings;

import javafx.scene.paint.Color;

import javafx.scene.text.Font;
import javafx.scene.text.Text;


import java.rmi.server.ExportException;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Text;

public class Starter extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();

        BorderPane menuPane = fxmlLoader.load(getClass().getResource("MenuPane.fxml").openStream());
        MenuController menuController = (MenuController) fxmlLoader.getController();

        TSwitch toggle = new TSwitch();
        toggle.setTranslateX(330);
        toggle.setTranslateY(360);

        Text text = new Text();
        text.setFont(Font.font(14));
        text.setFill(Color.GOLD);
        text.setTranslateX(336);
        text.setTranslateY(350);
        text.textProperty().bind(Bindings.when(toggle.switchedOnProperty()).then("HARD").otherwise("EASY"));

        menuPane.getChildren().addAll(toggle, text);

        menuController.pStage = primaryStage;
        menuController.tSwitch = toggle;

        menuController.start();

        Scene scene = new Scene(menuPane, 600, 400);
        primaryStage.setTitle("Array Game");

        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
