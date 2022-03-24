package io.siri.joe;

public class Maths {
    public static int clamp(int v, int min, int max) {
        return (v > max) ? max : Math.max(v, min);
    }
    public static float clamp(float v, float min, float max) {
        return (v > max) ? max : Math.max(v, min);
    }
}
