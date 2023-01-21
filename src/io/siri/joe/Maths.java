/*
 * Copyright (c) 2022. Kira "Siri" K.
 * Distributed subject to the terms of the Mozilla Public License (MPL) v 2.0
 * See the LICENSE File for more Details
 * If a copy of the MPL was not distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package io.siri.joe;

/**
 * A helper class for various math functions that involve Java's primitive numbers.
 * For Operations on JOE's datatypes, like {@linkplain Vector2}, see the static functions of those classes.
 * @implNote Doesn't implement anything that base Java already implements.
 *
 * @author Siri.
 */
public class Maths {
    /**
     * Clamps an integer between two values.
     *
     * @param v   The input integer.
     * @param min The minimum value the integer can be.
     * @param max The maximum value the integer can be.
     * @return The input integer clamped between the minimum and maximum values.
     */
    public static int clamp(int v, int min, int max) {
        return (v > max) ? max : Math.max(v, min);
    }

    /**
     * Clamps a long integer between two values.
     *
     * @param v   The input long.
     * @param min The minimum value the long can be.
     * @param max The maximum value the long can be.
     * @return The input long integer clamped between the minimum and maximum values.
     */
    public static long clamp(long v, long min, long max) {
        return (v > max) ? max : Math.max(v, min);
    }

    /**
     * Clamps a floating-point number between two values.
     *
     * @param v   The input float.
     * @param min The minimum value the float can be.
     * @param max The maximum value the float can be.
     * @return The float clamped between the minimum and maximum values.
     */
    public static float clamp(float v, float min, float max) {
        return (v > max) ? max : Math.max(v, min);
    }

    /**
     * Base 2 Logarithm
     *
     * @param n the input
     * @return the number that 2 has to be raised to the power of to reach n.
     * @apiNote Due to the way this is implemented, negative numbers will cause undefined behavior.
     */
    public static int log2(int n){
        int i = 0;
        int mask = Integer.MAX_VALUE -1;
        while ((n & mask) != 0){
            n = n >> 1;
            i++;
        }
        return i;
    }

    /**
     * Base 2 Logarithm
     *
     * @param n the input
     * @return the number that 2 has to be raised to the power of to reach n.
     * @apiNote Due to the way this is implemented, negative numbers will cause undefined behavior.
     */
    public static long log2(long n){
        int i = 0;
        int mask = Integer.MAX_VALUE -1;
        while ((n & mask) != 0){
            n = n >> 1;
            i++;
        }
        return i;
    }

    /**
     * Base 2 Logarithm
     *
     * @param n the input
     * @return the number that 2 has to be raised to the power of to reach n.
     * @apiNote Due to the way this is implemented, negative numbers will cause undefined behavior.
     */
    public static float log2(float n) {
        int i = 0;
        int nn = (int)n;
        int mask = Integer.MAX_VALUE -1;
        while ((nn & mask) != 0){
            nn = nn >> 1;
            i++;
        }
        return (float)i;
    }

    /**
     * Base 2 Logarithm
     *
     * @param n the input
     * @return the number that 2 has to be raised to the power of to reach n.
     * @apiNote Due to the way this is implemented, negative numbers will cause undefined behavior.
     */
    public static double log2(double n) {
        int i = 0;
        int nn = (int)n;
        int mask = Integer.MAX_VALUE -1;
        while ((nn & mask) != 0){
            nn = nn >> 1;
            i++;
        }
        return (double)i;
    }
}
