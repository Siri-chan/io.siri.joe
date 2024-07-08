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

        try {
            d = JOE.d.load("/scores");
        } catch (Exception _e) {
            //this try/catch is only here in case load has a fit.
        }
        JOE.handler.addObject(new Board());
        System.out.println(d.toString());
    }
}
