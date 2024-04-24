import java.io.*;
import java.util.*;
import javax.sound.sampled.*;
public class Spotify {
    private AudioInputStream music;
    private Clip song;
    private String filePath = "";
    //AIFF,AU,WAV are all suported audio files
    public Spotify()throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        music = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
        song = AudioSystem.getClip();
        song.open(music);
        song.loop(Clip.LOOP_CONTINUOUSLY);
    } 
    public void play(){
        song.start();
    }
    public void stop(){
        song.stop();
    }
}
