import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class OneDimQuestion extends Question {

    private int arrayLength;
    private final int timeForElementQuestion = 10;
    private final int timeForRangeQuestion = 20;
    private int timeForQuestion;
    private int forEachWritten = 0;
    private int score;

    public OneDimQuestion(QuestionType difficulty,int score)
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
                if(score <= 10)
                    generateElementQuestion();
                if(score > 10) {
                    Random r = new Random();
                    int nextQ = r.nextInt(100);

                    if (nextQ > 60-score || score == 60)
                        generateRangeQuestion();
                    else
                        generateElementQuestion();
                    break;

                }
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

    private void generateElementQuestion() //Creates a question for a specific cell
    {
        this.timeForQuestion = timeForElementQuestion;
        Random rand = new Random();
        int arrayLength = rand.nextInt(8) + 3;
        int correctIndex = rand.nextInt(arrayLength);
        this.arrayLength = arrayLength;
        OneDimIndex element = new OneDimIndex(correctIndex);
        this.question = element.toString();
        this.correctIndices.add(new OneDimIndex(correctIndex));
    }

    private void generateRangeQuestion() //Creates a Range question where user selects multiple
    {
       // Random r = new Random();
        //boolean isForEach = r.nextBoolean();

        this.timeForQuestion = timeForRangeQuestion;

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

        this.arrayLength = arrayLength;

        /*if(!isForEach) {
            this.question = "for(int i = " + lowerBound + "; i <= " + upperBound + "; ++i) \n a[i]";
        }
        else{
            this.question = " int [] a; \nfor(int i : a)";

        }*/

        int factor = 1;
        Random r = new Random();
        if((upperBound-lowerBound) > 6){
           factor = (r.nextInt(2) + 2);
        }else{
            if((upperBound-lowerBound) > 3){
                factor = (r.nextInt(1) + 1);
            }
        }

        this.question = "for(int i = " + lowerBound + "; i <= " + upperBound + "; " + "i+=" + factor + ") \n a[i]";


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
}