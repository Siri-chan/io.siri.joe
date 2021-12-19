package io.siri.joe;

import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.IntStream;

public class Handler {
    LinkedList<GameObject> objs = new LinkedList<>();
    int[] inputs = {};
    public void tic(){
        for (GameObject obj : objs) {
            obj.tic(inputs);
        }
        inputs = new int[]{};
    }
    public void render(Graphics g){
        for (GameObject obj : objs) {
            obj.render(g);
        }
    }
    public void addObject(GameObject obj){
        this.objs.add(obj);
    }
    public void removeObject(GameObject obj){
        this.objs.remove(obj);
    }
    public void addInput(int input){
        inputs = IntStream.concat(Arrays.stream(inputs), Arrays.stream(new int[]{input})).toArray();
    }
}
