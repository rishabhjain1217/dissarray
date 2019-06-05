import java.util.ArrayList;
import java.util.Random;

public class ArrayListQuestion extends  Question {

    private int listLength;
    private final int timeForElementQuestion = 10;
    private final int timeForRangeQuestion = 20;
    private final int MAXELEMENTDETRACTION = 05;
    private final int MAXRANGEDDETRACTION =10;


    private int timeForQuestion;

    private int score;

    public int getTimeForQuestion() {
        return timeForQuestion;
    }


    public ArrayListQuestion(QuestionType difficulty, int score){
        super();
        this.score = score;
        this.generateQuestion(difficulty,score);
    }

    @Override
    void generateQuestion(QuestionType difficulty,int score) //Selects what type of question is given
    {
        switch (difficulty) {
            case Element:
                if(score <= 10)
                    generateElementQuestion();
                if(score > 10) {
                    Random r = new Random();
                    int nextQ = r.nextInt(100);

                    if (nextQ > 100-score*4 || score == 25)
                        generateRangeQuestion();
                    else
                        generateElementQuestion();
                    break;

                }
                break;
            case Range: //Hard mode, which can have either an element question or a range question
                Random r = new Random();
                int nextQ = r.nextInt(100);

                if(nextQ > 100-score*4 || score == 25)
                    generateRangeQuestion();
                else
                    generateElementQuestion();
                break;
        }
    }
    private void generateElementQuestion() //Creates a question for a specific cell
    {
        int scoreInfluence = score/2;
        if(scoreInfluence <=4)
            this.timeForQuestion = timeForElementQuestion-(scoreInfluence);
        else
            this.timeForQuestion = timeForElementQuestion -MAXELEMENTDETRACTION;

        Random rand = new Random();
        int listLength = rand.nextInt(8) + 3;
        int correctIndex = rand.nextInt(listLength);
        this.listLength = listLength;
        OneDimIndex element = new OneDimIndex(correctIndex);
        this.question = element.toString();
        this.correctIndices.add(new OneDimIndex(correctIndex));
    }

    private void generateRangeQuestion() //Creates a Range question where user selects multiple
    {
        // Random r = new Random();
        //boolean isForEach = r.nextBoolean();
        int scoreInfluence = score/5;
        if(scoreInfluence <= 5)
            this.timeForQuestion = timeForRangeQuestion - (scoreInfluence*2);
        else
            this.timeForQuestion = timeForRangeQuestion - MAXRANGEDDETRACTION;

        Random rand = new Random();
        int arrayLength = rand.nextInt(9) + 3;
        int bound1 = rand.nextInt(arrayLength);
        int bound2 = rand.nextInt(arrayLength);
        /* Prevent having the same bounds. */
        while (bound1 == bound2) {
            bound2 = rand.nextInt(arrayLength);
        }

        int lowerBound = Math.min(bound1, bound2);
        int upperBound = Math.max(bound1, bound2);

        this.listLength = arrayLength;

        /*if(!isForEach) {
            this.question = "for(int i = " + lowerBound + "; i <= " + upperBound + "; ++i) \n a[i]";
        }
        else{
            this.question = " int [] a; \nfor(int i : a)";

        }*/
        int factor = 1;
        Random r = new Random();
        int f = r.nextInt(100);
        if (f > 100-score*4 || score == 25)
            factor = (r.nextInt(1) + 2);
        else
            factor = (r.nextInt(1) + 1);

        this.question = "for(int i = " + lowerBound + "; i <= " + upperBound + "; " + "i+=" + factor + ") \n a.get(i)";


        setCorrectedIndex(lowerBound,upperBound,factor);
        /*
        if(isForEach){
            this.question = " int [] a; \nfor(int i : a)";
            for(int i = 0; i <= arrayLength; ++i)
                this.correctIndices.add(new OneDimIndex(i));

        }*/
    }

    public void setCorrectedIndex(int lower, int upper, int factor){
        for (int i = lower; i <= upper; i+=factor) {
            this.correctIndices.add(new OneDimIndex(i));
        }
    }


    @Override
    boolean checkAnswer(ArrayList<Index> index){

        return false;
    }

    public int getArrayLength() {
        return listLength;
    }


}
