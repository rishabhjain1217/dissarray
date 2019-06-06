
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.*;
import java.net.URL;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class BackgroundMusic {
    private static BackgroundMusic INSTANCE = new BackgroundMusic();
    private static boolean isRunning = false;
    private static boolean mute = true;
    private static Clip clip;
    private static AudioInputStream audioInputStream;
    private static long clipTime = 0;

    public boolean alternateSounds = false;

    private final String nameOfMusic = "resources/sounds/Wii Remix.wav";
    private final String nameOfMemeMusic = "resources/sounds/Wii Remix BOOSTED.wav";//Must add src to the beginning to specify path

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
                URL musicURL = getClass().getResource(this.alternateSounds ? nameOfMemeMusic : nameOfMusic);
                //File file = new File(musicURL);//this.alternateSounds ? nameOfMemeMusic : nameOfMusic);
                this.clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(musicURL));
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
    }


    public void mute() {
        if(!mute){
            isRunning = false;
            mute = true;
            System.out.println("yo");
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
