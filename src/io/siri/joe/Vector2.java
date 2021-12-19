package io.siri.joe;

public class Vector2 {
    // Members
    public float x;
    public float y;

    // Constructors
    public Vector2() {
        this.x = 0.0f;
        this.y = 0.0f;
    }

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }
    //convert to vector2int
    public Vector2Int toInt(){
        return new Vector2Int(Math.round(x), Math.round(y));
    }
    // Compare two vectors
    public boolean equals(Vector2 other) {
        return (this.x == other.x && this.y == other.y);
    }
}
