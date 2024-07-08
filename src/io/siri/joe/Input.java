/*
 * Copyright (c) 2024. Kira "Siri" K.
 * Distributed subject to the terms of the Mozilla Public License (MPL) v 2.0
 * See the LICENSE File for more Details
 * If a copy of the MPL was not distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package io.siri.joe;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * The state of user input into the game window.
 * @see java.awt.event.KeyEvent KeyEvent for a list of keyCodes
 * @author Siri
 */
//TODO: Remove this suppressor once I'm confident about making currInputs protected.
@SuppressWarnings("DeprecatedIsStillUsed")
public class Input {
    /**
     * The Current input state.
     *
     * @// TODO : this doesnt need to be public, this is just a stop gap until i stabilise the new input features
     * @deprecated 0.7.0 - Use new input handling wherever possible, unless unexpected complex behaviour is required.
     */
    public int[] currInputs = {};
    int[] prevInputs = {};
    protected void tic() {
        prevInputs = currInputs;
    }

    /**
     * Push input to the input buffer.
     *
     * @param input the input keycode
     * @author Siri
     */
    protected void pushInput(int input) {
        if (Arrays.stream(currInputs).noneMatch(el -> el == input)) currInputs = IntStream.concat(Arrays.stream(currInputs), Arrays.stream(new int[]{input})).toArray();
    }

    /**
     * Drop input from the input buffer.
     *
     * @param input the input keycode
     * @author Siri
     */
    protected void dropInput(int input) {
        currInputs = Arrays.stream(currInputs).filter(el -> el != input).toArray();
    }

    /**
     * Checks if a key on the keyboard is currently being held down.
     *
     * @param keyCode The Relevant Keycode
     * @return The current key's state.
     * @author Siri
     */
    public boolean keyHeld(int keyCode) {
        return Arrays.stream(currInputs).anyMatch(el -> el == keyCode);
    }

    /**
     * Checks if a key on the keyboard has been pressed down this frame.
     *
     * @param keyCode The Relevant Keycode
     * @return The current key's state.
     * @author Siri
     */
    public boolean keyDown(int keyCode) {
        return Arrays.stream(prevInputs).noneMatch(el -> el == keyCode) && Arrays.stream(currInputs).anyMatch(el -> el == keyCode);
    }

    /**
     * Checks if a key on the keyboard has been released this frame.
     *
     * @param keyCode The Relevant Keycode
     * @return The current key's state.
     * @author Siri
     */
    public boolean keyUp(int keyCode) {
        return Arrays.stream(currInputs).noneMatch(el -> el == keyCode) && Arrays.stream(prevInputs).anyMatch(el -> el == keyCode);
    }
}
