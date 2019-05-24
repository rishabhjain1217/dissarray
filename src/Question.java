import java.util.ArrayList;

public abstract class Question {

    protected String question;
    protected ArrayList<Index> correctIndices;

    public Question()
    {
        this.question = "";
        this.correctIndices = new ArrayList<>();
    }

    abstract void generateQuestion();

    abstract boolean checkAnswer(ArrayList<Index> selectedIndices);

}
