package com.github.christopheml.fastblocks.core;

/**
 * Represents the content of the board.
 */
public class Board {

    public static final int COLUMNS = 12;
    public static final int LINES = 22;

    private final Block[][] board = new Block[COLUMNS][LINES];


    public void lock(Piece piece) {
        piece.blocksPositions().forEach(p -> board[p.x][p.y] = new DeadBlock(p, piece.shape().color));
    }

    public boolean isOccupied(Point p) {
        if (collidesLeftSide(p) || collidesRightSide(p) || p.y < 0 || p.y >= LINES) {
            return false;
        }
        return board[p.x][p.y] != null;
    }

    public String getColor(Point p) {
        return board[p.x][p.y].color();
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


}
