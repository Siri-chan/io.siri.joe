package io.siri.joe.components;

import io.siri.joe.*;
import io.siri.joe.Component;

public class MusicComponent extends Component {
    String id;
    MusicControls musicControls;
    public MusicComponent(GameObject parent, String id, /*eventually this will take Asset, but for now just gimme string path*/String path){
        super(parent);
        this.id = id;
        musicControls = new MusicControls();
        try {
            Core.c.music.add(id, path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public MusicComponent(GameObject parent, String id, /*todo: eventually this will take Asset, but for now just gimme string path*/String path, MusicControls m){
        super(parent);
        this.id = id;
        musicControls = m;
        try {
            Core.c.music.add(id, path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void tic(int[] inputs){
        for (int keyCode : inputs){
            //this cant be a switch bc music controls arent constant.
            if (keyCode == musicControls.play) {
                Core.c.music.play(id);
            }
            if (keyCode == musicControls.resume) {
                Core.c.music.resume(id);
            }
            if (keyCode == musicControls.pause) {
                Core.c.music.pause(id);
            }
            if (keyCode == musicControls.stop) {
                Core.c.music.stop(id);
            }
            if (keyCode == musicControls.restart) {
                Core.c.music.restart(id);
            }
        }
    }

    //this has to be a full class and not just a struct because fuck java.
    //because this is here, todo: make a pause/resume toggle.
    public static class MusicControls {
        public int play = -1;
        public int resume = -1;
        public int pause = -1;
        public int stop = -1;
        public int restart = -1;
    }
}
