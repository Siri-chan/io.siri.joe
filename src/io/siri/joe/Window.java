package io.siri.joe;

import javax.swing.*;
import java.awt.*;

public class Window extends Canvas {
    public Window(Dimension resolution, String title, Core game){
        JFrame frame = new JFrame(title);
        frame.setPreferredSize(resolution);
        frame.setMinimumSize(resolution);
        frame.setMaximumSize(resolution);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        game.start();
    }
}
