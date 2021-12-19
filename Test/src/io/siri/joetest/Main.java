/* import 'MyClass' class from 'names' myPackage */
package io.siri.joetest;
import io.siri.joe.*;

import java.awt.*;

public class Main
{
    public static void main(String[] args)
    {
        Core JOE = new Core();
        Config cfg = new Config();
        cfg.resolution = new Dimension(1280, 720);
        cfg.title = "Test";
        JOE.init(cfg);
        JOE.handler.addObject(new CustomGameObject(new Vector2Int(), new Dimension()));
    }
}