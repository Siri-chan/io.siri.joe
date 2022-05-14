package io.siri.joe.components;

import io.siri.joe.Component;
import io.siri.joe.Core;
import io.siri.joe.GameObject;

import java.awt.*;
import java.util.*;

public class BoxCollider extends Component {
    public boolean showCollider = false;
    protected Dimension scale;

    public BoxCollider(GameObject parent, Dimension scale){
        super(parent);
        this.scale = scale;
    }
    public BoxCollider(GameObject parent, Dimension scale, boolean showCollider){
        super(parent);
        this.scale = scale;
        this.showCollider = showCollider;
    }
    Rectangle getBounds() {
        return new Rectangle(parent.getPos().x, parent.getPos().y, scale.width, scale.height);
    }
    @Override
    public void tic(int[] inputs) {

    }

    public LinkedList<GameObject> collision(){
        LinkedList<GameObject> l = new LinkedList<>();
        for (var obj : Core.c.handler.objs) {
            for(var component : obj.components) {
                if(component.getClass() == this.getClass()) {
                    if (getBounds().intersects(((BoxCollider) component).getBounds())) l.add(obj);
                }
            }
        }
        return l;
    }

    @Override
    public void render(Graphics g) {
        if(!showCollider) return;
        Graphics2D g2d = (Graphics2D) g;
        g2d.draw(getBounds());
    }
}
