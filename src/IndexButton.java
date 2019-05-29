import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXToggleButton;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleButton;

import static javafx.application.Application.setUserAgentStylesheet;

public class IndexButton extends JFXCheckBox {
/*
    private ToggleButton button;
    private Index index;

    public IndexButton(Index index)
    {
        this.index = index;
        this.button = new ToggleButton();
        this.button.setPrefSize(25, 25);
        button.setAlignment(Pos.CENTER);
    }

    public ToggleButton getButton() {
        return button;
    }



    public Index getIndex() {
        return index;
    }
*/

    private Index index;
    public static double BUTTON_SIZE = 65;

    public IndexButton(Index index)
    {
        super();
        this.index = index;
        //this.setPrefSize(25, 25);
        this.setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
        this.setAlignment(Pos.CENTER);
        this.setStyle("-jfx-checked-color:gold;" +
                "-jfx-unchecked-color:black;" );
    }

    public CheckBox getButton() {
        return this;
    }

    public Index getIndex() {
        return index;
    }
}

