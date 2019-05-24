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

        int length = ((OneDimQuestion) this.question).getArrayLength();
        for (int i = 0; i < length; ++i) {
            this.indexButtons.add(new IndexButton(new OneDimIndex(i)));
            this.add(this.indexButtons.get(i).getButton(), i, 0);
        }
    }

}
