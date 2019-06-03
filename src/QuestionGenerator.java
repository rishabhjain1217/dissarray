import java.util.Random;

public class QuestionGenerator {

    public QuestionGenerator()
    {

    }

    /**
     * Generates a one-dimensional array question.
     * @return a OneDimQuestion
     */
    public Question generateOneDim(QuestionType difficulty, int score)
    {
        return new OneDimQuestion(difficulty,score);
    }

    /**
     * Generates a two-dimensional array question.
     * @return a TwoDimQuestion
     */
    public Question generateTwoDim(QuestionType difficulty,int score)
    {
        return new TwoDimQuestion(difficulty,score);
    }

    /**
     * Generates either a one dimension or two dimension question.
     * @return either a OneDimQuestion or a TwoDimQuestion
     */
    public Question generateRandom(QuestionType difficulty,int score)
    {

        Random rand =  new Random();
        int n = rand.nextInt(2);

        return n == 0 ? generateOneDim(difficulty,score) : generateTwoDim(difficulty,score);
    }
/*
    public Question getDifficulty(int score, QuestionType qt){
        switch(qt){
            case Element:
                if(score <= 10)
                    //generateOneDim(qt);

            case Range:

            default:
        }

    }
*/
}
