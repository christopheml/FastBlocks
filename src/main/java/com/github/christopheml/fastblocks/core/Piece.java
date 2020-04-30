package com.github.christopheml.fastblocks.core;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a single moving piece.
 */
public class Piece {

    private final Shape shape;

    private int x;

    private int y;

    private int orientation;

    public Piece(Shape shape, int x, int y) {
        this.shape = shape;
        this.x = x;
        this.y = y;
        orientation = 0;
    }

    public void moveLeft() {
        this.x = x - 1;
    }

    public void moveRight() {
        this.x = x + 1;
    }

    public void moveDown() {
        this.y = y + 1;
    }

    public void rotateRight() {
        orientation = (orientation + 1) % shape.rotations().size();
    }

    public void drop() {
        // TODO: implement this
    }

    public Shape shape() {
        return shape;
    }

    public Point position() {
        return Point.p(x, y);
    }

    public List<Point> blocksPositions() {
        return Arrays.stream(shape.rotations().get(orientation)).map(p -> p.add(position())).collect(Collectors.toList());
    }

}
