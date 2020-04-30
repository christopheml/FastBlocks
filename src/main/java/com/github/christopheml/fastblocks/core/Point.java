package com.github.christopheml.fastblocks.core;

/**
 * Cartesian coordinate.
 */
public class Point {

    public final int x;
    public final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point left() {
        return new Point(x - 1, y);
    }

    public Point right() {
        return new Point(x + 1, y);
    }

    public Point down() {
        return new Point(x, y + 1);
    }

    public Point add(Point other) {
        return new Point(x + other.x, y + other.y);
    }

    public static Point at(int x, int y) {
        return new Point(x, y);
    }

    @Override
    public String toString() {
        return "(" + x +
                ", " + y +
                ")";
    }
}
