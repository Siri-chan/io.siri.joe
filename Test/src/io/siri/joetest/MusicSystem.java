/*
 * Copyright (c) 2022. Kira "Siri" K.
 * Distributed subject to the terms of the Mozilla Public License (MPL) v 2.0
 * See the LICENSE File for more Details
 * If a copy of the MPL was not distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package io.siri.joetest;

import io.siri.joe.*;
import io.siri.joe.components.MusicComponent;

import java.awt.*;

import static java.awt.event.KeyEvent.*;

public class MusicSystem extends GameObject{
    /**
     * Instantiates a new Game object.
     *
     * @param pos   The Position of the Object.
     * @param scale The Scale of the Object.
     */
    public MusicSystem(Vector2Int pos, Dimension scale) { //todo: this should really have an alternate constructor,
                                                                // pos and scale are unnecessary here
        super(pos, scale);
        MusicComponent.MusicControls m = new MusicComponent.MusicControls();
        m.resume = VK_SPACE;
        m.pause = VK_M;
        m.restart = VK_N;
        components.add(new MusicComponent(this, "music", "test.wav", m));
    }
}
