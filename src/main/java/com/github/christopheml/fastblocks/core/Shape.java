package com.github.christopheml.fastblocks.core;

import java.util.Random;

/**
 * Represents the piece shapes of the game and their possible configurations.
 *
 * Colors are in web representation (hexadecimal for RGB and alpha channel).
 */
public enum Shape {

    I("#47FFEA80", new Point[]{Point.at(0, 0), Point.at(0, 1), Point.at(0, 2), Point.at(0, 3)}),
    O("#B4373280", new Point[]{Point.at(0, 0), Point.at(0, 1), Point.at(1, 0), Point.at(1, 1)}),
    T("#469D3480", new Point[]{Point.at(1, 0), Point.at(0, 1), Point.at(1, 1), Point.at(2, 1)}),
    S("5F2BCF80", new Point[]{Point.at(1, 0), Point.at(2, 0), Point.at(0, 1), Point.at(1, 1)}),
    Z("#FFD60080", new Point[]{Point.at(0, 0), Point.at(1, 0), Point.at(1, 1), Point.at(2, 1)}),
    J("#30639180", new Point[]{Point.at(0, 0), Point.at(0, 1), Point.at(1, 1), Point.at(2, 1)}),
    L("#FA7D0080", new Point[]{Point.at(2, 0), Point.at(0, 1), Point.at(1, 1), Point.at(2, 1)});

    public final String color;
    public final Point[] blocks;

    private static final Random rng = new Random();

    Shape(String color, Point[] blocks) {
        this.color = color;
        this.blocks = blocks;
    }

    public static Shape random() {
        return values()[rng.nextInt(values().length)];
    }

}
