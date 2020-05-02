package com.github.christopheml.fastblocks.ui.animations;

import com.github.christopheml.fastblocks.core.Board;
import com.github.christopheml.fastblocks.core.Point;
import com.github.christopheml.fastblocks.core.Shape;

/**
 * Visual effect that fills the board.
 *
 * A minimum of one line or a fixed number of empty blocks are filled in an iteration.
 */
public class BoardFiller {

    public static void fillBlocks(Board board, int blockCount) {
        var filledBlocks = 0;
        for (var y = Board.LINES - 1; y >= 0; --y) {
            for (var x = 0; x < Board.COLUMNS; ++x) {
                var position = Point.p(x, y);
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
