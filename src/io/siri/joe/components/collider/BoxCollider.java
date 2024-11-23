/*
 * Copyright (c) 2022-2024. Kira "Siri" K.
 * Distributed subject to the terms of the Mozilla Public License (MPL) v 2.0
 * See the LICENSE File for more Details
 * If a copy of the MPL was not distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package io.siri.joe.components.collider;

import io.siri.joe.Core;
import io.siri.joe.GameObject;
import io.siri.joe.Vector2Int;

import java.awt.*;
import java.util.*;

//todo there should be an abstract collider class that does most of this stuff, making colliders of other shapes simpler in future.
/**
 * A Collider Component for Rectangular GameObjects
 * @see io.siri.joe.Component
 * @author Siri
 */
public class BoxCollider extends Collider {
    protected final Dimension scale;
    /**
     * Determines if a Visible Hitbox Square should be displayed.
     */
    public boolean showCollider = false;
    /**
     * The Color of the collider. Only used while collider is visible.
     */
    public Color c;

    protected Optional<Rectangle> bounds = Optional.empty();

    /**
     * Instantiates a new {@link BoxCollider}.
     *
     * @param parent The parent GameObject. In 99% of cases, use the keyword `this`.
     * @param scale  The scale of the collider. In 99% of cases, use `this.scale`.
     * @apiNote If intending to show the collider at any point, I suggest using the other constructor.
     * @author Siri
     */
    public BoxCollider(GameObject parent, Dimension scale){
        super(parent);
        this.scale = scale;
    }

    /**
     * Instantiates a new {@link BoxCollider}.
     *
     * @param parent The parent GameObject. In 99% of cases, use the keyword `this`.
     * @param scale  The scale of the collider. In 99% of cases, use `this.scale`.
     * @param showCollider_Color The Color of the Collider, whenever the collider is visible using showCollider.
     *                           Should be set if the collider is ever drawn.
     *                           Will also auto-enable showCollider for convenience.
     * @author Siri
     */
    public BoxCollider(GameObject parent, Dimension scale, Color showCollider_Color){
        super(parent, showCollider_Color);
        this.scale = scale;
    }

    @Override
    public Vector2Int getCenter() {
        var pos = parent.getPos().get();
        return new Vector2Int(pos.x + scale.width / 2, pos.y + scale.height / 2);
    }

    /**
     * Determines if there is a Collision with any other objects.
     *
     * @return A {@link LinkedList<GameObject>} of all objects that are currently being collided with.
     * @author Siri
     */
    public LinkedList<GameObject> collision(){
        LinkedList<GameObject> l = new LinkedList<>();
        for (var obj : Core.c.handler.objs) {
            // TODO write a get_all_components in GameObject
            for (var component : obj.components) {
                if (component instanceof Collider collider) {
                    var center = collider.getCenter();
                    // TODO: add a cutoff distance for collision checks.
                    Vector2Int closestPoint = closestPointInBounds(center);
                    if (collider.pointCollides(closestPoint))
                        l.add(obj);
                }
            }
        }
        return l;
    }

    @Override
    boolean pointCollides(Vector2Int point) {
        var pos = parent.getPos().get();
        bounds = Optional.of(this.bounds.orElse(new Rectangle(pos.x, pos.y, scale.width, scale.height)));
        var bound = bounds.get();
        var target = new Rectangle(point.x, point.y, 1, 1);
        return bound.intersects(target);
    }

    @Override
    public Vector2Int closestPointInBounds(Vector2Int target) {
        if (pointCollides(target)) return target;
        // else
        var delta_pos = target.subtract(getCenter());
        // Figure out which quadrant to check the line in.
        int quadrant = 0;
        if (delta_pos.x > 0) {
            if (delta_pos.y > 0) {
                quadrant = 1;
            } else {
                quadrant = 4;
            }
        } else {
            if (delta_pos.y > 0) {
                quadrant = 2;
            } else {
                quadrant = 3;
            }
        }

        // todo abs these based on quadrant
        double gradient = delta_pos.y / delta_pos.x;
        // get the angle of delta_pos
        var theta = Math.asin(gradient);


        return new Vector2Int();
    }

    /**
     * Draws a hitbox if showCollider is true.
     * @author Siri
     */
    @Override
    public void drawHitbox(Graphics g) {
        g.setColor(c);
        g.drawRect(getCenter().x, getCenter().y, scale.width, scale.height);
    }
}
