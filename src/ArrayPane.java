import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

abstract class ArrayPane extends GridPane {
//quicktest
    protected Question question;
    protected ArrayList<IndexButton> indexButtons;

    public ArrayPane(Question question)
    {
        super();
        this.setAlignment(Pos.CENTER);
        this.question = question;
        this.indexButtons = new ArrayList<>();
        this.render();
    }

    public void disableButtons()
    {
        for (IndexButton button : indexButtons) {
            button.setDisable(true);
        }
    }

    abstract void render();

}
