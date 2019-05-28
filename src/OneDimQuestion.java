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

    public OneDimQuestion(QuestionType difficulty)
    {
        super();
        this.generateQuestion(difficulty);
    }

    public int getTimeForQuestion() {
        return timeForQuestion;
    }

    @Override
    void generateQuestion(QuestionType difficulty) //Selects what type of question is given
    {
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

    private void generateElementQuestion() //Creates a question for a specific cell
    {
        this.timeForQuestion = timeForElementQuestion;
        Random rand = new Random();
        int arrayLength = rand.nextInt(10) + 3;
        int correctIndex = rand.nextInt(arrayLength);
        this.arrayLength = arrayLength;
        OneDimIndex element = new OneDimIndex(correctIndex);
        this.question = element.toString();
        this.correctIndices.add(new OneDimIndex(correctIndex));
    }

    private void generateRangeQuestion() //Creates a Range question where user selects multiple
    {
        this.timeForQuestion = timeForRangeQuestion;
        Random rand = new Random();
        int arrayLength = rand.nextInt(10) + 3;
        int bound1 = rand.nextInt(arrayLength);
        int bound2 = rand.nextInt(arrayLength);

        /* Prevent having the same bounds. */
        while (bound1 == bound2) {
            bound2 = rand.nextInt(arrayLength);
        }

        int lowerBound = Math.min(bound1, bound2);
        int upperBound = Math.max(bound1, bound2);

        this.arrayLength = arrayLength;

        this.question = "a["+ lowerBound+ "] ... a[" + upperBound+"]";

        for (int i = lowerBound; i <= upperBound; ++i) {
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