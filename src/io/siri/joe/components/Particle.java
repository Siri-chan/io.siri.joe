package io.siri.joe.components;

import io.siri.joe.Maths;
import io.siri.joe.Vector2Int;

import java.awt.*;

public class Particle {
    ParticleTrail particleTrail;
    private float alpha = 1;
    private Vector2Int velocity = new Vector2Int();
    private Vector2Int pos;
    private Color c = Color.magenta;
    private Dimension d;
    private float life = 4.0f;
    private final float baseLife;
    public Particle(ParticleTrail parent, Dimension d, Color color, Vector2Int pos, Vector2Int velocity, float lifeTime) {
        this.d = d;
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

    void tic() {
        life -= 0.1f; //todo make this delta
        alpha = life / baseLife;
        pos = pos.add(velocity);
        if (life < 0.1f){
            particleTrail.removeQ.add(this);
            particleTrail = null;
            alpha = 0;
            velocity = null;
            pos = null;
            c = null;
            d = null;
        }
    }

    void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(makeTransparent(alpha));
        g.setColor(c);
        g.fillRect(pos.x, pos.y, d.width, d.height);
        g2d.setComposite(makeTransparent(1));
    }
}

