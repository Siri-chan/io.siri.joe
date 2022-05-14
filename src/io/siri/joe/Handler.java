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

    void tic() {
        for (GameObject obj : objs) {
            obj.componentTic(inputs);
            obj.tic(inputs);
        }
        inputs = new int[]{};
    }

    void render(Graphics g) {
        for (GameObject obj : objs) {
            obj.componentRender(g);
            obj.render(g);
        }
    }
    
    void dispose(){
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
    }

    /**
     * Removes an object from the Tic/Render loop.
     *
     * @param obj The {@link io.siri.joe.GameObject} to Remove.
     */
    public void removeObject(GameObject obj) {
        this.objs.remove(obj);
    }

    public void addInput(int input) {
        inputs = IntStream.concat(Arrays.stream(inputs), Arrays.stream(new int[]{input})).toArray();
    }
}
