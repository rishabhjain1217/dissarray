
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class BackgroundMusic implements Constants {
    private static BackgroundMusic INSTANCE = new BackgroundMusic();
    private static boolean isRunning = false;
    private static boolean mute = true;
    private static Clip clip;
    private static AudioInputStream audioInputStream;
    private static long clipTime = 0;

    public boolean alternateSounds = false;


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
                URL musicURL = getClass().getResource(this.alternateSounds ? NAME_OF_MEME_MUSIC : NAME_OF_MUSIC);
                //File file = new File(musicURL);//this.alternateSounds ? NAME_OF_MEME_MUSIC : NAME_OF_MUSIC);
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
            System.out.println("Something went wrong with the background music");
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
