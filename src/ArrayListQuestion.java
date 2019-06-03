import java.util.ArrayList;

public class ArrayListQuestion extends  Question {

    private int listLength;
    private final int timeForElementQuestion = 10;
    private final int timeForRangeQuestion = 20;
    private int timeForQuestion;
    private int forEachWritten = 0;
    private int score;


    public ArrayListQuestion(QuestionType difficulty, int score){
        super();
        this.score = score;
        this.generateQuestion(difficulty,score);
    }

    @Override
    void generateQuestion(QuestionType difficulty, int score) {

    }

    @Override
    boolean checkAnswer(ArrayList<Index> index){

        return false;
    }


}
