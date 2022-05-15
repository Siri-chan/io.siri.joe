package io.siri.joetest;

import io.siri.joe.*;

import java.awt.*;
import java.io.*;

public class Main {
    static Config cfg = new Config("io.siri.joetest", "siri");
    public static void main(String[] args) {
        SaveData d = new SaveData();
        Core JOE = new Core();
        cfg.resolution = new Dimension(1280, 720);
        cfg.title = "Test";
        cfg.suppressDebug = false;
        JOE.init(cfg);
        JOE.handler.addObject(new Player(new Vector2Int(), new Dimension(10, 10)));
        JOE.handler.addObject(new Enemy(new Vector2Int(100, 100), new Dimension(10, 10)));
        //todo maybe make an alternate constructor
        JOE.handler.addObject(new MusicSystem(Vector2Int.ZERO, new Dimension(0,0)));
        try {
            JOE.d.save(d, "/ser.ser");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        SaveData q;
        try {
            q = JOE.d.load("/ser.ser");
        } catch (FileNotFoundException e) {
            q = new SaveData();
            e.printStackTrace();
        }
        System.out.println(q.test);
        try {
            JOE.sfx.play(new File(JOE.d.constantDataPath + "/testsfx.wav")); //this disposes the clip
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            JOE.sfx.addClip("hit", new File(JOE.d.constantDataPath + "/testsfx.wav"));
            JOE.sfx.addClip("hit2", new File(JOE.d.constantDataPath + "/test.wav"));
            JOE.sfx.play("hit");
            //this creates a queue.
            JOE.sfx.play("hit2");
            JOE.sfx.play("hit");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}