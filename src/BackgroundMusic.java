import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class BackgroundMusic {
    private static BackgroundMusic INSTANCE = new BackgroundMusic();
    private static boolean isRunning = false;
    private static boolean mute = true;
    private static Clip clip;
    private static long clipTime = 0;
    private final String nameOfMusic = "src/resources/sounds/Wii Remix.wav"; //Must add src to the beginning to specify path
    private final String nameOfMemeMusic = "src/resources/sounds/Wii Remix BOOSTED.wav";

    private boolean alternateSounds = false;

    public static BackgroundMusic getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new BackgroundMusic();
        }

        return INSTANCE;
    }

    public boolean isRunning(){
        return isRunning;
    }

    public void play() {
        if(!isRunning) {
            try {
                File file = new File(this.alternateSounds ? nameOfMemeMusic : nameOfMusic);
                this.clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(file));
                clip.setMicrosecondPosition(clipTime);
                clip.start();
                isRunning = true;
                clip.loop(1000);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        else{
            System.out.println("Hello");
        }
    }//hi

    public void mute(){
        if(!mute){
            isRunning = false;
            mute = true;
            clipTime = clip.getMicrosecondPosition();
            clip.stop();
        }
        else{
            mute = false;
            play();
        }
    }

    public void useAlternate()
    {
        this.alternateSounds = true;
    }

    public void useRegular() { this.alternateSounds = false;}

    private BackgroundMusic() {
    }
}
