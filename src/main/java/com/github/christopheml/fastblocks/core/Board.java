package com.github.christopheml.fastblocks.core;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Represents the content of the board.
 */
public class Board {

    public static final int COLUMNS = 12;
    public static final int LINES = 22;

    private final Block[][] board = new Block[LINES][COLUMNS];

    public void lock(Piece piece) {
        piece.blocksPositions().forEach(p -> board[p.y][p.x] = new DeadBlock(p, piece.shape().color));
    }

    public boolean isOccupied(Point p) {
        if (collidesLeftSide(p) || collidesRightSide(p) || p.y < 0 || p.y >= LINES) {
            return false;
        }
        return board[p.y][p.x] != null;
    }

    boolean collidesVerticalTop(Point point) {
        return point.y < 0 || isOccupied(point);
    }

    boolean collidesVerticalBottom(Point point) {
        return point.y >= LINES || isOccupied(point);
    }

    boolean collidesLateral(Point point) {
        return collidesLeftSide(point) || collidesRightSide(point) || isOccupied(point);
    }

    boolean collidesRightSide(Point point) {
        return point.x >= COLUMNS;
    }

    boolean collidesLeftSide(Point point) {
        return point.x < 0;
    }

    public List<Block> getBlocks() {
        return Arrays.stream(board).flatMap(Arrays::stream).filter(Objects::nonNull).collect(Collectors.toList());
    }

}
