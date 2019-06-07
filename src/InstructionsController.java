import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Rishabh Jain AKA CodeGod on 05 28, 2019 at 16:33
 */
public class InstructionsController implements Initializable {
    
    @FXML
    Button backButton;

    @FXML
    MenuItem backMenuItem;

    public Stage pStage;


    public InstructionsController(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void start(){
        backControl();
        menuItem();
    }

    private void backControl() {
        backButton.setOnAction(e -> { restart();});
    }

    private void menuItem() {
        backMenuItem.setOnAction(e -> {
            //RESTART CODE
            restart();
        });
    }

    private void restart(){
        pStage.close();
        Platform.runLater(new Runnable() {
            @Override public void run() {

                try{
                    Starter  s = new Starter();
                    s.start(new Stage());
                }
                catch (Exception e){
                    System.out.println("Error in InstructionsController" + e.getMessage());
                }
            }
        });
    }
}
