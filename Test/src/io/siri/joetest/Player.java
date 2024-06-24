/*
 * Copyright (c) 2022. Kira "Siri" K.
 * Distributed subject to the terms of the Mozilla Public License (MPL) v 2.0
 * See the LICENSE File for more Details
 * If a copy of the MPL was not distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package io.siri.joetest;

//Accordioned Imports useful for categorising examples later.
import io.siri.joe.*;
import io.siri.joe.components.BoxCollider;
import io.siri.joe.components.Transform;
import io.siri.joe.components.particletrail.ParticleTrail;
import io.siri.joe.components.SpriteRenderer;
import io.siri.joe.components.TextRenderer;
import io.siri.joe.components.particletrail.ParticleTrailBuilder;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Player extends GameObject {

    final BoxCollider b;
    final ParticleTrail p;
    SpriteRenderer s;
    TextRenderer t;
    Transform tr;
    int health = 999;
    boolean spaceDown = false;

    public Player(Vector2Int pos, Dimension scale) {
        super();
        tr = new Transform(this, pos, scale);
        this.components.add(tr);
        b = new BoxCollider(this, scale);
        this.components.add(b);
        p = new ParticleTrailBuilder(this, new Dimension(10, 10), 50).withColor(Color.BLACK).withLifeTime(25).build();
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

    void move(Vector2Int direction) {
        var posOption = getPos();
        if (posOption.isEmpty()) return;
        setPos(posOption.get().add(direction));
    }
    @Override
    public void tic(double delta, int[] inputs) {
        //Input Handling
        for (int keyCode : inputs) {
            switch (keyCode) {
                case KeyEvent.VK_UP -> move(Vector2Int.UP.multiply(6));

                case KeyEvent.VK_DOWN -> move(Vector2Int.DOWN.multiply(6));

                case KeyEvent.VK_LEFT -> move(Vector2Int.LEFT.multiply(6));

                case KeyEvent.VK_RIGHT -> move(Vector2Int.RIGHT.multiply(6));

                case KeyEvent.VK_SPACE -> { if (!spaceDown) { p.enabled =  !p.enabled; spaceDown = true;} }
            }
        }

        if (Arrays.stream(inputs).noneMatch(el -> el == KeyEvent.VK_SPACE)) {
            spaceDown = false;
        }

        //Collision Checks
        LinkedList<GameObject> colliding = b.collision();
        for (var obj : colliding){
            if(obj.getClass() == Enemy.class) {
                health--;
                System.out.printf("health: %d\n", health);
            }
        }
        //todo should also check if player is out of bounds

        //Health Popup
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
        var opt = getPos();
        if (opt.isEmpty()) return;
        var pos = opt.get();

        var opt2 = getScale();
        if (opt2.isEmpty()) return;
        var scale = opt2.get();

        g.fillRect(pos.x, pos.y, scale.width, scale.height);
    }
}
