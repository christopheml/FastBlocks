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

    public Piece(Shape shape, int x, int y) {
        this.shape = shape;
        this.x = x;
        this.y = y;
    }

    public void moveLeft() {
        this.x = Math.max(0, x - 1);
    }

    public void moveRight() {
        this.x = Math.min(12, x + 1);
    }

    public void moveDown() {
        this.y = y + 1;
    }

    public void rotateLeft() {
        // TODO: implement this
    }

    public void rotateRight() {
        // TODO: implement this
    }

    public void drop() {
        // TODO: implement this
    }

    public Shape shape() {
        return shape;
    }

    public Point position() {
        return Point.at(x, y);
    }

    public List<Point> blocksPositions() {
        return Arrays.stream(shape.blocks).map(p -> p.add(position())).collect(Collectors.toList());
    }

}
