package io.siri.joe;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Manages Storage and Playing of Once-Off Sound Effects, In a Queue
 * @author Siri
 */
public class SFXHandler {
    private static final int BUFFER_SIZE = 0xFFFF;
    public HashMap<String, File> clips = new HashMap<>();

    /**
     * Plays a sound effect.
     * @param soundFile The file to play
     * @author Siri
     */
    public void play(File soundFile/*this will eventually take SoundAsset*/) throws UnsupportedAudioFileException, LineUnavailableException, IOException{ //todo still need to make a getAsset function
        AudioInputStream stream = AudioSystem.getAudioInputStream(soundFile);
        AudioFormat format = stream.getFormat();
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
        SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
        line.open(format);
        line.start();
        byte[] bufferBytes = new byte[BUFFER_SIZE];
        int readBytes;
        while ((readBytes = stream.read(bufferBytes)) != -1) {
            line.write(bufferBytes, 0, readBytes);
        }
        line.drain();
        line.close();
        stream.close();
    }

    /**
     * Adds a sound to the SFXHandler HashMap.
     * @param id The Key used to Access the HashMap
     * @param path A filepath to the sound asset.
     * @author Siri
     */
    public void addClip(String id, File path/*this will eventually take SoundAsset*/){
        clips.put(id, path);
    }
    /**
     * Plays a sound effect based on the SFXHandler HashMap.
     * @param id The Key used to Access the HashMap
     * @author Siri
     */
    public void play(String id) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if(clips.containsKey(id)){
            play(clips.get(id));
        }
    }
    /**
     * Plays every sound effect in the SFXHandler HashMap.
     * @author Siri
     */
    public void playAll() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        for (String id: clips.keySet()){
            play(clips.get(id));
        }
    }
    /**
     * Removes a sound from the SFXHandler HashMap.
     * @param id The Key used to Access the HashMap
     * @author Siri
     */
    public void remove(String id){
        clips.remove(id);
    }
    /**
     * Removes all sounds from the SFXHandler HashMap.
     * @author Siri
     */
    public void clear(){
        clips.clear();
    }
}
