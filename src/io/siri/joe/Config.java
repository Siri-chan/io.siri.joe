/*
 * Copyright (c) 2022-2024. Kira "Siri" K.
 * Distributed subject to the terms of the Mozilla Public License (MPL) v 2.0
 * See the LICENSE File for more Details
 * If a copy of the MPL was not distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package io.siri.joe;

import java.awt.*;

/**
 * A bunch of parameters passed into {@link io.siri.joe.Core}, all bundled down into a convenience class.
 * @author Siri
 */
public class Config {
    /**
     * The name of the game's Java Package.
     */
    public final String pkgid,
    /**
     * The Author(s) of the Game.
     */
    author;
    /**
     * The Expected Resolution of the Game Window.
     */
    public Dimension resolution = new Dimension(1280, 720);
    /**
     * The Title on the TitleBar of the Game Window.
     */
    public String title = "My JOE game";
    /**
     * The Default Background Color, for when nothing is drawn.
     */
    public Color bg_color = new Color(34, 43, 61);
    /**
     * If true, hides debug info from the console.
     */
    public boolean suppressDebug = true;
    public boolean suppressFps = true;

    /**
     * Creates the base Config.
     *
     * @param pkgid  The name of the game's Java Package.
     * @param author The Author(s) of the Game.
     * @author Siri
     */
    public Config(String pkgid, String author) {
        this.pkgid = pkgid;
        this.author = author;
    }
    //todo maybe window resizable bool
}
