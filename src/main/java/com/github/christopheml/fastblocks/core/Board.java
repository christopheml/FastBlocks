package com.github.christopheml.fastblocks.core;

/**
 * Represents the content of the board.
 */
public class Board {

    private final Shape[][] board = new Shape[12][22];


    public void lock(Piece piece) {
        piece.blocksPositions().forEach(p -> board[p.x][p.y] = piece.shape());
    }

    public boolean isOccupied(Point p) {
        if (p.x < 0 || p.x > 11 || p.y < 0 || p.y > 21) {
            return false;
        }
        return board[p.x][p.y] != null;
    }

    public String getColor(Point p) {
        return board[p.x][p.y].color;
    }

}
