package com.github.christopheml.fastblocks.ui.animations;

import com.github.christopheml.fastblocks.core.Board;
import com.github.christopheml.fastblocks.core.Point;
import com.github.christopheml.fastblocks.core.Shape;

public class BoardFiller {

    public static void fillBlocks(Board board, int blockCount) {
        int filledBlocks = 0;
        for (int y = Board.LINES - 1; y >= 0; --y) {
            for (int x = 0; x < Board.COLUMNS; ++x) {
                Point position = Point.p(x, y);
                if (!board.isOccupied(position)) {
                    board.fillBlock(position, Shape.random().color);
                    filledBlocks++;
                }
            }
            if (filledBlocks >= blockCount) {
                return;
            }
        }
    }

}
