package io.siri.joetest;

//Accordioned Imports useful for categorising examples later.
import io.siri.joe.*;
import io.siri.joe.components.BoxCollider;
import io.siri.joe.components.ParticleTrail;
import io.siri.joe.components.SpriteRenderer;
import io.siri.joe.components.TextRenderer;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Player extends GameObject {

    final BoxCollider b;
    final ParticleTrail p;
    SpriteRenderer s;
    TextRenderer t;
    int health = 999;

    public Player(Vector2Int pos, Dimension scale) {
        super(pos, scale);
        this.pos = new Vector2Int(10, 10);
        b = new BoxCollider(this, scale);
        this.components.add(b);
        p = new ParticleTrail(this, new Dimension(10, 10), 50, Color.BLACK, 25);
        this.components.add(p);
        try {
            s = new SpriteRenderer(this, Core.c.d.getSpriteAsset("/20201005103949_1.jpg"), new Dimension(100, 100));
        } catch (Exception e) {
            e.printStackTrace();
            s = null;
        }
        this.components.add(s);
        t = new TextRenderer(this, new Font(Font.SANS_SERIF, Font.PLAIN, 100), Color.green, "HP: 1000", new Vector2Int(Core.c.getWidth()/2, 10));
        this.components.add(t);
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
        t.contents = String.format("HP: %d", health);
        if(health < 990){
            t.color = Color.red;
        } else {
            t.color = Color.green;
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.green);
        //thought: make render private, add a draw method inside that's public
        g.fillRect(pos.x, pos.y, scale.width, scale.height);
    }
}
