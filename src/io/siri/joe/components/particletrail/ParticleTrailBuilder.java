/*
 * Copyright (c) 2024. Kira "Siri" K.
 * Distributed subject to the terms of the Mozilla Public License (MPL) v 2.0
 * See the LICENSE File for more Details
 * If a copy of the MPL was not distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package io.siri.joe.components.particletrail;

import io.siri.joe.GameObject;
import io.siri.joe.Vector2;
import io.siri.joe.Vector2Int;

import java.awt.*;

public class ParticleTrailBuilder {
    GameObject parent;
    Dimension scale;
    long frequency;
    private Vector2Int offset = new Vector2Int();
    private float baseLife = 4.0f;
    private Vector2Int velocity = new Vector2Int();
    private Color c = Color.magenta;

    public ParticleTrailBuilder(GameObject parent, Dimension scale, long frequency) {
        this.parent = parent;
        this.scale = scale;
        this.frequency = frequency;
    }
    public ParticleTrailBuilder withOffset(Vector2 offset) {
        this.offset = offset.toInt();
        return this;
    }
    public ParticleTrailBuilder withVelocity(Vector2Int velocity) {
        this.velocity = velocity;
        return this;
    }

    public ParticleTrailBuilder withColor(Color color) {
        this.c = color;
        return this;
    }

    public ParticleTrailBuilder withLifeTime(float lifeTime) {
        this.baseLife = lifeTime;
        return this;
    }
    public ParticleTrail build() {
        return new ParticleTrail(parent, scale, frequency, c, offset, velocity, baseLife);
    }
}
