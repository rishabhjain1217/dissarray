import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TwoDimQuestion extends Question implements Constants {

    private int rows, cols;
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

    @Override
    void generateQuestion(QuestionType difficulty,int score) {

        switch (difficulty) {
            case Element:
                if (score <= SWITCH_CASE_SCORE_TWODIM) {
                    generateElementQuestion();
                    break;
                }
                if(score > SWITCH_CASE_SCORE_TWODIM){
                    generateQuestionBasedOnScore();
                }
                break;
            case Range: /**Hard mode, which can have either an element question or a range question*/
                generateQuestionBasedOnScore();
                break;
        }
    }

    private void generateQuestionBasedOnScore() {
        Random r = new Random();
        int nextQ = r.nextInt(PROBABILITY_BOUNDS);

        if(nextQ > difficultyProbability(score) || score == MAX_SCORE_FOR_EASY)
            generateRangeQuestion();
        else
            generateElementQuestion();
    }

    private void generateElementQuestion()
    {
        difficulty = QuestionType.Element;
        int scoreInfluence = score/2;

        if(scoreInfluence <=6)
        this.timeForQuestion = this.TIME_FOR_ELEMENT_QUESTION_TWODIM-(scoreInfluence);//Changes time for question
        else{
            this.timeForQuestion = this.TIME_FOR_ELEMENT_QUESTION_TWODIM - MAX_RANGED_DETRACTION_TWODIM;
        }
        Random rand = new Random();
        int rows = rand.nextInt(MAX_ROWS_TWODIM) + MIN_ROWS_TWODIM;
        int cols = rand.nextInt(MAX_COLS_TWODIM) + MIN_COLS_TWODIM;

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
        int scoreInfluence = score/5;

        if(scoreInfluence <= ONEDIM_ELEMENT_TIME_INCREMENTS)
            this.timeForQuestion = this.TIME_FOR_RANGE_QUESTION_TWODIM-(scoreInfluence*2);
        else
            this.timeForQuestion = this .TIME_FOR_RANGE_QUESTION_TWODIM-TIME_FOR_RANGE_QUESTION_TWODIM;

        Random rand = new Random();
        int rows = rand.nextInt(MAX_ROWS_TWODIM) + MIN_ROWS_TWODIM;
        int cols = rand.nextInt(MAX_COLS_TWODIM) + MIN_COLS_TWODIM;

        int startRow = rand.nextInt(rows);
        int startCol = rand.nextInt(cols);
        int endRow = rand.nextInt(rows);
        int endCol = rand.nextInt(cols);

        TwoDimIndex start = new TwoDimIndex(startRow, startCol);
        TwoDimIndex end = new TwoDimIndex(endRow, endCol);

        /** Prevent duplicates. */
        while (start.equals(end)) {
            endCol = rand.nextInt(cols);
            endRow = rand.nextInt(rows);
            end = new TwoDimIndex(endRow, endCol);
        }

        /**Prevents the lower bound from being greater than the upper bound*/
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

        /** Set Random increments for the first and second loops*/

        int iIncrement  = 1;
        int jIncrement = 1;

        Random r = new Random();
        int f = r.nextInt(PROBABILITY_BOUNDS);
        if((f > (difficultyProbability(score))) || (score == MAX_SCORE_FOR_EASY)){
            iIncrement = (r.nextInt(2) + 2);
            jIncrement = (r.nextInt(2) + 2);
        }
        else {
            iIncrement = (r.nextInt(1) + 1);
            jIncrement = (r.nextInt(1) + 1);
        }

        /**this.question = "A for loop starts with the rows with variable i on the range of " + startRow + " to " + endRow
                        + ", and nests into the columns with a for loop with variable j on the range of " + startCol
                        + " to " + endCol + ". What elements will be covered in the loop?"; */

        this.question = "for(int i = " + start.getRowIndex() + "; i < " + end.getRowIndex() + "; i+= " + iIncrement + ")\n" +
        "           for(int j = " + start.getColIndex() + "; j < " + end.getColIndex() + "; j+= " + jIncrement + ")\n" +
                "       a[i][j];";


        setCorrectedIndex(startRow,endRow,startCol,endCol,iIncrement,jIncrement);

    }

    /**Set teh array correctIndices with the correct indices*/
    public void setCorrectedIndex(int startRow, int endRow, int startCol, int endCol, int factor1, int factor2){
        for (int i = startRow; i < endRow; i+=factor1) {
            for (int j = startCol; j < endCol; j+=factor2) {
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

    public int difficultyProbability(int score){
        return PROBABILITY_BOUNDS-score * SCORE_DIFFICULTY_MULTIPLIER;
    }
}
