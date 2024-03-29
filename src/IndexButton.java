import com.jfoenix.controls.JFXCheckBox;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;

import java.util.Random;

public class IndexButton extends JFXCheckBox {

    private String [] colors = {
            "F9FF33", "FFAF33","FF3333","F633FF","FF3386","DB88FF","FCFA7F","FCBE7F","ACCA71","C8E718","E596E4","FF5D00"
    };


    private Index index;
    public static double BUTTON_SIZE = 70;

    public IndexButton(Index index)
    {
        super();
        this.index = index;
        //this.setPrefSize(25, 25);
        this.setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
        this.setAlignment(Pos.CENTER);
        this.setStyle( "-jfx-checked-color:"+ getRandomColor() + ";" +
                "-jfx-unchecked-color:white;" + "-fx-padding: 10 10 10 10;" );

    }

    public CheckBox getButton() {
        return this;
    }

    public Index getIndex() {
        return index;
    }

    public String getRandomColor(){
        Random r = new Random();
        return colors[r.nextInt(10)];
    }

}

