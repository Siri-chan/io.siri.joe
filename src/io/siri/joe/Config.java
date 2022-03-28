package io.siri.joe;

import java.awt.Dimension;
import java.awt.Color;

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

    /**
     * Creates the very base Config.
     *
     * @param pkgid  The name of the game's Java Package.
     * @param author The Author(s) of the Game.
     */
    public Config(String pkgid, String author) {
        this.pkgid = pkgid;
        this.author = author;
    }
    //maybe window resizable bool
}
