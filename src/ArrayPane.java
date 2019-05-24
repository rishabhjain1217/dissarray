import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

abstract class ArrayPane extends GridPane {

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

    abstract void render();

}
