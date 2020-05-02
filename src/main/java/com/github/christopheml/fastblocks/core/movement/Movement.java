package com.github.christopheml.fastblocks.core.movement;

import com.github.christopheml.fastblocks.core.Board;
import com.github.christopheml.fastblocks.core.Piece;
import com.github.christopheml.fastblocks.core.Point;

import java.util.List;

/**
 * Piece move logic across a board, checking constraints.
 */
public class Movement {

    private final Board board;

    public Movement(Board board) {
        this.board = board;
    }

    public Outcome down(Piece piece) {
        if (piece.blocksPositions().stream()
                .map(Point::down)
                .noneMatch(board::collidesVerticalBottom)) {
            piece.moveDown();
            return Outcome.MOVED;
        }
        return Outcome.LOCKED;
    }

    public void left(Piece piece) {
        if (piece.blocksPositions().stream()
                .map(Point::left)
                .noneMatch(board::collidesLateral)) {
            piece.moveLeft();
        }
    }

    public void right(Piece piece) {
        if (piece.blocksPositions().stream()
                .map(Point::right)
                .noneMatch(board::collidesLateral)) {
            piece.moveRight();
        }
    }

    public Outcome drop(Piece piece) {
        var outcome = Outcome.MOVED;
        do {
            outcome = down(piece);
        } while (outcome != Outcome.LOCKED);
        return Outcome.LOCKED;
    }

    public Outcome rotateRight(Piece piece) {
        var rotated = piece.tryRotation();
        if (rotated.stream().anyMatch(board::isOccupied)) {
            // Collision with pieces, no rotation
            return Outcome.NOT_MOVED;
        }
        if (rotated.stream().anyMatch(board::collidesLeftSide)) {
            return tryLeftWallKick(piece, rotated);
        } else if (rotated.stream().anyMatch(board::collidesRightSide)) {
            return tryRightWallKick(piece, rotated);
        }
        piece.rotateRight();
        return Outcome.MOVED;
    }

    private Outcome tryRightWallKick(Piece piece, List<Point> rotated) {
        if (rotated.stream().map(Point::left).noneMatch(board::collidesLateral)) {
            piece.moveLeft();
            piece.rotateRight();
            return Outcome.MOVED;
        }
        return Outcome.NOT_MOVED;
    }

    private Outcome tryLeftWallKick(Piece piece, List<Point> rotated) {
        if (rotated.stream().map(Point::right).noneMatch(board::collidesLateral)) {
            piece.moveRight();
            piece.rotateRight();
            return Outcome.MOVED;
        }
        return Outcome.NOT_MOVED;
    }


    public enum Outcome {
        /**
         * Piece was moved as requested
         */
        MOVED,

        /**
         * Piece wasn't moved but is still free (e.g. hitting a wall)
         */
        NOT_MOVED,

        /**
         * Piece couldn't move and should be locked (i.e. hit something at the bottom)
         */
        LOCKED;
    }

}
