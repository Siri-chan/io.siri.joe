/*
 * Copyright (c) 2022. Kira "Siri" K.
 * Distributed subject to the terms of the Mozilla Public License (MPL) v 2.0
 * See the LICENSE File for more Details
 * If a copy of the MPL was not distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package io.siri.joetest;

import io.siri.joe.GameObject;
import io.siri.joe.Input;
import io.siri.joe.Vector2Int;
import io.siri.joe.components.TextRenderer;
import io.siri.joe.components.Transform;

import java.awt.*;

import static java.awt.event.KeyEvent.*;

public class TestText extends GameObject {

    TextRenderer t;
    Transform tr;
    final String[] texts = {
            /* WARNING: English and Japanese are the only languages I am literate in myself.
            *   Also, character sets with broken afterwards do not work in the given default fonts:
            * ss: Font.SANS_SERIF
            * s: Font.SERIF
            * ms: Font.MONOSPACED
            * d: Font.DIALOG
            * di: Font.DIALOG_INPUT
            */
            "test",
            "this test is far longer than the initial test Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            "Alternate_Characters::Japanese: これは書くことのテストです。",
            "Alternate_Characters::Chinese_Traditional: 這是一個寫作測試",
            "Alternate_Characters::Korean: 이것은 글쓰기 테스트입니다", //broken ss s ms d di
            "Alternate_Characters::Hindi: यह एक लेखन परीक्षा है।", //broken ss s ms d di
            "Alternate_Characters::Thai: นี่คือการทดสอบการเขียน", //broken ss s ms d di
            "Alternate_Characters::Arabic: هذا اختبار الكتابة.",
            "Alternate_Characters::Hebrew: זהו מבחן כתיבה.",
            "Alternate_Characters::Icelandic: Þetta er ritpróf.",
            "Alternate_Characters::Russian: Это письменный тест.",
            "Alternate_Characters::Greek: Αυτή είναι μια δοκιμή γραφής.",
            "Alternate_Characters::Germany/Europe: Ää Öö Üü ẞß Åå",
            "Control_Characters::Backspace(\\b): A\bB", //displays as unknown ascii
            "Control_Characters::NewLine(\\n): There should be a line\nbreak here.", //does not linebreak
            "Control_Characters::Tab(\\t): T\tA\tB", //does not tab
            //"Control_Characters::VerticalTab(\\v): V\vT\vA\vB", //\v is disallowed in string literals in Java
            "Control_Characters::CarriageReturn(\\r): AA\rB",//does not CR
            "Control_Characters::Quotes(\"): \"Is this Real?\"",
            "Control_Characters::Octal(\\XXX): A\072\065",
            //"Control_Characters::Hexadecimal(\\xXX): \x4a L", //\x is illegal in string literals also
            "Emoji::Working_1:\uD83D\uDE00\uD83D\uDE03\uD83D\uDE04\uD83D\uDE01\uD83D\uDE06\uD83D\uDE05\uD83D\uDE02" //there are more that work, it looks like very early emoji work fine, i cba testing every emoji
    };
    int i = 0;
    /**
     * Instantiates a new Game object.
     *
     * @param pos   The Position of the Object.
     */
    public TestText(Vector2Int pos) {
        super();
        tr = new Transform(this, pos);
        this.components.add(tr);
        t = new TextRenderer(this, new Font(Font.DIALOG_INPUT, Font.PLAIN, 25), Color.blue, "test");
        setLayer(2);
        this.components.add(t);
    }

    /**
     * Tic. Runs at 60FPS.
     *
     * @param delta The amount of time since last tic()
     * @param inputs Keys being pressed on the frame.
     */
    @Override
    public void tic(double delta, Input inputs) {
        if (inputs.keyDown(VK_C)) {
            setLayer(getLayer() * -1);

        }
        if (inputs.keyDown(VK_E)){
            i++;
            i %= texts.length;
            t.contents = texts[i];
        }
        if (inputs.keyDown(VK_Q)) {
            t.font = new Font(Font.SERIF, Font.PLAIN, 25);
        }
    }
}
