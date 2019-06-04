import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class BackgroundMusic {
    private static BackgroundMusic INSTANCE = new BackgroundMusic();
    private static boolean isRunning = false;


    public static BackgroundMusic getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new BackgroundMusic();
        }

        return INSTANCE;
    }

    public void play() {
        if(!isRunning) {
            try {
                File file = new File("src/resources/sounds/correct.wav");
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(file));
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
    }

    private BackgroundMusic() {
    }
}
