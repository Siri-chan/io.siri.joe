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
