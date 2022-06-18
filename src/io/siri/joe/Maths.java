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
}
