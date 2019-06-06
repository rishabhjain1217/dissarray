import java.util.ArrayList;

public abstract class Question {

    /** Abstract Question class that is implemented by various classes like OneDimQuestion, TwoDimQuestion, ArrayListQuestion,
     * Holds String Question and an Arraylist of correct Indices
     */

    protected String question;
    protected ArrayList<Index> correctIndices;

    public Question()
    {
        this.question = "";
        this.correctIndices = new ArrayList<>();
    }

    abstract void generateQuestion(QuestionType difficulty,int score);

    abstract boolean checkAnswer(ArrayList<Index> selectedIndices);

}
