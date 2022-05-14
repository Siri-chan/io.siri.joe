package io.siri.joe.audio;

import io.siri.joe.Core;
import io.siri.joe.Maths;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class MusicHandler {
    //this will have a list of music classes, so that we can play/pause/destroy/etc. all of them
    HashMap<String, MusicClip> clips;
    List<String> ids;
    public void add(String id, String path) throws Exception {
        if(clips.containsKey(id)){
            throw new Exception("Music already has clip of id" + id);
        }
        clips.put(id, new MusicClip(new File(Core.c.d.constantDataPath + "/" + path))); //todo: currently saves with serialised data, maybe make seperate asset dir?
        ids.add(id);
    }
    public void play(String id){

    }
    public void pause(String id){

    }
    public void resume(String id){

    }
    public void restart(String id){

    }
    public void stop(String id){

    }
    public void playAll(){
        for (String id: ids) {
            clips.get(id).play();
        }
    }
    public void pauseAll(){

    }
    public void resumeAll(){

    }
    public void restartAll(){

    }
    public void stopAll(){

    }
    //no need for seekAll, as there is legit no reason to.
    public void seek(String id, long time){

    }
}
