/*
 * Copyright (c) 2022-2024. Kira "Siri" K.
 * Distributed subject to the terms of the Mozilla Public License (MPL) v 2.0
 * See the LICENSE File for more Details
 * If a copy of the MPL was not distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package io.siri.joe;

import io.siri.joe.components.Transform;

import java.awt.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * An abstract class designed to be overridden for every object in the game. Contains {@link Component}s, Position and Scale by Default.
 * @author Siri
 */
public abstract class GameObject {


    /**
     * The Rendering Layer of the Object.
     */
    protected int layer = 0;

    public GameObject() {}

    /**
     * Instantiates a new Game object.
     *
     * @param pos   The Position of the Object.
     * @deprecated 0.6.0 - Use the empty constructor and add a Transform Component Instead.
     */
    public GameObject(Vector2Int pos) {
        this.components.add(new Transform(this, pos));
    }

    /**
     * Instantiates a new Game object.
     *
     * @param scale The Scale of the Object.
     * @deprecated 0.6.0 - Use the empty constructor and add a Transform Component Instead.
     */
    public GameObject(Dimension scale) {
        this.components.add(new Transform(this, scale));
    }

    /**
     * Instantiates a new Game object.
     *
     * @param pos   The Position of the Object.
     * @param scale The Scale of the Object.
     * @deprecated 0.6.0 - Use the empty constructor and add a Transform Component Instead.
     */
    public GameObject(Vector2Int pos, Dimension scale) {
        this.components.add(new Transform(this, pos, scale));
    }

    /**
     * @return The Object's Position.
     * @apiNote Now returns an Option as of 0.6.0 - Objects without a Transform will fail here
     */
    public Optional<Vector2Int> getPos() {
        Optional<Transform> transform = GetComponent(Transform.class);
        return transform.map(value -> value.pos);
    }

    /**
     * Sets the Object's Position
     * @param pos The New Position
     */
    public void setPos(Vector2Int pos) {
        for (var c: components) {
            if (c instanceof Transform) {
                ((Transform) c).pos = pos;
                return;
            }
        }
    }

    /**
     * Finds the first member {@link Component} of type {@code tClass}
     * @param tClass The class that you want to find.
     * @return The first component attatched to this gameobject that is {@code instanceof} tClass
     * @param <T> A Guard Scoping the typeof tClass to only implementors of {@link Component}
     * @author Siri
     */
    public <T extends Component> Optional<T> GetComponent(Class<T> tClass){
        for (var c: this.components) {
            if (tClass.isInstance(c)) return Optional.of(tClass.cast(c));
        }
        return Optional.empty();
    }

    /**
     * @return The Object's Scale.
     */
    public Optional<Dimension> getScale() {
        Optional<Transform> transform = GetComponent(Transform.class);
        return transform.map(value -> value.scale);
    }

    /**
     * Sets the Object's Scale
     * @param scale The New Scale
     */
    public void setScale(Dimension scale) {
        for (var c: components) {
            if (c instanceof Transform) {
                ((Transform) c).scale = scale;
                return;
            }
        }
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
    public CopyOnWriteArrayList<Component> components = new CopyOnWriteArrayList<>();

    protected void componentTic(double delta, int[] inputs){
        for (var component : components) {
            component.tic(delta, inputs);
        }
    }

    protected void componentRender(Graphics g){
        for (var component : components) {
            component.render(g);
        }
    }
}
