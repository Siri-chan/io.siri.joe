package io.siri.joe;

import java.awt.*;

public class Component {
    protected final GameObject parent;
    Component(GameObject parent){
        this.parent = parent;
    }
    void tic(int[] inputs){
    }

    void render(Graphics g) {
    }
}
