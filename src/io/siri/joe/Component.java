package io.siri.joe;

import java.awt.*;

public class Component {
    protected final GameObject parent;
    public Component(GameObject parent){
        this.parent = parent;
    }

    public void tic(int[] inputs){
    }

    public void render(Graphics g) {
    }
}
