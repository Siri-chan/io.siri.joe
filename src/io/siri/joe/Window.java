/*
 * Copyright (c) 2022. Kira "Siri" K.
 * Distributed subject to the terms of the Mozilla Public License (MPL) v 2.0
 * See the LICENSE File for more Details
 * If a copy of the MPL was not distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package io.siri.joe;

import javax.swing.*;
import java.awt.*;

public class Window extends Canvas {
    public Window(Dimension resolution, String title, Core game) {
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
