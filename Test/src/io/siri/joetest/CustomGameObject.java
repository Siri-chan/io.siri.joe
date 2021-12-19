package io.siri.joetest;

import io.siri.joe.GameObject;
import io.siri.joe.Vector2Int;
import jdk.jshell.spi.ExecutionControl;

import java.awt.*;
import java.awt.event.KeyEvent;

public class CustomGameObject extends GameObject {

    public CustomGameObject(Vector2Int pos, Dimension scale) {
        super(pos, scale);
        this.pos = new Vector2Int(10, 10);
        this.scale = new Dimension(300, 300);
    }

    @Override
    public void tic(int[] inputs) {
        for (int keyCode : inputs) {
            if(keyCode == KeyEvent.VK_UP){
                throw new ExecutionControl.NotImplementedException("not implemented");
                //do move up dumdum
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(pos.x, pos.y, scale.width, scale.height);
    }
}
