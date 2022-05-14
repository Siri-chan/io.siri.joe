package io.siri.joetest;

//Accordioned Imports useful for categorising examples later.
import io.siri.joe.components.BoxCollider;
import io.siri.joe.GameObject;
import io.siri.joe.Vector2Int;

import java.awt.*;

public class Enemy extends GameObject{
    /**
     * Instantiates a new Enemy.
     *
     * @param pos   The Position of the Object.
     * @param scale The Scale of the Object.
     */
    public Enemy(Vector2Int pos, Dimension scale) {
        super(pos, scale);
        b = new BoxCollider(this, scale);
        this.components.add(b);
    }

    final BoxCollider b;
    int speed = 6;

    @Override
    public void tic(int[] inputs) {
        pos.x += speed;
        if(pos.x < 0 || pos.x > Main.cfg.resolution.width){
            speed *= -1;
            pos.x += 2 * speed;
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(pos.x, pos.y, scale.width, scale.height);
    }
}
