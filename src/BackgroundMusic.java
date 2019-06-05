/*import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import javax.sound.sampled.AudioSystem;*/
import javax.sound.sampled.Clip;
import java.io.*;

public class BackgroundMusic {
    private static BackgroundMusic INSTANCE = new BackgroundMusic();
    private static boolean isRunning = false;
    private static boolean mute = true;
    private static Clip clip;
    private static long clipTime = 0;
    private final String nameOfMusic = "src/resources/sounds/Wii Remix.wav"; //Must add src to the beginning to specify path
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


    public void mute() {
        if(!mute){
            isRunning = false;
            mute = true;
            System.out.println("yo");
            clipTime = clip.getMicrosecondPosition();
            clip.stop();
            //player.close();
        }
        else{
            mute = false;
            play();
        }
    }

    public void play(){
        if(!isRunning) {
            System.out.println("wassup");
            try {
                new Thread() {
                    public void run() {
                        try {
                           // player.play();
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                }.start();
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
