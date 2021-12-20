// Name of the package must be same as the directory
// under which this file is saved
package io.siri.joe;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Core extends Canvas implements Runnable {
    public static Core c;
    Thread thread; //todo: multi-threaded, 1 for logic, 1 for
    boolean running = false;
    Config config;
    public Handler handler;
    public Random rand = new Random();
    public KeyInput keyInput;
    Window win;
    public static final double ticCount = 60.0;

    public void init(Config cfg) {
        config = cfg;
        handler = new Handler();
        keyInput = new KeyInput(handler);
        this.addKeyListener(keyInput);
        win = new Window(cfg.resolution, cfg.title, this);
    }

    public synchronized void start() {
        if(c != null) return;
        c = this;
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        final double ns = 1000000000 / ticCount;
        double delta = 0;
        long fpsTimer = System.currentTimeMillis();
        int frames = 0, framesLastFPSCheck = 0;
        requestFocus();
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tic(); //todo give tic the delta
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
        System.out.printf("%s %s %s%n", new SimpleDateFormat("HH:mm:ss.SSS").format(new Date()), prefix, msg);
    }
    void tic() {
        handler.tic();
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
