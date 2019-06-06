public class SoundLoader implements Constants {

    public boolean alternateSounds = false;

    /** Singleton for sounds, Correct and incorrect. Alternative option is used when you find Easter Egg!*/

    private static SoundLoader ourInstance = new SoundLoader();

    public static SoundLoader getInstance() {
        return ourInstance;
    }

    private SoundLoader() {
    }

    public void useAlternate()
    {
        this.alternateSounds = true;
    }

    public void useRegular() { this.alternateSounds = false;}

    public String getCorrect()
    {
        return this.alternateSounds ? SoundLoader.CORRECT_TWO : SoundLoader.CORRECT_ONE;
    }

    public String getWrong()
    {
        return this.alternateSounds ? SoundLoader.WRONG_TWO : SoundLoader.WRONG_ONE;
    }

}
