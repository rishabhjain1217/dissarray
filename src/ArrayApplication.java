import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ArrayApplication extends Application {

    private MainPane main;

    public ArrayApplication()
    {
        super();
        this.main = new MainPane();

    }//hello

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader();
        BorderPane gamePane = fxmlLoader.load(getClass().getResource("GamePane.fxml").openStream());
        GameController gameController = (GameController) fxmlLoader.getController();

        gameController.setGameMode(GameController.GameMode.Both);
        gameController.start();


        Scene scene = new Scene(gamePane, 600, 400);
        primaryStage.setTitle("Array Game");

        primaryStage.setScene(scene);
        primaryStage.show();
        this.main.changeText();
    }

}
