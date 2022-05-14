package io.siri.joe.audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SFXClip {
    public AudioInputStream stream;
    public AudioFormat format;
    public DataLine.Info info;
    public SourceDataLine line;
    public SFXClip (File soundFile) throws UnsupportedAudioFileException, IOException, LineUnavailableException { //todo make this an asset path like music
        stream = AudioSystem.getAudioInputStream(soundFile);
        format = stream.getFormat();
        info = new DataLine.Info(SourceDataLine.class, format);
        line = (SourceDataLine) AudioSystem.getLine(info);
    }
    public void dispose() throws IOException { //idk if user should be in charge of disposing this
        line.drain();
        line.close();
        stream.close();
    }
}
