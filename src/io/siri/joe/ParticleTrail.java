package io.siri.joe;

import java.awt.*;
import java.util.*;

public class ParticleTrail extends Component{
    private float alpha = 1;
    private Vector2Int velocity = new Vector2Int();
    private Vector2Int pos, offset = new Vector2Int();
    private Color c = Color.magenta;
    private Dimension d;
    private float life = 4.0f, baseLife;
    public boolean enabled = true; //todo make this on every component
    public long lasttime = System.currentTimeMillis();
    public long frequency;

    LinkedList<Particle> particles = new LinkedList<>();

    public ParticleTrail(GameObject parent, Dimension d, long frequency) {
        super(parent);
        this.d = d;
        this.frequency = frequency;
        pos = parent.pos;
        baseLife = life;
    }
    public ParticleTrail(GameObject parent, Dimension d, long frequency, Vector2Int velocity) {
        super(parent);
        this.d = d;
        this.frequency = frequency;
        this.velocity = velocity;
        pos = parent.pos;
        baseLife = life;
    }
    public ParticleTrail(GameObject parent, Dimension d, long frequency, Vector2 offset) {
        super(parent);
        this.d = d;
        this.frequency = frequency;
        pos = offset.toInt();
        this.offset = offset.toInt();
        baseLife = life;
    }
    public ParticleTrail(GameObject parent, Dimension d, long frequency, Vector2Int offset, Vector2Int velocity) {
        super(parent);
        this.d = d;
        this.frequency = frequency;
        this.velocity = velocity;
        pos = offset;
        this.offset = offset;
        baseLife = life;
    }
    public ParticleTrail(GameObject parent, Dimension d, long frequency, Color color) {
        super(parent);
        this.d = d;
        this.frequency = frequency;
        c = color;
        pos = parent.pos;
        baseLife = life;
    }
    public ParticleTrail(GameObject parent, Dimension d, long frequency, Color color, Vector2Int velocity) {
        super(parent);
        this.d = d;
        this.frequency = frequency;
        c = color;
        this.velocity = velocity;
        pos = parent.pos;
        baseLife = life;
    }
    public ParticleTrail(GameObject parent, Dimension d, long frequency, Color color, Vector2 offset) {
        super(parent);
        this.d = d;
        this.frequency = frequency;
        c = color;
        pos = offset.toInt();
        this.offset = offset.toInt();
        baseLife = life;
    }
    public ParticleTrail(GameObject parent, Dimension d, long frequency, Color color, Vector2Int offset, Vector2Int velocity) {
        super(parent);
        this.d = d;
        this.frequency = frequency;
        c = color;
        this.velocity = velocity;
        pos = offset;
        this.offset = offset;
        baseLife = life;
    }
    public ParticleTrail(GameObject parent, Dimension d, long frequency, float lifeTime) {
        super(parent);
        this.d = d;
        this.frequency = frequency;
        pos = parent.pos;
        life = Maths.clamp(lifeTime, 0, 10);
        baseLife = life;
    }
    public ParticleTrail(GameObject parent, Dimension d, long frequency, Vector2Int velocity, float lifeTime) {
        super(parent);
        this.d = d;
        this.frequency = frequency;
        this.velocity = velocity;
        pos = parent.pos;
        life = Maths.clamp(lifeTime, 0, 10);
        baseLife = life;
    }
    public ParticleTrail(GameObject parent, Dimension d, long frequency, Vector2 offset, float lifeTime) {
        super(parent);
        this.d = d;
        this.frequency = frequency;
        pos = offset.toInt();
        this.offset = offset.toInt();
        life = Maths.clamp(lifeTime, 0, 10);
        baseLife = life;
    }
    public ParticleTrail(GameObject parent, Dimension d, long frequency, Vector2Int offset, Vector2Int velocity, float lifeTime) {
        super(parent);
        this.d = d;
        this.frequency = frequency;
        this.velocity = velocity;
        pos = offset;
        this.offset = offset;
        life = Maths.clamp(lifeTime, 0, 10);
        baseLife = life;
    }
    public ParticleTrail(GameObject parent, Dimension d, long frequency, Color color, float lifeTime) {
        super(parent);
        this.d = d;
        this.frequency = frequency;
        c = color;
        pos = parent.pos;
        life = Maths.clamp(lifeTime, 0, 10);
        baseLife = life;
    }
    public ParticleTrail(GameObject parent, Dimension d, long frequency, Color color, Vector2Int velocity, float lifeTime) {
        super(parent);
        this.d = d;
        this.frequency = frequency;
        c = color;
        this.velocity = velocity;
        pos = parent.pos;
        life = Maths.clamp(lifeTime, 0, 10);
        baseLife = life;
    }
    public ParticleTrail(GameObject parent, Dimension d, long frequency, Color color, Vector2 offset, float lifeTime) {
        super(parent);
        this.d = d;
        this.frequency = frequency;
        c = color;
        pos = offset.toInt();
        this.offset = offset.toInt();
        life = Maths.clamp(lifeTime, 0, 10);
        baseLife = life;
    }
    public ParticleTrail(GameObject parent, Dimension d, long frequency, Color color, Vector2Int offset, Vector2Int velocity, float lifeTime) {
        super(parent);
        this.d = d;
        c = color;
        this.frequency = frequency;
        this.velocity = velocity;
        pos = offset;
        this.offset = offset;
        life = Maths.clamp(lifeTime, 0, 10);
        baseLife = life;
    }
    private AlphaComposite makeTransparent(float alpha){
        int type = AlphaComposite.SRC_OVER;
        return AlphaComposite.getInstance(type, alpha);
    }

    @Override
    void tic(int[] inputs) {
        if(!enabled || removeLock) return;
        if(lasttime + frequency < System.currentTimeMillis()) {
            pos = parent.pos.add(offset);
            particles.add(new Particle(this, d, c, pos, velocity, life));
            lasttime = System.currentTimeMillis();
        }
        for (var part : particles) {
            part.tic();
        }
        if(removeQ.size() != 0)
            remove();
    }

    @Override
    void render(Graphics g) {
        if(removeLock) return;
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
}
