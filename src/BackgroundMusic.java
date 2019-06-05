import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

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

    private final String nameOfMusic = "Wii Remix.wav"; //Must add src to the beginning to specify path\
    // private final String nameOfMemeMusic = "src/resources/sounds/Wii Remix BOOSTED.wav";
    // private Player player;
    // private FileInputStream fis;

    //private static long location = 0;

    //private boolean alternateSounds = false;

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
                URL musicURL = getClass().getResource("resources/sounds/Wii Remix.wav");
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
            clipTime = clip.getMicrosecondPosition();
            clip.stop();
            //player.close();
        }
        else{
            mute = false;
            play();
        }
    }

    /*

    public void useAlternate()
    {
        this.alternateSounds = true;
    }

    public void useRegular() { this.alternateSounds = false;}
*/
    private BackgroundMusic() {
    }
}
