/*
 * Copyright (c) 2022-2024. Kira "Siri" K.
 * Distributed subject to the terms of the Mozilla Public License (MPL) v 2.0
 * See the LICENSE File for more Details
 * If a copy of the MPL was not distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package io.siri.joe;

/**
 *  A representation of the Floating-Points x and y within 2D space.
 *  @apiNote Designed for use with java.awt.*, thus, y is measured top-down, and not bottom-up.
 *  @see Vector2Int Integer Vector2
 */
//todo add normalise
public class Vector2 {
    // Members
    public float x;
    public float y;

    public static final Vector2 DOWN = new Vector2(0, 1), UP = new Vector2(0, -1), LEFT = new Vector2(-1, 0), RIGHT = new Vector2(1, 0), ZERO = new Vector2(0,0);

    // Constructors
    public Vector2() {
        this.x = 0.0f;
        this.y = 0.0f;
    }

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return The corresponding Vector2Int for this Vector2, using Math.round();
     */
    //convert to vector2int
    public Vector2Int toInt() {
        return new Vector2Int(Math.round(x), Math.round(y));
    }

    /**
     * @param v The relevant Vector2
     * @return The corresponding Vector2Int for the Vector2 v using Math.round();
     */
    public static Vector2Int toInt(Vector2 v) {
        return new Vector2Int(Math.round(v.x), Math.round(v.y));
    }

    // Compare two vectors
    public boolean equals(Vector2 other) {
        return (this.x == other.x && this.y == other.y);
    }

    public boolean greater(Vector2 other) {
        return (this.x > other.x || this.y > other.y);
    }

    public boolean less(Vector2 other) {
        return (this.x < other.x || this.y < other.y);
    }

    public Vector2 add(Vector2 other){
        return new Vector2(this.x + other.x, this.y + other.y);
    }
    public Vector2 add(Vector2Int other) {
        return new Vector2(this.x + other.x, this.y + other.y);
    }
    public Vector2 add(float other){
        return new Vector2(this.x + other, this.y + other);
    }
    public Vector2 add(int other){
        return new Vector2(this.x + other, this.y + other);
    }
    public Vector2 subtract(Vector2 other){
        return new Vector2(this.x - other.x, this.y - other.y);
    }
    public Vector2 subtract(Vector2Int other) {
        return new Vector2(this.x - other.x, this.y - other.y);
    }
    public Vector2 subtract(float other){
        return new Vector2(this.x - other, this.y - other);
    }
    public Vector2 subtract(int other){
        return new Vector2(this.x - other, this.y - other);
    }
    public Vector2 multiply(Vector2 other){
        return new Vector2(this.x * other.x, this.y * other.y);
    }
    public Vector2 multiply(Vector2Int other) {
        return new Vector2(this.x * other.x, this.y * other.y);
    }
    public Vector2 multiply(float other){
        return new Vector2(this.x * other, this.y * other);
    }
    public Vector2 multiply(int other){
        return new Vector2(this.x * other, this.y * other);
    }
    public Vector2 divide(Vector2 other){
        return new Vector2(this.x / other.x, this.y / other.y);
    }
    public Vector2 divide(Vector2Int other) {
        return new Vector2(this.x / other.x, this.y / other.y);
    }
    public Vector2 divide(float other){
        return new Vector2(this.x / other, this.y / other);
    }
    public Vector2 divide(int other){
        return new Vector2(this.x / other, this.y / other);
    }



    public static Vector2 add(Vector2 v, Vector2 other) {
        return new Vector2(v.x + other.x, v.y + other.y);
    }
    public static Vector2 add(Vector2 v, Vector2Int other) {
        return new Vector2(v.x + other.x, v.y + other.y);
    }
    public static Vector2 add(Vector2 v, float other) {
        return new Vector2(v.x + other, v.y + other);
    }

    public static Vector2 add(Vector2 v, int other) {
        return new Vector2(v.x + other, v.y + other);
    }


    public static Vector2 subtract(Vector2 v, Vector2 other) {
        return new Vector2(v.x - other.x, v.y - other.y);
    }
    public static Vector2 subtract(Vector2 v, Vector2Int other) {
        return new Vector2(v.x - other.x, v.y - other.y);
    }
    public static Vector2 subtract(Vector2 v, float other) {
        return new Vector2(v.x - other, v.y - other);
    }

    public static Vector2 subtract(Vector2 v, int other) {
        return new Vector2(v.x - other, v.y - other);
    }


    public static Vector2 multiply(Vector2 v, Vector2 other) {
        return new Vector2(v.x * other.x, v.y * other.y);
    }
    public static Vector2 multiply(Vector2 v, Vector2Int other) {
        return new Vector2(v.x * other.x, v.y * other.y);
    }
    public static Vector2 multiply(Vector2 v, float other) {
        return new Vector2(v.x * other, v.y * other);
    }

    public static Vector2 multiply(Vector2 v, int other) {
        return new Vector2(v.x * other, v.y * other);
    }


    public static Vector2 divide(Vector2 v, Vector2 other) {
        return new Vector2(v.x / other.x, v.y / other.y);
    }
    public static Vector2 divide(Vector2 v, Vector2Int other) {
        return new Vector2(v.x / other.x, v.y / other.y);
    }
    public static Vector2 divide(Vector2 v, float other) {
        return new Vector2(v.x / other, v.y / other);
    }

    public static Vector2 divide(Vector2 v, int other) {
        return new Vector2(v.x / other, v.y / other);
    }
    /**
     * @param min The lowest value (inclusive) that this Vector2 can equal.
     * @param max The lowest value (inclusive) that this Vector2 can equal.
     * @return This Vector2, within the bounds (min, max)
     */
    public Vector2 clamp(Vector2 min, Vector2 max) {
        return (this.greater(max) ? max : (this.less(min) ? min : this));
    }
    /**
     * @param v The Vector2 to apply operations to.
     * @param min The lowest value (inclusive) that the v can equal.
     * @param max The lowest value (inclusive) that the v can equal.
     * @return The Vector2 v, within the bounds (min, max)
     */
    public static Vector2 clamp(Vector2 v, Vector2 min, Vector2 max) {
        return new Vector2(Maths.clamp(v.x, min.x, max.x), Maths.clamp(v.x, min.x, max.x));
    }
}
