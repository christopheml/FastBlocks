package com.github.christopheml.fastblocks.core;

import java.util.List;
import java.util.Random;

import static com.github.christopheml.fastblocks.core.Point.p;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

/**
 * Represents the piece shapes of the game and their possible configurations.
 * <p>
 * Colors are in web representation (hexadecimal for RGB and alpha channel).
 */
public enum Shape {

    I("#47FFEA80", asList(
            new Point[]{p(0, 0), p(0, 1), p(0, 2), p(0, 3)},
            new Point[]{p(0, 0), p(1, 0), p(2, 0), p(3, 0)}
    )),
    O("#B4373280", singletonList(
            new Point[]{p(0, 0), p(0, 1), p(1, 0), p(1, 1)}
    )),
    T("#469D3480", asList(
            new Point[]{p(1, 0), p(0, 1), p(1, 1), p(2, 1)},
            new Point[]{p(0, 0), p(0, 1), p(1, 1), p(0, 2)},
            new Point[]{p(0, 0), p(0, 1), p(0, 2), p(1, 1)},
            new Point[]{p(1, 0), p(0, 1), p(1, 1), p(1, 2)}
    )),
    S("5F2BCF80", asList(
            new Point[]{p(1, 0), p(2, 0), p(0, 1), p(1, 1)},
            new Point[]{p(0, 0), p(0, 1), p(1, 1), p(1, 2)}
    )),
    Z("#FFD60080", asList(
            new Point[]{p(0, 0), p(1, 0), p(1, 1), p(2, 1)},
            new Point[]{p(1, 0), p(0, 1), p(1, 1), p(0, 2)}
    )),
    J("#30639180", asList(
            new Point[]{p(0, 0), p(0, 1), p(1, 1), p(2, 1)},
            new Point[]{p(0, 0), p(1, 0), p(0, 1), p(0, 2)},
            new Point[]{p(0, 0), p(1, 0), p(2, 0), p(2, 1)},
            new Point[]{p(1, 0), p(1, 1), p(0, 2), p(1, 2)}
    )),
    L("#FA7D0080", asList(
            new Point[]{p(2, 0), p(0, 1), p(1, 1), p(2, 1)},
            new Point[]{p(0, 0), p(0, 1), p(0, 2), p(1, 2)},
            new Point[]{p(0, 0), p(1, 0), p(2, 0), p(0, 1)},
            new Point[]{p(0, 0), p(1, 0), p(1, 1), p(1, 2)}
    ));

    public final String color;

    private final List<Point[]> rotations;

    private static final Random rng = new Random();

    Shape(String color, List<Point[]> rotations) {
        this.color = color;
        this.rotations = rotations;
    }

    public static Shape random() {
        return values()[rng.nextInt(values().length)];
    }

    public List<Point[]> rotations() {
        return rotations;
    }

}
