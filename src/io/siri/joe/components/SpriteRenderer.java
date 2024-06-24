/*
 * Copyright (c) 2022-2024. Kira "Siri" K.
 * Distributed subject to the terms of the Mozilla Public License (MPL) v 2.0
 * See the LICENSE File for more Details
 * If a copy of the MPL was not distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package io.siri.joe.components;

import io.siri.joe.Component;
import io.siri.joe.GameObject;
import io.siri.joe.SpriteAsset;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * A component that draws a {@link SpriteAsset} to the screen.
 *
 * @see io.siri.joe.Component
 * @see SpriteAsset
 * @author Siri
 */
public class SpriteRenderer extends Component {
    BufferedImage sprite;
    Dimension scale;

    /**
     * Instantiates a new {@link SpriteRenderer}.
     *
     * @param parent The parent {@linkplain GameObject}. In 99% of cases, use the keyword `this`.
     * @param spriteAsset The {@link SpriteAsset} to be displayed.
     * @throws IOException When the asset cannot be read.
     */
    public SpriteRenderer(GameObject parent, SpriteAsset spriteAsset) throws IOException {
        super(parent);
        sprite = ImageIO.read(spriteAsset);
        this.scale = new Dimension(sprite.getWidth(), sprite.getHeight());
    }

    /**
     * Instantiates a new {@link SpriteRenderer}.
     *
     * @param parent The parent {@linkplain GameObject}. In 99% of cases, use the keyword `this`.
     * @param spriteAsset The {@link SpriteAsset} to be displayed.
     * @param scale An override for image scale. Useful for large images.
     * @throws IOException When the asset cannot be read.
     */
    public SpriteRenderer(GameObject parent, SpriteAsset spriteAsset, Dimension scale) throws IOException {
        super(parent);
        sprite = ImageIO.read(spriteAsset);
        this.scale = scale;
    }
    @Override
    public void render(Graphics graphics) {
        var posOpt = parent.getPos();
        if(!enabled || posOpt.isEmpty()) return;
        var pos = posOpt.get();
        graphics.drawImage(sprite, pos.x, pos.y, scale.width, scale.height, null);
    }
}
