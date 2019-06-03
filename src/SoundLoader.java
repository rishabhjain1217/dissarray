public class SoundLoader {

    private static String correct1 = "resources/sounds/correct.wav";
    private static String correct2 = "resources/sounds/correct2.wav";
    private static String wrong1 = "resources/sounds/wrong.wav";
    private static String wrong2 = "resources/sounds/wrong2.wav";

    private boolean alternateSounds = false;

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

    public String getCorrect()
    {
        return this.alternateSounds ? SoundLoader.correct2 : SoundLoader.correct1;
    }

    public String getWrong()
    {
        return this.alternateSounds ? SoundLoader.wrong2 : SoundLoader.wrong1;
    }

}
