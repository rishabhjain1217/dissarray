import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HowToPlayController {
    @FXML
    Button backButton;

    @FXML
    MenuItem goMenuItem;

    public Stage pStage;

    public void start() {

        goBack();
        menuItem();
    }

    private void menuItem() {
        goMenuItem.setOnAction(e -> {
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

    public void goBack(){

        backButton.setOnAction(e -> {
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
}



