package io.siri.joetest;

import io.siri.joe.GameObject;
import io.siri.joe.Vector2Int;

import java.awt.*;
import java.util.Objects;

import static java.awt.event.KeyEvent.VK_Q;

public class TestText extends GameObject {
    /**
     * Instantiates a new Game object.
     *
     * @param pos   The Position of the Object.
     * @param scale The Scale of the Object.
     */
    TextRenderer t;
    public TestText(Vector2Int pos, Dimension scale) {
        super(pos, scale);
        t = new TextRenderer(this, new Font(Font.SANS_SERIF, Font.PLAIN, 12), Color.blue, "test");
        this.components.add(t);
    }

    /**
     * Tic. Runs at 60FPS.
     *
     * @param inputs Keys being pressed on the frame.
     */
    @Override
    public void tic(int[] inputs) {
        for (var input : inputs) {
            if (input != VK_Q)
                return;
            if(Objects.equals(t.contents, "test")) {
                t.contents = "lol";
            } else {
                t.contents = "test";
            }
        }
    }
}
