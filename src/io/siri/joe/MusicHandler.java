/*
 * Copyright (c) 2022. Kira "Siri" K.
 * Distributed subject to the terms of the Mozilla Public License (MPL) v 2.0
 * See the LICENSE File for more Details
 * If a copy of the MPL was not distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package io.siri.joe;

import java.io.File;
import java.util.HashMap;

/**
 * A HandlerClass for MusicClips
 * @see MusicClip
 * @author Siri
 */
public class MusicHandler {
    /**
     * A String-Indexed HashMap of MusicClips
     */
    HashMap<String, MusicClip> clips = new HashMap<>();

    /**
     * Add.
     *
     * @param id   The HashMap Index
     * @param path The Path to the Sound Asset
     * @throws Exception I actually don't know what this throws.
     * @author Siri
     */
    public void add(String id, String path) throws Exception {
        if(clips.containsKey(id)){
            throw new Exception("Music already has clip of id" + id);
        }
        clips.put(id, new MusicClip(new File(Core.c.d.constantDataPath + "/" + path))); //todo: currently saves with serialised data, maybe make seperate asset dir?
        clips.get(id).stop();
    }

    /**
     * Play.
     *
     * @param id   The HashMap Index
     * @author Siri
     */
    public void play(String id){
        if(clips.containsKey(id))
            clips.get(id).play();
    }

    /**
     * Pause.
     *
     * @param id   The HashMap Index
     * @author Siri
     */
    public void pause(String id){
        clips.get(id).pause();
    }

    /**
     * Resume.
     *
     * @param id the id
     * @author Siri
     */
    public void resume(String id){
        if(clips.containsKey(id))
            clips.get(id).resume();
    }

    /**
     * Restart.
     *
     * @param id   The HashMap Index
     * @author Siri
     */
    public void restart(String id){
        if(clips.containsKey(id))
            clips.get(id).restart();
    }

    /**
     * Stop.
     *
     * @param id   The HashMap Index
     * @author Siri
     */
    public void stop(String id){
        if(clips.containsKey(id))
            clips.get(id).stop();
    }

    /**
     * Dispose.
     *
     * @param id   The HashMap Index
     * @author Siri
     */
    public void dispose(String id){
        if(clips.containsKey(id))
            clips.get(id).dispose();
    }

    /**
     * Play All Clips.
     * @author Siri
     */
    public void playAll(){
        for (String id: clips.keySet()) {
            clips.get(id).play();
        }
    }

    /**
     * Pause All Clips.
     * @author Siri
     */
    public void pauseAll(){
        for (String id: clips.keySet()) {
            clips.get(id).pause();
        }
    }

    /**
     * Resume All Clips.
     * @author Siri
     */
    public void resumeAll(){
        for (String id: clips.keySet()) {
            clips.get(id).resume();
        }
    }

    /**
     * Restart All Clips.
     * @author Siri
     */
    public void restartAll(){
        for (String id: clips.keySet()) {
            clips.get(id).restart();
        }
    }

    /**
     * Stop All Clips.
     * @author Siri
     */
    public void stopAll(){
        for (String id: clips.keySet()) {
            clips.get(id).stop();
        }
    }

    /**
     * Dispose of All Clips.
     * @author Siri
     */
    public void disposeAll(){
        for (String id: clips.keySet()) {
            clips.get(id).dispose();
        }
    }

    /**
     * Seek.
     *
     * @param id   The HashMap Index
     * @param time The Seek Destination
     * @implNote There is seriously no use for seekAll()
     * @author Siri
     */
    public void seek(String id, long time){
        if(clips.containsKey(id))
            clips.get(id).seek(time);
    }
}
