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
    /**
     * Constructs a new {@link ParticleTrail} when the {@code ParticleTrailBuilder.build()} method is run.
     * Provides granular control over a trail's optional properties.
     *
     * @param parent The parent {@linkplain GameObject}. In 99% of cases, use the keyword `this`.
     * @param scale The size of a given particle.
     * @param frequency How often (in milliseconds) a new particle should appear.
     * @author Siri
     */
    public ParticleTrailBuilder(GameObject parent, Dimension scale, long frequency) {
        this.parent = parent;
        this.scale = scale;
        this.frequency = frequency;
    }

    /**
     * Modifies the underlying builder.
     * @param offset The offset from the parent object the particles should appear at.
     * @author Siri
     */
    public ParticleTrailBuilder withOffset(Vector2 offset) {
        this.offset = offset.toInt();
        return this;
    }
    /**
     * Modifies the underlying builder.
     * @param velocity The velocity of each particle.
     * @author Siri
     */
    public ParticleTrailBuilder withVelocity(Vector2Int velocity) {
        this.velocity = velocity;
        return this;
    }
    /**
     * Modifies the underlying builder.
     * @param color The color of generated particles.
     * @author Siri
     */
    public ParticleTrailBuilder withColor(Color color) {
        this.c = color;
        return this;
    }
    /**
     * Modifies the underlying builder.
     * @param lifeTime The amount of time (in tics / 10) that it takes for the particle to disappear.
     * @author Siri
     */
    public ParticleTrailBuilder withLifeTime(float lifeTime) {
        this.baseLife = lifeTime;
        return this;
    }

    /**
     * The concluding method of the builder.
     * @return a complete {@link ParticleTrail} component.
     */
    @SuppressWarnings("deprecation")
    public ParticleTrail build() {
        // It's all good to use this call internally, we just don't want it being used in the public API where possible.
        return new ParticleTrail(parent, scale, frequency, c, offset, velocity, baseLife);
    }
}
