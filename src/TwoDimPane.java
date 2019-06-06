import javafx.scene.layout.GridPane;

/**
 * Created by Rishabh Jain AKA CodeGod on 05 22, 2019 at 08:15
 */
public class TwoDimPane extends ArrayPane {


    public TwoDimPane(TwoDimQuestion question)
    {
        super(question);
    }

    /** Create ArrayList with IndexButtons */
    @Override
    void render() {
        int rows = ((TwoDimQuestion) this.question).getRows();
        int cols = ((TwoDimQuestion) this.question).getCols();

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                this.indexButtons.add(new IndexButton(new TwoDimIndex(i, j)));
                this.add(this.indexButtons.get(i * cols + j).getButton(), j, i);
            }
        }

    }
}
