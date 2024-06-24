/*
 * Copyright (c) 2023-2024. Kira "Siri" K.
 * Distributed subject to the terms of the Mozilla Public License (MPL) v 2.0
 * See the LICENSE File for more Details
 * If a copy of the MPL was not distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package io.siri.joe.components;

import io.siri.joe.Component;
import io.siri.joe.GameObject;
import io.siri.joe.Vector2;
import io.siri.joe.Vector2Int;

import java.awt.*;

/**
 * A Transform Component for GameObjects
 * @see io.siri.joe.Component
 * @author Siri
 */
public class Transform extends Component {
    /**
     * The Position of the GameObject.
     */
    public Vector2Int pos = new Vector2Int(0,0);

    /**
     * The Scale of the GameObject.
     */
    public Dimension scale = new Dimension(0,0);

    public Transform(GameObject parent) {
        super(parent);
    }

    public Transform(GameObject parent, Vector2Int pos) {
        super(parent);
        this.pos = pos;
    }

    public Transform(GameObject parent, Dimension scale) {
        super(parent);
        this.scale = scale;
    }

    public Transform(GameObject parent, Vector2Int pos, Dimension scale) {
        super(parent);
        this.pos = pos;
        this.scale = scale;
    }
}
