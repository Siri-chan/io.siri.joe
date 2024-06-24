/*
 * Copyright (c) 2022. Kira "Siri" K.
 * Distributed subject to the terms of the Mozilla Public License (MPL) v 2.0
 * See the LICENSE File for more Details
 * If a copy of the MPL was not distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package io.siri.joetest;

//Accordioned Imports useful for categorising examples later.
import io.siri.joe.components.BoxCollider;
import io.siri.joe.GameObject;
import io.siri.joe.Vector2Int;
import io.siri.joe.components.Transform;

import java.awt.*;

public class Enemy extends GameObject{
    /**
     * Instantiates a new Enemy.
     *
     * @param pos   The Position of the Object.
     * @param scale The Scale of the Object.
     */
    public Enemy(Vector2Int pos, Dimension scale) {
        super();
        tr = new Transform(this, pos, scale);
        this.components.add(tr);
        b = new BoxCollider(this, scale);
        this.components.add(b);
        setLayer(-1);
    }

    final BoxCollider b;
    Transform tr;
    int speed = 6;

    @Override
    public void tic(double delta, int[] inputs) {
        Vector2Int pos = getPos().get();
        pos.x += speed;
        if(pos.x < 0 || pos.x > Main.cfg.resolution.width){
            speed *= -1;
            pos.x += 2 * speed;
        }
    }

    @Override
    public void render(Graphics g) {
        Vector2Int pos = getPos().get();
        Dimension scale = getScale().get();
        g.setColor(Color.red);
        g.fillRect(pos.x, pos.y, scale.width, scale.height);
    }
}
