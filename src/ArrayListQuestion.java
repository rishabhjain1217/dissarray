import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class ArrayListQuestion extends Question implements Constants {

    private int arrayLength;

    private int timeForQuestion;
    private int score;

    public ArrayListQuestion(QuestionType difficulty,int score)
    {
        super();
        this.score = score;
        this.generateQuestion(difficulty,score);
    }

    public int getTimeForQuestion() {
        return timeForQuestion;
    }

    @Override
    void generateQuestion(QuestionType difficulty,int score) //Selects what type of question is given
    {
        switch (difficulty) {
            case Element:
                if(score <= SWITCH_CASE_SCORE_LIST)
                    generateElementQuestion();
                if(score > SWITCH_CASE_SCORE_LIST) {
                    Random r = new Random();
                    int nextQ = r.nextInt(PROBABILITY_BOUNDS); //push

                    if (nextQ > difficultyProbability(score) || score == MAX_SCORE_FOR_EASY)
                        generateRangeQuestion();
                    else
                        generateElementQuestion();
                    break;
                }
                break;
            case Range: //Hard mode, which can have either an element question or a range question
                Random r = new Random();
                int nextQ = r.nextInt(PROBABILITY_BOUNDS);

                if(nextQ > difficultyProbability(score) || score == MAX_SCORE_FOR_EASY)
                    generateRangeQuestion();
                else
                    generateElementQuestion();
                break;
        }
    }

    private void generateElementQuestion() //Creates a question for a specific cell
    {
        int scoreInfluence = score/2;
        if(scoreInfluence <= ONEDIM_ELEMENT_TIME_INCREMENTS)
            this.timeForQuestion = TIME_FOR_ELEMENT_QUESTION_LIST-(scoreInfluence);
        else
            this.timeForQuestion = TIME_FOR_ELEMENT_QUESTION_LIST - MAX_ELEMENT_DETRACTION_LIST;

        Random rand = new Random();
        int arrayLength = rand.nextInt(MAX_SIZE_ONELIST) + MIN_SIZE_ONELIST;
        int correctIndex = rand.nextInt(arrayLength);
        this.arrayLength = arrayLength;
        ArrayListIndex element = new ArrayListIndex(correctIndex);
        this.question = element.toString();
        this.correctIndices.add(new ArrayListIndex(correctIndex));
    }

    private void generateRangeQuestion() //Creates a Range question where user selects multiple
    {
        // Random r = new Random();
        //boolean isForEach = r.nextBoolean();
        int scoreInfluence = score/5;
        if(scoreInfluence <= ONEDIM_RANGE_TIME_INCREMENTS)
            this.timeForQuestion = TIME_FOR_RANGE_QUESTION_LIST - (scoreInfluence*2);
        else
            this.timeForQuestion = TIME_FOR_RANGE_QUESTION_LIST - MAX_RANGED_DETRACTION_LIST;

        Random rand = new Random();
        int arrayLength = rand.nextInt(MAX_SIZE_ONELIST) + MIN_SIZE_ONELIST;
        int bound1 = rand.nextInt(arrayLength);
        int bound2 = rand.nextInt(arrayLength);
        /* Prevent having the same bounds. */
        while (bound1 == bound2) {
            bound2 = rand.nextInt(arrayLength);
        }

        int lowerBound = Math.min(bound1, bound2);
        int upperBound = Math.max(bound1, bound2);

        this.arrayLength = arrayLength;


        int factor = 1;
        Random r = new Random();
        int f = r.nextInt(100);
        if (f > difficultyProbability(score) || score == MAX_SCORE_FOR_EASY)
            factor = (r.nextInt(1) + 2);
        else
            factor = (r.nextInt(1) + 1);

        this.question = "for(int i = " + lowerBound + "; i <= " + upperBound + "; " + "i+=" + factor + ") \n a.get(i)";


        setCorrectedIndex(lowerBound,upperBound,factor);
    }

    public void setCorrectedIndex(int lower, int upper, int factor){
        for (int i = lower; i <= upper; i+=factor) {
            this.correctIndices.add(new ArrayListIndex(i));
        }
    }

    public int getArrayLength() {
        return arrayLength;
    }

    @Override
    boolean checkAnswer(ArrayList<Index> selectedIndices)
    {
        if (this.correctIndices.size() != selectedIndices.size()) {
            return false;
        }

        Collections.sort(selectedIndices);

        for (int i = 0; i < this.correctIndices.size(); ++i) {
            if (!correctIndices.get(i).equals(selectedIndices.get(i))) return false;
        }

        return true;
    }

    public int difficultyProbability(int score){
        return PROBABILITY_BOUNDS-score * SCORE_DIFFICULTY_MULTIPLIER;
    }
}