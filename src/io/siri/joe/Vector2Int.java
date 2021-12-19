package io.siri.joe;

public class Vector2Int {
    // Members
    public int x;
    public int y;

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
    public Vector2 toFloat(){
        return new Vector2(x, y);
    }

    // Compare two vectors
    public boolean equals(Vector2Int other) {
        return (this.x == other.x && this.y == other.y);
    }
}
