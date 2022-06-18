package io.siri.joe.components;

import io.siri.joe.Component;
import io.siri.joe.GameObject;
import io.siri.joe.Vector2Int;

import java.awt.*;

//todo add enum for alignment point of text. Default rn is top-left
/**
 * A Text Rendering Component for GameObjects
 * @see io.siri.joe.Component
 * @author Siri
 */
public class TextRenderer extends Component {
    /**
     * The font of the displayed text.
     */
    public Font font;
    /**
     * The string contents of the displayed text.
     */
    public String contents;
    protected boolean hasPos;
    /**
     * The absolute position of the displayed text.
     */
    public Vector2Int pos;
    /**
     * The color of the displayed text.
     */
    public Color color;

    /**
     * Instantiates a new {@link TextRenderer}.
     *
     * @param parent The parent GameObject. In 99% of cases, use the keyword `this`.
     * @param font The font of the displayed text.
     * @param color The color of the displayed text.
     * @param contents The string contents of the displayed text.
     * @author Siri
     */
    public TextRenderer(GameObject parent, Font font, Color color, String contents) {
        super(parent);
        this.font = font;
        this.contents = contents;
        hasPos = false;
        this.color = color;
    }

    /**
     * Instantiates a new {@link TextRenderer}.
     *
     * @param parent The parent GameObject. In 99% of cases, use the keyword `this`.
     * @param font The font of the displayed text.
     * @param color The color of the displayed text.
     * @param contents The string contents of the displayed text.
     * @param pos The absolute position of the displayed text.
     * @author Siri
     */
    public TextRenderer(GameObject parent, Font font, Color color ,String contents, Vector2Int pos) {
        super(parent);
        this.font = font;
        this.contents = contents;
        hasPos = true;
        this.pos = pos;
        this.color = color;
    }

    @Override
    public void render(Graphics g){
        var g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.setFont(font);
        g2d.drawString(contents, (hasPos ? pos.x : parent.getPos().x), (hasPos ? pos.y : parent.getPos().y) + g2d.getFontMetrics().getAscent());
    }
}
