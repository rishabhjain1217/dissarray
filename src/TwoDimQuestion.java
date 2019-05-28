import java.util.ArrayList;
import java.util.Random;

public class TwoDimQuestion extends Question {

    private int rows, cols;

    public TwoDimQuestion(QuestionType difficulty)
    {
        super();
        this.generateQuestion(difficulty);
    }

    @Override
    void generateQuestion(QuestionType difficulty) {

        switch (difficulty) {
            case Element:
                generateElementQuestion();
                break;
            case Range: //Hard mode, which can have either an element question or a range question
                Random r = new Random();
                int nextQ = r.nextInt(100);

                if(nextQ > 50)
                generateRangeQuestion();
                else
                    generateElementQuestion();
                break;
        }
    }

    private void generateElementQuestion()
    {
        Random rand = new Random();
        int rows = rand.nextInt(5) + 3;
        int cols = rand.nextInt(5) + 3;

        int elementRow = rand.nextInt(rows);
        int elementCol = rand.nextInt(cols);

        this.rows = rows;
        this.cols = cols;

        TwoDimIndex element = new TwoDimIndex(elementRow, elementCol);

        this.question = "a" + element.toString();

        this.correctIndices.add(element);
    }

    private void generateRangeQuestion()
    {
        Random rand = new Random();
        int rows = rand.nextInt(5) + 3;
        int cols = rand.nextInt(5) + 3;

        int startRow = rand.nextInt(rows);
        int startCol = rand.nextInt(cols);
        int endRow = rand.nextInt(rows);
        int endCol = rand.nextInt(cols);

        TwoDimIndex start = new TwoDimIndex(startRow, startCol);
        TwoDimIndex end = new TwoDimIndex(endRow, endCol);

        /* Prevent duplicates. */
        while (start.equals(end)) {
            endCol = rand.nextInt(cols);
            end = new TwoDimIndex(endRow, endCol);
        }

        if (startRow > endRow) {
            int temp = startRow;
            startRow = endRow;
            endRow = temp;
        }

        if (startRow == endRow && startCol > endCol) {
            int temp = startCol;
            startCol = endCol;
            endCol = temp;
        }

        this.rows = rows;
        this.cols = cols;

        /*this.question = "A for loop starts with the rows with variable i on the range of " + startRow + " to " + endRow
                        + ", and nests into the columns with a for loop with variable j on the range of " + startCol
                        + " to " + endCol + ". What elements will be covered in the loop?"; */

        this.question = "Fill in the box of the coordinates a[" + start.toString() + ", " + end.toString() + "]";


        for (int i = startRow; i < endRow; ++i) {
            for (int j = startCol; j < endCol; ++j) {
                this.correctIndices.add(new TwoDimIndex(i, j));
            }
        }

    }

    @Override
    boolean checkAnswer(ArrayList<Index> selectedIndices) {
        return false;

    }


    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}
