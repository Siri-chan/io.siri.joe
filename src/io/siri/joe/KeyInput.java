package io.siri.joe;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    Handler handler;
    public KeyInput(Handler h){
        handler = h;
    }
    public void keyPressed(KeyEvent k){
        int key = k.getKeyCode();
        handler.addInput(key);
    }
}
