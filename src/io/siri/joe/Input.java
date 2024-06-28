/*
 * Copyright (c) 2024. Kira "Siri" K.
 * Distributed subject to the terms of the Mozilla Public License (MPL) v 2.0
 * See the LICENSE File for more Details
 * If a copy of the MPL was not distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package io.siri.joe;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Input {
    /**
     * @// TODO: 2024-06-28 this doesnt need to be public, this is just a stop gap until i stabilise the new input features
     */
    public int[] currInputs = {};
    int[] prevInputs = {};

    protected void tic() {
        prevInputs = currInputs;
    }
    protected void pushInput(int input) {
        if (Arrays.stream(currInputs).noneMatch(el -> el == input)) currInputs = IntStream.concat(Arrays.stream(currInputs), Arrays.stream(new int[]{input})).toArray();
    }

    protected void dropInput(int input) {
        currInputs = Arrays.stream(currInputs).filter(el -> el != input).toArray();
    }

    public boolean keyHeld(int keyCode) {
        return Arrays.stream(currInputs).anyMatch(el -> el == keyCode);
    }
    public boolean keyDown(int keyCode) {
        return Arrays.stream(prevInputs).noneMatch(el -> el == keyCode) && Arrays.stream(currInputs).anyMatch(el -> el == keyCode);
    }
    public boolean keyUp(int keyCode) {
        return Arrays.stream(currInputs).noneMatch(el -> el == keyCode) && Arrays.stream(prevInputs).anyMatch(el -> el == keyCode);
    }
}
