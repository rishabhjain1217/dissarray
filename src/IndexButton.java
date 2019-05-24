import javafx.scene.control.ToggleButton;

public class IndexButton {

    private ToggleButton button;
    private Index index;

    public IndexButton(Index index)
    {
        this.index = index;
        this.button = new ToggleButton();
        this.button.setPrefSize(25, 25);
    }

    public ToggleButton getButton() {
        return button;
    }

    public Index getIndex() {
        return index;
    }

}
