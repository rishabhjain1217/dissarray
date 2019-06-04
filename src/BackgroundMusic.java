import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class BackgroundMusic {
    private static BackgroundMusic INSTANCE = new BackgroundMusic();
    private static boolean isRunning = false;
    private static boolean mute = true;
    private static Clip clip;
    private static long clipTime = 0;
    private final String nameOfMusic = "src/resources/sounds/Wii Remix.wav"; //Must add src to the beginning


    public static BackgroundMusic getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new BackgroundMusic();
        }

        return INSTANCE;
    }

    public void play() {
        if(!isRunning) {
            try {
                File file = new File(nameOfMusic);
                this.clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(file));
                clip.setMicrosecondPosition(clipTime);
                clip.start();
                isRunning = true;
               // clip.loop(1000);
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

    private BackgroundMusic() {
    }
}
