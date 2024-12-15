import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class SoundModel {

    private Clip[] clips = new Clip[10];
    private URL[] soundURLs = new URL[10];

    public SoundModel() {
        soundURLs[5] = getClass().getResource("res/sounds/backgroundMusic.wav");
        soundURLs[1] = getClass().getResource("res/sounds/newstep.wav");
        soundURLs[2] = getClass().getResource("res/sounds/box.wav");
        soundURLs[3] = getClass().getResource("res/sounds/boxOnTarget.wav");
        loadSounds();
    }

    private void loadSounds() {
        try {
            for (int i = 0; i < soundURLs.length; i++) {
                if (soundURLs[i] != null) {
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURLs[i]);
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioStream);
                    clips[i] = clip;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play(int i) {
        if (clips[i] != null) {
            if (clips[i].isRunning()) {
                clips[i].stop();
            }
            clips[i].setFramePosition(0);
            clips[i].start();
        }
    }

    public void loop(int i) {
        if (clips[i] != null) {
            clips[i].loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stop(int i) {
        if (clips[i] != null && clips[i].isRunning()) {
            clips[i].stop();
        }
    }
}
