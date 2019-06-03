import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TwoDimQuestion extends Question {

    private int rows, cols;

    private final int TIMEFORELEMENTQUESTION = 15;
    private int TIMEFORRANGEDQUESTION = 25;
    private int timeForQuestion;
    private QuestionType difficulty;
    private int score;

    public TwoDimQuestion(QuestionType difficulty, int score)
    {
        super();
        this.score = score;
        this.generateQuestion(difficulty, score);
    }

    public int getTimeForQuestion(){
        return timeForQuestion;
    }

    public QuestionType getDifficulty(){
        return difficulty;
    }

    //@Override
    void generateQuestion(QuestionType difficulty,int score) {

        switch (difficulty) {
            case Element:
                if (score <= 10)
                    generateElementQuestion();
                if(score > 10){
                    Random r = new Random();
                    int nextQ = r.nextInt(100);

                    if(nextQ > 60-(score*2) || score == 30)
                        generateRangeQuestion();
                    else
                        generateElementQuestion();
                    break;

                }

                break;
            case Range: //Hard mode, which can have either an element question or a range question
                Random r = new Random();
                int nextQ = r.nextInt(100);

                if(nextQ > 60-(score*2) || score == 30)
                generateRangeQuestion();
                else
                    generateElementQuestion();
                break;
        }
    }

    private void generateElementQuestion()
    {
        difficulty = QuestionType.Element;
        this.timeForQuestion = this.TIMEFORELEMENTQUESTION; //Changes time for question
        Random rand = new Random();
        int rows = rand.nextInt(3) + 3;
        int cols = rand.nextInt(6) + 3;

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
        difficulty = QuestionType.Range;
        this.timeForQuestion = this.TIMEFORRANGEDQUESTION;
        Random rand = new Random();
        int rows = rand.nextInt(3) + 3;
        int cols = rand.nextInt(6) + 3;

        int startRow = rand.nextInt(rows);
        int startCol = rand.nextInt(cols);
        int endRow = rand.nextInt(rows);
        int endCol = rand.nextInt(cols);

        TwoDimIndex start = new TwoDimIndex(startRow, startCol);
        TwoDimIndex end = new TwoDimIndex(endRow, endCol);

        /* Prevent duplicates. */
        while (start.equals(end)) {
            endCol = rand.nextInt(cols);
            endRow = rand.nextInt(rows);
            end = new TwoDimIndex(endRow, endCol);
        }

        while(startRow >= endRow) {
            if (startRow > endRow) {
                int temp = startRow;
                startRow = endRow;
                endRow = temp;
            }
            if (startRow == endRow && startRow != 0) {
                startRow--;
            }
            if (startRow == endRow && startRow == 0) {
                endRow++;
            }
        }

        while(startCol >= endCol) {
            if (startCol > endCol) {
                int temp = startCol;
                startCol = endCol;
                endCol = temp;
            }
            if (startCol == endCol && startCol != 0) {
                --startCol;
            }
            if (startCol == endCol && startCol == 0) {
                ++endCol;
            }
        }
        start = new TwoDimIndex(startRow, startCol);
        end = new TwoDimIndex(endRow, endCol);

        this.rows = rows;
        this.cols = cols;

        int iIncrement  = 1;
        int jIncrement = 1;

        Random r = new Random();
        if((endRow-startRow) > 3){
            iIncrement = (r.nextInt(2) + 2);
        }else{
            if((endRow-startRow) > 0){
                iIncrement = (r.nextInt(1) + 1);
            }
        }

        if((endCol-startCol) > 3){
            jIncrement = (r.nextInt(2) + 2);
        }else{
            if((endCol-startCol) > 0) {
                jIncrement = (r.nextInt(1) + 1);
            }
        }

        /*this.question = "A for loop starts with the rows with variable i on the range of " + startRow + " to " + endRow
                        + ", and nests into the columns with a for loop with variable j on the range of " + startCol
                        + " to " + endCol + ". What elements will be covered in the loop?"; */

        this.question = "for(int i = " + start.getRowIndex() + "; i < " + end.getRowIndex() + "; i+= " + iIncrement + ")\n" +
        "           for(int j = " + start.getColIndex() + "; j < " + end.getColIndex() + "; j+= " + jIncrement + ")\n" +
                "       a[i][j];";


        setCorrectedIndex(startRow,endRow,startCol,endCol,iIncrement,jIncrement);

    }

    public void setCorrectedIndex(int startRow, int endRow, int startCol, int endCol, int factor1, int factor2){
        for (int i = startRow; i < endRow; i+=factor1) {
            for (int j = startCol; j < endCol; j+=factor1) {
                this.correctIndices.add(new TwoDimIndex(i, j));
            }
        }
    }

    @Override
    boolean checkAnswer(ArrayList<Index> selectedIndices) {
        if (this.correctIndices.size() != selectedIndices.size()) return false;

        Collections.sort(selectedIndices);

        for (int i = 0; i < this.correctIndices.size(); ++i) {
            if (!correctIndices.get(i).equals(selectedIndices.get(i))) return false;
        }

        return true;
    }


    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}
