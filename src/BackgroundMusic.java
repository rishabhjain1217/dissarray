import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class BackgroundMusic {
    private static BackgroundMusic INSTANCE = new BackgroundMusic();
    private static boolean isRunning = false;
    private static boolean mute = true;
    private static Clip clip;
    private static long clipTime = 0;


    public static BackgroundMusic getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new BackgroundMusic();
        }

        return INSTANCE;
    }

    public void play() {
        if(!isRunning) {
            try {
                File file = new File("src/resources/sounds/OldTownRoad.wav");
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
    }

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
