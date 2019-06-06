import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class Starter extends Application implements Constants{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        /**Loads the MenuPane.fxml*/
        FXMLLoader fxmlLoader = new FXMLLoader();

        BorderPane menuPane = fxmlLoader.load(getClass().getResource("MenuPane.fxml").openStream());
        MenuController menuController = (MenuController) fxmlLoader.getController();

        menuController.pStage = primaryStage;

        menuController.start();

        Scene scene = new Scene(menuPane,600, 400);
        scene.getStylesheets().add("checkBoxStyle.css");

        primaryStage.setTitle(TITLE_OF_GAME);

        primaryStage.setScene(scene);

        /** Centers stage in the middle of the user's screen*/
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - scene.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - scene.getHeight()) / 2);


        primaryStage.show();

    }


}
