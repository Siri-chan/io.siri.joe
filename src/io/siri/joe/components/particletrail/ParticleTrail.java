/*
 * Copyright (c) 2022-2024. Kira "Siri" K.
 * Distributed subject to the terms of the Mozilla Public License (MPL) v 2.0
 * See the LICENSE File for more Details
 * If a copy of the MPL was not distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package io.siri.joe.components.particletrail;

import io.siri.joe.*;
import io.siri.joe.Component;

import java.awt.*;
import java.util.*;

/**
 * A Particle Trail Component for Moving GameObjects
 * @implNote Should probably be abstracted by some ParticleSystem that has the Particle inner-class and all the particle settings,
 *              because it would make it easier to implement other types of particle effect.
 * @see io.siri.joe.Component
 * @see Particle
 * @author Siri
 */
public class ParticleTrail extends Component {
    private Vector2Int pos;
    private final Vector2Int velocity, offset;
    private final Color c;
    private final Dimension scale;
    private final float life;
    public long lastTime = System.currentTimeMillis();
    public long frequency;
    LinkedList<Particle> particles = new LinkedList<>();

    /**
     * Instantiates a new {@link ParticleTrail}.
     * @deprecated 0.6.0 - Use a {@link ParticleTrailBuilder} instead, to pick-and-choose parameters.
     *
     * @param parent The parent {@linkplain GameObject}. In 99% of cases, use the keyword `this`.
     * @param scale The size of a given particle.
     * @param frequency How often (in milliseconds) a new particle should appear.
     * @param color The color of generated particles.
     * @param offset The offset from the parent object the particles should appear at.
     * @param velocity The velocity of each particle.
     * @param lifeTime The amount of time (in tics / 10) that it takes for the particle to disappear.
     * @author Siri
     */
    @SuppressWarnings("DeprecatedIsStillUsed")
    public ParticleTrail(GameObject parent, Dimension scale, long frequency, Color color, Vector2Int offset, Vector2Int velocity, float lifeTime) {
        super(parent);
        this.scale = scale;
        c = color;
        this.frequency = frequency;
        this.velocity = velocity;
        pos = offset;
        this.offset = offset;
        life = Maths.clamp(lifeTime, 0, 10);
    }

    @Override
    public void tic(double delta, Input inputs) {
        if(removeLock) return;
        if(lastTime + frequency < System.currentTimeMillis()) {
            pos = parent.getPos().orElse(new Vector2Int()).add(offset);
            particles.add(new Particle(this, scale, c, pos, velocity, life));
            lastTime = System.currentTimeMillis();
        }
        for (var part : particles) {
            part.tic(delta);
        }
        if(removeQ.size() != 0)
            remove();
    }

    @Override
    public void render(Graphics g) {
        if(!enabled || removeLock) return;
        for (var part : particles) {
            if(removeLock) break;
            part.render(g);
        }
    }

    LinkedList<Particle> removeQ = new LinkedList<>();
    boolean removeLock;
    void remove(){
        removeLock = true;
        for (var p : removeQ) {
            particles.remove(p);
        }
        removeQ = new LinkedList<>();
        removeLock = false;
    }

    /**
     * A particle subclass for {@link ParticleTrail}.
     *
     * @author Siri
     * @implNote Is non-static because it should never be instantiated without a {@link ParticleTrail}
     * @see ParticleTrail
     */
    @SuppressWarnings("InnerClassMayBeStatic")
    protected class Particle {
        /**
         * The Parent {@link ParticleTrail}.
         */
        ParticleTrail particleTrail;
        private float alpha = 1;
        private Vector2Int velocity;
        private Vector2Int pos;
        private Color c;
        private Dimension scale;
        private float life;
        private final float baseLife;

        /**
         * Instantiates a new {@link Particle}.
         *
         * @param parent The parent {@linkplain GameObject}. In 99% of cases, use the keyword `this`.
         * @param scale The size of a given particle.
         * @param color The color of generated particles.
         * @param velocity The velocity of each particle.
         * @param lifeTime The amount of time (in tics / 10) that it takes for the particle to disappear.
         * @author Siri
         */
        public Particle(ParticleTrail parent, Dimension scale, Color color, Vector2Int pos, Vector2Int velocity, float lifeTime) {
            this.scale = scale;
            particleTrail = parent;
            c = color;
            this.velocity = velocity;
            this.pos = pos;
            life = Maths.clamp(lifeTime, 0, 100);
            baseLife = life;
        }

        private AlphaComposite makeTransparent(float alpha){
            int type = AlphaComposite.SRC_OVER;
            return AlphaComposite.getInstance(type, alpha);
        }

        void tic(double delta) {
            life -= delta;
            alpha = life / baseLife;
            pos = pos.add(velocity);
            if (life < 0.1f){
                particleTrail.removeQ.add(this);
                particleTrail = null;
                alpha = 0;
                velocity = null;
                pos = null;
                c = null;
                scale = null;
            }
        }

        void render(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setComposite(makeTransparent(alpha));
            g.setColor(c);
            g.fillRect(pos.x, pos.y, scale.width, scale.height);
            g2d.setComposite(makeTransparent(1));
        }
    }
}
