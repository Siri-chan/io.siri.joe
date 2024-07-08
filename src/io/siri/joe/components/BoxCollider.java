/*
 * Copyright (c) 2022-2024. Kira "Siri" K.
 * Distributed subject to the terms of the Mozilla Public License (MPL) v 2.0
 * See the LICENSE File for more Details
 * If a copy of the MPL was not distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package io.siri.joe.components;

import io.siri.joe.Component;
import io.siri.joe.Core;
import io.siri.joe.GameObject;

import java.awt.*;
import java.util.*;

//todo there should be an abstract collider class that does most of this stuff, making colliders of other shapes simpler in future.
/**
 * A Collider Component for Rectangular GameObjects
 * @see io.siri.joe.Component
 * @author Siri
 */
public class BoxCollider extends Component {
    protected final Dimension scale;
    /**
     * Determines if a Visible Hitbox Square should be displayed.
     */
    public boolean showCollider = false;
    /**
     * The Color of the collider. Only used while collider is visible.
     */
    public Color c;

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
        super(parent);
        this.scale = scale;
        this.showCollider = true;
        this.c = showCollider_Color;
    }

    /**
     * Gets the bounds of the Box Collider, for convenience drawing hitboxes.
     *
     * @return The collidable area as a {@link java.awt.Rectangle}
     * @author Siri
     */
    Rectangle getBounds() {
        var posOpt = parent.getPos();
        if (posOpt.isPresent())
            return new Rectangle(posOpt.get().x, posOpt.get().y, scale.width, scale.height);
        return null; //todo this should return an option
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
            for(var component : obj.components) {
                if(component.getClass() == this.getClass()) {
                    var bounds = getBounds();
                    var otherBounds = ((BoxCollider) component).getBounds();

                    if (otherBounds != null && bounds != null && bounds.intersects((otherBounds)))
                        l.add(obj);
                }
            }
        }
        return l;
    }

    /**
     * Draws a hitbox if showCollider is true.
     * @author Siri
     */
    @Override
    public void render(Graphics g) {
        if(!enabled || !showCollider) return;
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(c);
        g2d.draw(getBounds());
    }
}
