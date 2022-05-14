package io.siri.joetest;

import io.siri.joe.*;

import java.awt.*;

import static java.awt.event.KeyEvent.*;

public class MusicSystem extends GameObject{
    /**
     * Instantiates a new Game object.
     *
     * @param pos   The Position of the Object.
     * @param scale The Scale of the Object.
     */
    public MusicSystem(Vector2Int pos, Dimension scale) { //todo: this should really have an alternate constructor
        super(pos, scale);
        components.add(new Music(this, "music", "test.wav"));
    }
}
