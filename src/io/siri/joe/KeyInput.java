/*
 * Copyright (c) 2022. Kira "Siri" K.
 * Distributed subject to the terms of the Mozilla Public License (MPL) v 2.0
 * See the LICENSE File for more Details
 * If a copy of the MPL was not distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package io.siri.joe;

import java.awt.event.*;

/**
 * A KeyAdapter Interface for exporting input data to the handler, and in turn, GameObjects.
 * @implNote This is a hacky stub implementation that can almost certainly do more than it does.
 *
 * @author Siri
 */
public class KeyInput extends KeyAdapter {
    final Handler handler;

    public KeyInput(Handler h) {
        handler = h;
    }

    public void keyPressed(KeyEvent k) {
        handler.addInput(k.getKeyCode());
    }
    public void keyReleased(KeyEvent k) {
        handler.removeInput(k.getKeyCode());
    }
}
