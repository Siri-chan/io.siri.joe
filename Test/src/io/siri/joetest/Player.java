package io.siri.joetest;

//Accordioned Imports useful for categorising examples later.
import io.siri.joe.BoxCollider;
import io.siri.joe.GameObject;
import io.siri.joe.ParticleTrail;
import io.siri.joe.Vector2Int;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Player extends GameObject {

    final BoxCollider b;
    final ParticleTrail p;
    int health = 999;

    public Player(Vector2Int pos, Dimension scale) {
        super(pos, scale);
        this.pos = new Vector2Int(10, 10);
        b = new BoxCollider(this, scale);
        this.components.add(b);
        p = new ParticleTrail(this, new Dimension(10, 10), 50, Color.BLACK, 25);
        this.components.add(p);
    }

    @Override
    public void tic(int[] inputs) {
        //Input Handling
        for (int keyCode : inputs) {
            //this shouldn't be a switch, probably breaks for loop if input matches event
            switch (keyCode) {
                case KeyEvent.VK_UP -> pos = pos.add(Vector2Int.UP.multiply(6));

                case KeyEvent.VK_DOWN -> pos = pos.add(Vector2Int.DOWN.multiply(6));

                case KeyEvent.VK_LEFT -> pos = pos.add(Vector2Int.LEFT.multiply(6));

                case KeyEvent.VK_RIGHT -> pos = pos.add(Vector2Int.RIGHT.multiply(6));

                case KeyEvent.VK_SPACE -> p.enabled = !p.enabled;
            }
        }

        //Collision Checks
        LinkedList<GameObject> colliding = b.collision();
        for (var obj : colliding){
            if(obj.getClass() == Enemy.class) {
                health--;
                System.out.printf("health: %d\n", health);
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.green);
        //thought: make render private, add a draw method inside that's public
        g.fillRect(pos.x, pos.y, scale.width, scale.height);
    }
}
