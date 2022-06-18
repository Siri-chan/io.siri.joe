package io.siri.joe;

import java.awt.*;

public class Component {
    protected final GameObject parent;
    public Component(GameObject parent){
        this.parent = parent;
    }
    @Virtual
    public void tic(int[] inputs){}
    @Virtual
    public void render(Graphics g) {}
}
