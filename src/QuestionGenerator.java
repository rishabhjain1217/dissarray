import java.util.Random;

public class  QuestionGenerator {

    public QuestionGenerator()
    {

    }

    /**
     * Generates a one-dimensional array question.
     * @return a OneDimQuestion
     */
    public Question generateOneDim(QuestionType difficulty)
    {
        return new OneDimQuestion(difficulty);
    }

    /**
     * Generates a two-dimensional array question.
     * @return a TwoDimQuestion
     */
    public Question generateTwoDim(QuestionType difficulty)
    {
        return new TwoDimQuestion(difficulty);
    }

    /**
     * Generates either a one dimension or two dimension question.
     * @return either a OneDimQuestion or a TwoDimQuestion
     */
    public Question generateRandom(QuestionType difficulty)
    {

        Random rand =  new Random();
        int n = rand.nextInt(2);

        return n == 0 ? generateOneDim(difficulty) : generateTwoDim(difficulty);
    }



}
