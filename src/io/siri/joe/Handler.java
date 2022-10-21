package io.siri.joe;

import java.awt.*;
import java.util.*;
import java.util.stream.*;

/**
 * Handler is the main driving class of JOE and handles the backend. It exposes the GameObject add/remove messages.
 * @see io.siri.joe.Core
 * @author Siri
 */
public class Handler {
    public LinkedList<GameObject> objs = new LinkedList<>();
    int[] inputs = {};
    boolean isUpdatingObjs = false;

    void tic(double delta) {
        if (isUpdatingObjs) return;
        for (GameObject obj : objs) {
            obj.componentTic(delta, inputs);
            obj.tic(delta, inputs);
        }
        inputs = new int[]{};
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

    void onChangeRenderLayer() {
        isUpdatingObjs = true;
        objs.sort((o1, o2) -> Integer.compare(o1.getLayer(), o2.getLayer()));
        isUpdatingObjs = false;
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
        inputs = IntStream.concat(Arrays.stream(inputs), Arrays.stream(new int[]{input})).toArray();
    }
}
