import java.io.*;
import java.util.*;
import javax.sound.sampled.*;
public class Spotify {
    private AudioInputStream music;
    private Clip song;
    private String filePath = "C:/Cascadia/src/sherry.wav";
    //AIFF,AU,WAV are all suported audio files
    public Spotify()throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        music = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
        song = AudioSystem.getClip();
        song.open(music);
        //song.loop();
    } 
    public void play(){
        song.start();
    }
    public void stop(){
        song.stop();
    }
}
