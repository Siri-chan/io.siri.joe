package io.siri.joetest;

import io.siri.joe.*;
import io.siri.joe.Component;

import java.awt.*;

import static java.awt.event.KeyEvent.*;

public class Music extends Component {
    String id;
    public Music(GameObject parent, String id, /*eventually this will take Asset, but for now just gimme string path*/String path){ //this should also take inputs for different music operations, if i decide to add this to the actual codebase
        super(parent);
        this.id = id;
        try {
            Core.c.music.add(id, path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void tic(int[] inputs){
        for (int keyCode : inputs){
            switch (keyCode){
                case VK_SPACE -> {
                    Core.c.music.resume(id);
                }
                case VK_B -> {
                    Core.c.music.stop(id);
                }
            }
        }
    }
}
