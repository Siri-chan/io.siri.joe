package io.siri.joe;

import java.awt.event.*;

public class KeyInput extends KeyAdapter {
    final Handler handler;

    public KeyInput(Handler h) {
        handler = h;
    }

    public void keyPressed(KeyEvent k) {
        int key = k.getKeyCode();
        handler.addInput(key);
    }
}
