package io.siri.joe.audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class SFXHandler {
    //this uses javax.sound.sampled.SourceDataLine
    private static final int BUFFER_SIZE = 0xFFFF;
    public HashMap<String, File> clips = new HashMap<>();
    public void play(File soundFile) throws UnsupportedAudioFileException, LineUnavailableException, IOException{
        AudioInputStream stream = AudioSystem.getAudioInputStream(soundFile);
        AudioFormat format = stream.getFormat();
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
        SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
        line.open(format);
        line.start();
        byte[] bufferBytes = new byte[BUFFER_SIZE];
        int readBytes = -1;
        while ((readBytes = stream.read(bufferBytes)) != -1) {
            line.write(bufferBytes, 0, readBytes);
        }
        line.drain();
        line.close();
        stream.close();
    }
    public void addClip(String id, File path){
        clips.put(id, path);
    }
    public void play(String id) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if(clips.containsKey(id)){
            play(clips.get(id));
        }
    }
    public void playAll() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        for (String id: clips.keySet()){
            play(clips.get(id));
        }
    }
    public void remove(String id){
        clips.remove(id);
    }
    public void clear(String id){
        clips.clear();
    }
}
