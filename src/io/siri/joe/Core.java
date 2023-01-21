/*
 * Copyright (c) 2022. Kira "Siri" K.
 * Distributed subject to the terms of the Mozilla Public License (MPL) v 2.0
 * See the LICENSE File for more Details
 * If a copy of the MPL was not distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

// Name of the package must be same as the directory
// under which this file is saved
package io.siri.joe;

import java.awt.*;
import java.awt.image.*;
import java.text.*;
import java.util.*;

/**
 * Core is the base-class of JOE and the primary way of interacting with it.
 * Takes input from {@link io.siri.joe.Config} when first initialised.
 * @see io.siri.joe.Handler
 * @author Siri
 */
public class Core extends Canvas implements Runnable {
    public static Core c;
    public DataManager d;
    private Thread thread; //todo: multi-threaded, 1 for logic, 1 for graphics ig
    boolean running = false;
    private Config config;
    public Handler handler;
    public Random rand = new Random();
    public KeyInput keyInput;
    public MusicHandler music;
    public SFXHandler sfx;
    Window win;
    public static final double TARGET_TICS_PER_SECOND = 60.0;

    /**
     * Initialises the Core.
     * This is done outside a constructor to give more control of when the game starts.
     *
     * @param cfg The Configuration of Core.
     * @see io.siri.joe.Config
     * @author Siri
     */
    public void init(Config cfg) {
        if(c != null) return;
        c = this;
        config = cfg;
        d = new DataManager(cfg);
        handler = new Handler();
        keyInput = new KeyInput(handler);
        this.addKeyListener(keyInput);
        music = new MusicHandler();
        sfx = new SFXHandler();
        win = new Window(cfg.resolution, cfg.title, this);
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        final double ns = 1000000000 / TARGET_TICS_PER_SECOND;
        double delta = 0;
        long fpsTimer = System.currentTimeMillis();
        int frames = 0, framesLastFPSCheck = 0;
        requestFocus();
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tic(delta);
                delta--;
            }
            if (!running) break;
            render();
            frames++;
            if (System.currentTimeMillis() - fpsTimer > 250) {
                fpsTimer += 250;
                Log("FPS: " + ((frames - framesLastFPSCheck) * 4));
                framesLastFPSCheck = frames;
            }
        }
        stop();
    }
    static void Log(String msg){
        if(Core.c.config.suppressDebug) return;
        final String prefix = "- [JOE]:"; //maybe add some more info here
        System.out.printf("%s %s %s\n", new SimpleDateFormat("HH:mm:ss.SSS").format(new Date()), prefix, msg);
    }
    static void LogError(String msg){
        if(Core.c.config.suppressDebug) return;
        final String prefix = "- [JOE Error]:"; //maybe add some more info here
        System.err.printf("%s %s %s\n", new SimpleDateFormat("HH:mm:ss.SSS").format(new Date()), prefix, msg);
    }

    void tic(double delta) {
        handler.tic(delta);
    }

    void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(config.bg_color);
        g.fillRect(0, 0, config.resolution.width, config.resolution.height);
        handler.render(g);
        g.dispose();
        bs.show();
    }

    public synchronized void stop() {
        music.disposeAll();
        handler.dispose();
        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            running = false;
        }
        System.exit(0);
    }
}
