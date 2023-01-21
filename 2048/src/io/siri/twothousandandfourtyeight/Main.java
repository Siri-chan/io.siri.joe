/*
 * Copyright (c) 2022. Kira "Siri" K.
 * Distributed subject to the terms of the Mozilla Public License (MPL) v 2.0
 * See the LICENSE File for more Details
 * If a copy of the MPL was not distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package io.siri.twothousandandfourtyeight;

import io.siri.joe.*;

import java.awt.*;

public class Main {
    static Config cfg = new Config("io.siri.twothousandandfourtyeight", "siri");
    public static final int WIDTH = 1280, HEIGHT = 720;
    public static void main(String[] args) {
        ScoreData d = new ScoreData();
        Core JOE = new Core();
        cfg.resolution = new Dimension(WIDTH, HEIGHT);
        cfg.title = "2048 - Built with JOE";
        // todo this should be removed in final build
        cfg.suppressDebug = false;

        JOE.init(cfg);
        /*
        JOE.handler.addObject(new Player(new Vector2Int(), new Dimension(10, 10)));
        JOE.handler.addObject(new Enemy(new Vector2Int(100, 100), new Dimension(10, 10)));
        //todo maybe make an alternate constructor for gameobjects without pos
        //though that would disallow certain components (duh)
        JOE.handler.addObject(new MusicSystem(Vector2Int.ZERO, new Dimension(0,0)));
        JOE.handler.addObject(new TestText(new Vector2Int(100, 100), new Dimension(0,0)));
        */
        try {
            d = JOE.d.load("/scores");
        } catch (Exception _e) {
            //this try/catch is only here in case load has a fit.
        }
        JOE.handler.addObject(new Board());
        System.out.println(d.toString());
    }
}
