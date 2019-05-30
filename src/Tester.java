import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by Rishabh Jain AKA CodeGod on 05 21, 2019 at 12:48
 */
public class Tester extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");


        btn.setOnAction(e -> {
            //btn.setDisable(true);
            System.out.println("Hello World!");
        });

        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: powderblue;");
        btn.setStyle("-fx-background-color: powderblue;");
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
