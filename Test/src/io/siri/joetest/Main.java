package io.siri.joetest;

import io.siri.joe.*;
import io.siri.joe.audio.MusicClip;

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

        //music test
        //eventually core will initialise a MusicHandler, and you will just need to add the music you want with an ID
        MusicClip m;
        try {
            m = new MusicClip(new File(JOE.d.constantDataPath + "/test.wav"));
            m.play();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}