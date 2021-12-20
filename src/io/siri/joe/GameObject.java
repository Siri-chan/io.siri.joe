package io.siri.joe;

import java.awt.*;
import java.util.*;

public abstract class GameObject {
    protected Vector2Int pos;
    protected Dimension scale;

    public GameObject(Vector2Int pos, Dimension scale) {
        this.pos = pos;
        this.scale = scale;
    }

    public Vector2Int getPos() {
        return pos;
    }

    public void setPos(Vector2Int pos) {
        this.pos = pos;
    }

    public abstract void tic(int[] inputs);

    public abstract void render(Graphics g);
    public LinkedList<Component> components = new LinkedList<>();

    public void componentTic(){
        for (var component : components) {
            component.tic();
        }
    }
    public void componentRender(Graphics g){
        for (var component : components) {
            component.render(g);
        }
    }
}
