package io.siri.joe;

import java.awt.*;
import java.awt.event.KeyListener;

public abstract class GameObject {
    protected Vector2Int pos;
    protected Dimension scale;

    public GameObject(Vector2Int pos, Dimension scale /*, ID id*/) {
        this.pos = pos; this.scale = scale;
    }

    public Dimension getPos() {
        return scale;
    }

    public void setPos(Dimension scale) {
        this.scale = scale;
    }

    public Dimension getScale() {
        return scale;
    }

    public void setScale(Dimension scale) {
        this.scale = scale;
    }

    public abstract void tic(int[] inputs);
    public abstract void render(Graphics g);

}
