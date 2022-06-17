package io.siri.joe.components;

import io.siri.joe.Component;
import io.siri.joe.GameObject;
import io.siri.joe.Vector2Int;

import java.awt.*;

public class TextRenderer extends Component {
    public Font font;
    public String contents;
    protected boolean hasPos;
    public Vector2Int pos;
    public Color color;
    public TextRenderer(GameObject parent, Font font, Color c, String contents) {
        super(parent);
        this.font = font;
        this.contents = contents;
        hasPos = false;
        color = c;
    }
    public TextRenderer(GameObject parent, Font font, Color c ,String contents, Vector2Int pos) {
        super(parent);
        this.font = font;
        this.contents = contents;
        hasPos = true;
        this.pos = pos;
        color = c;
    }
    //draws text at top left
    @Override
    public void render(Graphics g){
        var g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.setFont(font);
        g2d.drawString(contents, (hasPos ? pos.x : parent.getPos().x), (hasPos ? pos.y : parent.getPos().y) + g2d.getFontMetrics().getAscent());
    }
}
