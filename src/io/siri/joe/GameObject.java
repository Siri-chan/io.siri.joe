/*
 * Copyright (c) 2022. Kira "Siri" K.
 * Distributed subject to the terms of the Mozilla Public License (MPL) v 2.0
 * See the LICENSE File for more Details
 * If a copy of the MPL was not distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package io.siri.joe;

import java.awt.*;
import java.util.*;

/**
 * An abstract class designed to be overridden for every object in the game. Contains {@link Component}s, Position and Scale by Default.
 * @author Siri
 */
public abstract class GameObject {
    /**
     * The Position of the Object.
     * @see Vector2Int
     */
    protected Vector2Int pos;
    /**
     * The Scale of the Object.
     */
    protected Dimension scale;

    /**
     * The Rendering Layer of the Object.
     */
    protected int layer = 0;

    /**
     * Instantiates a new Game object.
     *
     * @param pos   The Position of the Object.
     * @param scale The Scale of the Object.
     */
    public GameObject(Vector2Int pos, Dimension scale) {
        this.pos = pos;
        this.scale = scale;
    }

    /**
     * @return The Object's Position.
     */
    public Vector2Int getPos() {
        return pos;
    }

    /**
     * Sets the Object's Position
     * @param pos The New Position
     */
    public void setPos(Vector2Int pos) {
        this.pos = pos;
    }

    /**
     * @return The Object's Render Layer.
     */
    public int getLayer() {
        return layer;
    }

    /**
     * Sets the Object's Position
     * @param layer The New Render Layer
     * @apiNote This will change the layer at the end of the next tic(), so it is suggested to only run it in tic()
     *          furthermore, this may cause a delay in the change of layer for a few render frames, so be aware.
     */
    public void setLayer(int layer) {
        Core.c.handler.onChangeRenderLayer();
        this.layer = layer;
    }

    /**
     * Tic. Runs at 60FPS.
     *
     * @param delta The time taken between tics
     * @param inputs Keys being pressed on the frame.
     */
    @Virtual
    public void tic(double delta, int[] inputs){}
    //todo maybe add a second tic that doesnt add inputs for non-player objects, like let the inputs be an overflow or smth idk.

    /**
     * Render. Draws as often as possible.
     *
     * @param g The Graphics Component to Draw to.
     */
    @Virtual
    public void render(Graphics g){}

    /**
     * The Sub-Components of the Object.
     * @see Component
     */
    public LinkedList<Component> components = new LinkedList<>();

    void componentTic(double delta, int[] inputs){
        for (var component : components) {
            component.tic(delta, inputs);
        }
    }

    void componentRender(Graphics g){
        for (var component : components) {
            component.render(g);
        }
    }
}
