/**
 * Created by Rishabh Jain AKA CodeBot on 06 06, 2019 at 08:06am
 */
public interface Constants {

    //Backgroundmusic
    String NAME_OF_MUSIC = "resources/sounds/Wii Remix.wav"; //Regular music
    String NAME_OF_MEME_MUSIC = "resources/sounds/Wii Remix BOOSTED.wav";//Must add src to the beginning to specify path

    String TITLE_OF_GAME = "Diss-Array v1.1";//Title Change

    final int timeForElementQuestion = 10;
    final int timeForRangeQuestion = 20;
    final int MAXELEMENTDETRACTION = 05;
    final int MAXRANGEDDETRACTION = 12;









    final int PROBABILITY_BOUNDS = 100;
    final int MAX_SCORE_FOR_EASY = 25;
    final int SCORE_DIFFICULTY_MULTIPLIER = 4;
    final int ONEDIM_ELEMENT_TIME_INCREMENTS = 4;
    final int ONEDIM_RANGE_TIME_INCREMENTS = 5;

































    /**SoundLoader*/
    String CORRECT_ONE = "resources/sounds/correct.wav";
    String CORRECT_TWO = "resources/sounds/correct2.wav";
    String WRONG_ONE = "resources/sounds/wrong.wav";
    String WRONG_TWO = "resources/sounds/wrong2.wav";




    /**TwoDimQuestion*/
    int TIME_FOR_ELEMENT_QUESTION_TWODIM = 15; //Element Question Time TwoDim
    int TIME_FOR_RANGE_QUESTION_TWODIM = 25; //Range Question Time TwoDim
    int MAX_RANGED_DETRACTION = 13; //Max amount of time taken away from questions
    int MAX_ROWS_TWODIM = 3;
    int MAX_COLS_TWODIM = 6;
    int MIN_ROWS_TWODIM = 3;
    int MIN_COLS_TWODIM = 3;
    int SWITCH_CASE_SCORE_TWODIM = 10;

}
