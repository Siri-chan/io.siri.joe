package io.siri.joe.components;

import io.siri.joe.Component;
import io.siri.joe.GameObject;
import io.siri.joe.InvalidAssetFileException;
import io.siri.joe.SpriteAsset;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Draws a {@link SpriteAsset} to the screen.
 * @author Siri
 */
public class SpriteRenderer extends Component {
    BufferedImage sprite;
    Dimension scale;
    public SpriteRenderer(GameObject parent, SpriteAsset spriteAsset) throws IOException, InvalidAssetFileException {
        super(parent);
        if (!spriteAsset.checkValidity()) throw new InvalidAssetFileException();
        sprite = ImageIO.read(spriteAsset);
        this.scale = new Dimension(sprite.getWidth(), sprite.getHeight());
    }
    public SpriteRenderer(GameObject parent, SpriteAsset spriteAsset, Dimension scale) throws IOException {
        super(parent);
        sprite = ImageIO.read(spriteAsset);
        this.scale = scale;
    }
    public void render(Graphics graphics) {
        graphics.drawImage(sprite, parent.getPos().x, parent.getPos().y, scale.width, scale.height, null);
    }
}
