import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class OneDimPane extends ArrayPane {

    public OneDimPane(OneDimQuestion question)
    {
        super(question);
    }


    @Override
    void render() {

        this.setAlignment(Pos.CENTER);

        int j = 4;

        int length = ((OneDimQuestion) this.question).getArrayLength();
        for (int i = 0; i < length; ++i) {
            this.indexButtons.add(new IndexButton(new OneDimIndex(i)));
            this.add(this.indexButtons.get(i).getButton(), 60 +i, 0);
        }
    }

}
