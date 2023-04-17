/*
 * Copyright (c) 2022. Kira "Siri" K.
 * Distributed subject to the terms of the Mozilla Public License (MPL) v 2.0
 * See the LICENSE File for more Details
 * If a copy of the MPL was not distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package io.siri.joe;

import java.awt.*;

/**
 * A relatively small class designed to abstract the functionality of Components on GameObjects.
 * @author Siri
 */
public abstract class Component {
    protected final GameObject parent;
    public boolean enabled = true;
    public Component(GameObject parent){
        this.parent = parent;
    }
    @Virtual
    public void tic(double delta, int[] inputs){}
    @Virtual
    public void render(Graphics g){}
}
