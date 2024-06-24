/*
 * Copyright (c) 2022-2024. Kira "Siri" K.
 * Distributed subject to the terms of the Mozilla Public License (MPL) v 2.0
 * See the LICENSE File for more Details
 * If a copy of the MPL was not distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package io.siri.joe;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * A class for handling individual Music Clips.
 * @implNote This uses {@link javax.sound.sampled.Clip},
 * see {@linkplain SFXHandler} for the SourceDataLine Implementation
 * @see MusicHandler
 * @author Siri
 */
public class MusicClip {
    /**
     * The current seeking time.
     * @implNote Microseconds.
     */
    long now = 0L;

    /**
     * The Music Clip itself.
     */
    Clip clip;
    /**
     * The Current Playback Status.
     */
    MusicStatus status;
    /**
     * The Audio Stream used for handling playback.
     */
    AudioInputStream audioStream;

    /**
     * Instantiates a new {@link MusicClip}.
     *
     * @param audioFile The file to be played
     * @throws UnsupportedAudioFileException If the AudioFile isn't supported by Java's Audio Sampling.
     * @throws IOException                   If Loading Files somehow fails.
     * @throws LineUnavailableException      If the Audio Line is unavailable.
     * @author Siri
     */
    public MusicClip(File audioFile) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        audioStream = AudioSystem.getAudioInputStream(audioFile);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        status = MusicStatus.Paused;

    }

    /**
     * Instantiates a new {@link MusicClip}.
     *
     * @param audioLink The file to be played (as a URL)
     * @throws UnsupportedAudioFileException If the AudioFile isn't supported by Java's Audio Sampling.
     * @throws IOException                   If Loading Files somehow fails.
     * @throws LineUnavailableException      If the Audio Line is unavailable.
     * @author Siri
     */
    public MusicClip(URL audioLink) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        audioStream = AudioSystem.getAudioInputStream(audioLink);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        status = MusicStatus.Paused;
    }

    /**
     * Play.
     * @author Siri
     */
    public void play(){
        //this should play from start
        if(status == MusicStatus.Playing)
            return;
        clip.start();
        status = MusicStatus.Playing;
    }

    /**
     * Pause.
     * @author Siri
     */
    public void pause(){
        if(status == MusicStatus.Paused)
            return;
        now = clip.getMicrosecondPosition();
        clip.stop();
        status = MusicStatus.Paused;
    }

    /**
     * Resume.
     * @author Siri
     */
    public void resume(){
        if (status == MusicStatus.Playing)
            return;
        clip.setMicrosecondPosition(now);
        play();
        status = MusicStatus.Playing;
    }

    /**
     * Restart.
     * @author Siri
     */
    public void restart(){
        stop();
        clip.setMicrosecondPosition(now);
        play();
        status = MusicStatus.Playing;
    }

    /**
     * Stop.
     * @author Siri
     */
    public void stop(){
        now = 0L;
        clip.stop();
        status = MusicStatus.Stopped;
    }

    /**
     * Seek.
     *
     * @param time The time to seek to in MicroSeconds
     * @author Siri
     */
    public void seek(long time){
        now = Maths.clamp(time, 0L, clip.getMicrosecondLength()); //keep in mind this will always seek, even when the microseconds are out of range
        clip.setMicrosecondPosition(now);
    }

    /**
     * Dispose.
     * @author Siri
     */
    public void dispose(){
        clip.close();
    }

    /**
     * A status tracking enum for music status.
     * @apiNote Stopped: at now == 0, where Paused: at now == Clip.microsecondlength
     * @author Siri
     */
    public enum MusicStatus {
        Playing,
        Paused,
        Stopped,
    }
}
