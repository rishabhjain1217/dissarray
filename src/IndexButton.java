import javafx.geometry.Pos;
import javafx.scene.control.ToggleButton;

public class IndexButton extends ToggleButton{
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

    public IndexButton(Index index)
    {
        super();
        this.index = index;
        this.setPrefSize(25, 25);
        this.setAlignment(Pos.CENTER);
    }

    public ToggleButton getButton() {
        return this;
    }

    public Index getIndex() {
        return index;
    }
}
