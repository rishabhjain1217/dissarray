/**
 * Created by Rishabh Jain AKA CodeGod on 05 24, 2019 at 13:31
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
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

        Text text = new Text();
        text.setFont(Font.font(14));
        text.setFill(Color.GOLD);
        text.setTranslateX(336);
        text.setTranslateY(350);
//hi
        menuPane.getChildren().add(text);

        menuController.pStage = primaryStage;

        menuController.start();



        Scene scene = new Scene(menuPane,600, 400);
        scene.getStylesheets().add("checkBoxStyle.css");

        primaryStage.setTitle("Diss-Array V1.0");

        primaryStage.setScene(scene);

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - scene.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - scene.getHeight()) / 2);


        primaryStage.show();

    }


}
