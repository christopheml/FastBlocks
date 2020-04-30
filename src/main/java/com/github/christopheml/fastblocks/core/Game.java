package com.github.christopheml.fastblocks.core;

import java.util.List;
import java.util.Random;

/**
 * Main game logic.
 */
public class Game {

    private Piece currentPiece;

    private final Board board = new Board();

    private Status status = Status.NOT_STARTED;

    private void spawnPiece() {
        currentPiece = new Piece(Shape.random(), new Random().nextInt(5) + 4, 0);
    }

    public void attemptMoveLeft() {
        if (currentPiece.blocksPositions().stream()
                .map(Point::left)
                .noneMatch(board::collidesLateral)) {
            currentPiece.moveLeft();
        }
    }

    public void attemptMoveRight() {
        if (currentPiece.blocksPositions().stream()
                .map(Point::right)
                .noneMatch(board::collidesLateral)) {
            currentPiece.moveRight();
        }
    }

    public void attemptRotateRight() {
        List<Point> rotated = currentPiece.tryRotation();
        if (rotated.stream().anyMatch(board::isOccupied)) {
            // Collision with pieces, no rotation
            return;
        }
        if (rotated.stream().anyMatch(board::collidesLeftSide)) {
            // Attempt wallkick on left wall
            if (rotated.stream().map(Point::right).noneMatch(board::collidesLateral)) {
                currentPiece.moveRight();
            } else {
                // Wallkick not possible, no rotation
                return;
            }
        } else if (rotated.stream().anyMatch(board::collidesRightSide)) {
            // Attempt wallkick on right wall
            if (rotated.stream().map(Point::left).noneMatch(board::collidesLateral)) {
                currentPiece.moveLeft();
            } else {
                // Wallkick not possible, no rotation
                return;
            }
        }

        currentPiece.rotateRight();
    }

    public void attemptMoveDown() {
        if (currentPiece.blocksPositions().stream()
                .map(Point::down)
                .noneMatch(board::collidesVerticalBottom)) {
            currentPiece.moveDown();
        } else {
            board.lock(currentPiece);
            spawnPiece();

            // if spawned piece hits anything upon spawning, game over
            if (currentPiece.blocksPositions().stream().anyMatch(board::collidesVerticalTop)) {
                lose();
            }
        }
    }

    private void lose() {
        status = Status.LOST;
    }

    public Piece currentPiece() {
        return currentPiece;
    }

    public void start() {
        status = Status.STARTED;
        spawnPiece();
    }

    public Board board() {
        return board;
    }

    public Status status() {
        return status;
    }

    public void attemptDrop() {
        while (currentPiece.blocksPositions().stream()
                .map(Point::down)
                .noneMatch(board::collidesVerticalBottom)) {
            currentPiece.moveDown();
        }
        attemptMoveDown();
    }

    public enum Status {
        NOT_STARTED, STARTED, LOST;
    }

}
