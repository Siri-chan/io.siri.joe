package io.siri.joe.audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SFXHandler {
    //this uses javax.sound.sampled.SourceDataLine
    private static final int BUFFER_SIZE = 0xFFFF;
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
}
