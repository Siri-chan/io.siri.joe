/*
 * Copyright (c) 2024. Kira "Siri" K.
 * Distributed subject to the terms of the Mozilla Public License (MPL) v 2.0
 * See the LICENSE File for more Details
 * If a copy of the MPL was not distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package io.siri.joe.components.collider;

import io.siri.joe.*;
import io.siri.joe.Component;

import java.awt.*;
import java.util.LinkedList;

public abstract class Collider extends Component{
    /**
     * Determines if a Visible Hitbox Square should be displayed.
     */
    public boolean showCollider = false;
    /**
     * The Color of the collider. Only used while collider is visible.
     */
    public Color c;

    public Collider(GameObject parent){
        super(parent);
    }
    public Collider(GameObject parent, Color showCollider_Color){
        super(parent);
        this.showCollider = true;
        this.c = showCollider_Color;
    }
    @Virtual
    public Vector2Int getCenter() {
        var posOpt = parent.getPos();
        //todo this should return an option
        return posOpt.orElse(null);
    }

    public Vector2Int closestPointInBounds(Vector2Int target) {
        return parent.getPos().get();
    }

    @Virtual
    boolean pointCollides(Vector2Int point) {
        var pos = parent.getPos().get();
        return (point.x == pos.x && point.y == pos.y);
    }

    /**
     * Determines if there is a Collision with any other objects.
     *
     * @return A {@link LinkedList<GameObject>} of all objects that are currently being collided with.
     * @author Siri
     */
    @Virtual
    public LinkedList<GameObject> collision(){
        return new LinkedList<>();
    }

    /**
     * Draws a hitbox if showCollider is true.
     * @author Siri
     */
    public void render(Graphics g) {
        if(!enabled || !showCollider) return;
        drawHitbox(g);
    }
    @Virtual
    public void drawHitbox(Graphics g) {
        g.setColor(c);
        g.drawRect(getCenter().x, getCenter().y, 1, 1);
    }
}
