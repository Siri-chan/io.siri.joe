package io.siri.joe;

import java.io.File;
import java.util.HashMap;

public class MusicHandler {
    //this will have a list of music classes, so that we can play/pause/destroy/etc. all of them
    HashMap<String, MusicClip> clips = new HashMap<>();
    public void add(String id, String path) throws Exception {
        if(clips.containsKey(id)){
            throw new Exception("Music already has clip of id" + id);
        }
        clips.put(id, new MusicClip(new File(Core.c.d.constantDataPath + "/" + path))); //todo: currently saves with serialised data, maybe make seperate asset dir?
        clips.get(id).stop();
    }
    public void play(String id){
        if(clips.containsKey(id))
            clips.get(id).play();
    }
    public void pause(String id){
        clips.get(id).pause();
    }
    public void resume(String id){
        if(clips.containsKey(id))
            clips.get(id).resume();
    }
    public void restart(String id){
        if(clips.containsKey(id))
            clips.get(id).restart();
    }
    public void stop(String id){
        if(clips.containsKey(id))
            clips.get(id).stop();
    }
    public void dispose(String id){
        if(clips.containsKey(id))
            clips.get(id).dispose();
    }
    public void playAll(){
        for (String id: clips.keySet()) {
            clips.get(id).play();
        }
    }
    public void pauseAll(){
        for (String id: clips.keySet()) {
            clips.get(id).pause();
        }
    }
    public void resumeAll(){
        for (String id: clips.keySet()) {
            clips.get(id).resume();
        }
    }
    public void restartAll(){
        for (String id: clips.keySet()) {
            clips.get(id).restart();
        }
    }
    public void stopAll(){
        for (String id: clips.keySet()) {
            clips.get(id).stop();
        }
    }
    public void disposeAll(){
        for (String id: clips.keySet()) {
            clips.get(id).dispose();
        }
    }
    //no need for seekAll, as there is legit no reason to.
    public void seek(String id, long time){
        if(clips.containsKey(id))
            clips.get(id).seek(time);
    }
}
