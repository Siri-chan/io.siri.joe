package io.siri.joe.audio;

import io.siri.joe.Maths;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class MusicClip {
    //this uses javax.sound.sampled.Clip
    long now = 0L; //Microseconds
    Clip clip;
    MusicStatus status;
    AudioInputStream audioStream;

    public MusicClip(File audioFile) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        audioStream = AudioSystem.getAudioInputStream(audioFile);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        status = MusicStatus.Paused;

    }
    public MusicClip(URL audioLink) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        audioStream = AudioSystem.getAudioInputStream(audioLink);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        status = MusicStatus.Paused;
    }

    public void play(){
        //this should play from start
        if(status == MusicStatus.Playing)
            return;
        clip.start();
        status = MusicStatus.Playing;
    }
    public void pause(){
        if(status == MusicStatus.Paused)
            return;
        now = clip.getMicrosecondPosition();
        clip.stop();
        status = MusicStatus.Paused;
    }
    public void resume(){
        if (status == MusicStatus.Playing)
            return;
        clip.setMicrosecondPosition(now);
        play();
        status = MusicStatus.Playing;
    }
    public void restart(){
        stop();
        clip.setMicrosecondPosition(now);
        play();
        status = MusicStatus.Playing;
    }
    public void stop(){
        now = 0L;
        clip.stop();
        status = MusicStatus.Stopped;
    }

    /**
     *
     *
     * @param time The time to seek to in MicroSeconds
     */
    public void seek(long time){
        now = Maths.clamp(time, 0L, clip.getMicrosecondLength()); //keep in mind this will always seek, even when the microseconds are out of range
        clip.setMicrosecondPosition(now);
    }

    public void dispose(){
        clip.close();
    }
}
