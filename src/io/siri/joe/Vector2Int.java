/*
 * Copyright (c) 2022. Kira "Siri" K.
 * Distributed subject to the terms of the Mozilla Public License (MPL) v 2.0
 * See the LICENSE File for more Details
 * If a copy of the MPL was not distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package io.siri.joe;
/**
 *  A representation of the Integer x and y within 2D space.
 *  @apiNote Designed for use with java.awt.*, thusly, y is measured top-down, and not bottom-up.
 *  @see Vector2 Floating-Point Vector2
 */
public class Vector2Int {
    // Members
    public int x;
    public int y;

    public static final Vector2Int DOWN = new Vector2Int(0, 1), UP = new Vector2Int(0, -1), LEFT = new Vector2Int(-1, 0), RIGHT = new Vector2Int(1, 0), ZERO = new Vector2Int(0, 0);

    // Constructors
    public Vector2Int() {
        this.x = 0;
        this.y = 0;
    }

    public Vector2Int(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //convert to standard vector2
    public Vector2 toFloat() {
        return new Vector2(x, y);
    }

    public static Vector2 toFloat(Vector2Int v) {
        return new Vector2(v.x, v.y);
    }

    // Compare two vectors
    public boolean equals(Vector2Int other) {
        return (this.x == other.x && this.y == other.y);
    }

    public boolean greater(Vector2Int other) {
        return (this.x > other.x && this.y > other.y);
    }

    public boolean less(Vector2Int other) {
        return (this.x < other.x && this.y < other.y);
    }

    public Vector2Int add(Vector2Int other) {
        return new Vector2Int(this.x + other.x, this.y + other.y);
    }
    public Vector2Int add(Vector2 other) {
        return new Vector2Int(this.x + Math.round(other.x), this.y + Math.round(other.y));
    }

    public Vector2Int add(float other) {
        return new Vector2(this.x + other, this.y + other).toInt();
    }

    public Vector2Int add(int other) {
        return new Vector2Int(this.x + other, this.y + other);
    }


    public Vector2Int subtract(Vector2Int other) {
        return new Vector2Int(this.x - other.x, this.y - other.y);
    }
    public Vector2Int subtract(Vector2 other) {
        return new Vector2Int(this.x - Math.round(other.x), this.y - Math.round(other.y));
    }

    public Vector2Int subtract(float other) {
        return new Vector2(this.x - other, this.y - other).toInt();
    }

    public Vector2Int subtract(int other) {
        return new Vector2Int(this.x - other, this.y - other);
    }


    public Vector2Int multiply(Vector2Int other) {
        return new Vector2Int(this.x * other.x, this.y * other.y);
    }
    public Vector2Int multiply(Vector2 other) {
        return new Vector2Int(this.x * Math.round(other.x), this.y * Math.round(other.y));
    }

    public Vector2Int multiply(float other) {
        return new Vector2(this.x * other, this.y * other).toInt();
    }

    public Vector2Int multiply(int other) {
        return new Vector2Int(this.x * other, this.y * other);
    }


    public Vector2Int divide(Vector2Int other) {
        return new Vector2Int(this.x / other.x, this.y / other.y);
    }
    public Vector2Int divide(Vector2 other) {
        return new Vector2Int(this.x / Math.round(other.x), this.y / Math.round(other.y));
    }

    public Vector2Int divide(float other) {
        return new Vector2(this.x / other, this.y / other).toInt();
    }

    public Vector2Int divide(int other) {
        return new Vector2Int(this.x / other, this.y / other);
    }



    public static Vector2Int add(Vector2Int v, Vector2Int other) {
        return new Vector2Int(v.x + other.x, v.y + other.y);
    }

    public static Vector2Int add(Vector2Int v, Vector2 other) {
        return new Vector2Int(v.x + Math.round(other.x), v.y + Math.round(other.y));
    }

    public static Vector2Int add(Vector2Int v, float other) {
        return new Vector2(v.x + other, v.y + other).toInt();
    }

    public static Vector2Int add(Vector2Int v, int other) {
        return new Vector2Int(v.x + other, v.y + other);
    }


    public static Vector2Int subtract(Vector2Int v, Vector2Int other) {
        return new Vector2Int(v.x - other.x, v.y - other.y);
    }

    public static Vector2Int subtract(Vector2Int v, Vector2 other) {
        return new Vector2Int(v.x - Math.round(other.x), v.y - Math.round(other.y));
    }

    public static Vector2Int subtract(Vector2Int v, float other) {
        return new Vector2(v.x - other, v.y - other).toInt();
    }

    public static Vector2Int subtract(Vector2Int v, int other) {
        return new Vector2Int(v.x - other, v.y - other);
    }


    public static Vector2Int multiply(Vector2Int v, Vector2Int other) {
        return new Vector2Int(v.x * other.x, v.y * other.y);
    }
    public static Vector2Int multiply(Vector2Int v, Vector2 other) {
        return new Vector2Int(v.x * Math.round(other.x), v.y * Math.round(other.y));
    }

    public static Vector2Int multiply(Vector2Int v, float other) {
        return new Vector2(v.x * other, v.y * other).toInt();
    }

    public static Vector2Int multiply(Vector2Int v, int other) {
        return new Vector2Int(v.x * other, v.y * other);
    }


    public static Vector2Int divide(Vector2Int v, Vector2Int other) {
        return new Vector2Int(v.x / other.x, v.y / other.y);
    }
    public static Vector2Int divide(Vector2Int v, Vector2 other) {
        return new Vector2Int(v.x / Math.round(other.x), v.y / Math.round(other.y));
    }
    public static Vector2Int divide(Vector2Int v, float other) {
        return new Vector2(v.x / other, v.y / other).toInt();
    }

    public static Vector2Int divide(Vector2Int v, int other) {
        return new Vector2Int(v.x / other, v.y / other);
    }


    public Vector2Int clamp(Vector2Int min, Vector2Int max) {
        return new Vector2Int(Maths.clamp(this.x, min.x, max.x), Maths.clamp(this.x, min.x, max.x));
    }

    public static Vector2Int clamp(Vector2Int v, Vector2Int min, Vector2Int max) {
        return new Vector2Int(Maths.clamp(v.x, min.x, max.x), Maths.clamp(v.x, min.x, max.x));
    }
}
