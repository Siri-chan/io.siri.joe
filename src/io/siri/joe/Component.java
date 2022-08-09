package io.siri.joe;

import java.awt.*;

/**
 * A relatively small class designed to abstract the functionality of Components on GameObjects.
 * @author Siri
 */
public class Component {
    protected final GameObject parent;
    public boolean enabled = true;
    public Component(GameObject parent){
        this.parent = parent;
    }
    @Virtual
    public void tic(int[] inputs){}
    @Virtual
    public void render(Graphics g){}
}
