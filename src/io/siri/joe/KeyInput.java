package io.siri.joe;

import java.awt.event.*;

/**
 * A KeyAdapter Interface for exporting input data to the handler, and in turn, GameObjects.
 * @implNote This is a hacky stub implementation that can almost certainly do more than it does.
 *
 * @author Siri
 */
public class KeyInput extends KeyAdapter {
    final Handler handler;

    public KeyInput(Handler h) {
        handler = h;
    }

    public void keyPressed(KeyEvent k) {
        handler.addInput(k.getKeyCode());
    }
}
