/*
 * Copyright (c) 2022. Kira "Siri" K.
 * Distributed subject to the terms of the Mozilla Public License (MPL) v 2.0
 * See the LICENSE File for more Details
 * If a copy of the MPL was not distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package io.siri.joe;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.*;

/**
 * Handler is the main driving class of JOE and handles the backend. It exposes the GameObject add/remove messages.
 * @see io.siri.joe.Core
 * @author Siri
 */
public class Handler {
    public LinkedList<GameObject> objs = new LinkedList<>();
    int[] inputs = {};
    boolean isUpdatingObjs = false, layerChanged = false;

    void tic(double delta) {
        if (isUpdatingObjs) return;
        for (int i = 0; i < objs.size(); i++) {
            var obj = objs.get(i);
            obj.componentTic(delta, inputs);
            obj.tic(delta, inputs);
        }
        if (layerChanged)
            changeRenderLayer();
    }

    void render(Graphics g) {
        if (isUpdatingObjs) return;
        for (GameObject obj : objs) {
            obj.componentRender(g);
            obj.render(g);
        }
    }
    
    void dispose(){
        if (isUpdatingObjs) return;
        for (GameObject obj :
                objs) {
            removeObject(obj);
        }
    }

    /**
     * Adds an object to the Tic/Render loop.
     *
     * @param obj The {@link io.siri.joe.GameObject} to Add.
     */
    public void addObject(GameObject obj) {
        this.objs.add(obj);
        onChangeRenderLayer();
    }

    /**
     * Changes the order of the objs list, such that it is rendered in layer order.
     */
    void changeRenderLayer() {
        isUpdatingObjs = true;
        objs.sort((o1, o2) -> Integer.compare(o1.getLayer(), o2.getLayer()));
        isUpdatingObjs = false;
        layerChanged = false;
    }

    /**
     * Run when an object changes it's render layer.
     * Sets a flag, such that at the end of the next tic() (ie. when it's guaranteed nothing is using objs)
     * that runs {@code io.siri.joe.Handler.changeRenderLayer()}, when needed
     */
    void onChangeRenderLayer() {
        layerChanged = true;
    }


    /**
     * Removes an object from the Tic/Render loop.
     *
     * @param obj The {@link io.siri.joe.GameObject} to Remove.
     */
    public void removeObject(GameObject obj) {
        this.objs.remove(obj);
    }

    /**
     * Adds an integer keycode to the handler.
     *
     * @param input the input
     * @see java.awt.event.KeyEvent
     * @author Siri
     */
    public void addInput(int input) {
        inputs = IntStream.concat(Arrays.stream(inputs), Arrays.stream(new int[]{input})).distinct().toArray();
    }

    /**
     * Adds an integer keycode to the handler.
     *
     * @param input the input
     * @see java.awt.event.KeyEvent
     * @author Siri
     */
    public void removeInput(int input) {
        inputs = IntStream.range(0, inputs.length)
                .filter(i -> inputs[i] != input)
                .map(i -> inputs[i])
                .toArray();
    }
}
