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
        currentPiece = new Piece(Shape.random(), new Random().nextInt(10), 0);
    }

    public void attemptMoveLeft() {
        if (currentPiece.blocksPositions().stream()
                .map(Point::left)
                .noneMatch(this::collidesLateral)) {
            currentPiece.moveLeft();
        }
    }

    public void attemptMoveRight() {
        if (currentPiece.blocksPositions().stream()
                .map(Point::right)
                .noneMatch(this::collidesLateral)) {
            currentPiece.moveRight();
        }
    }

    private boolean collidesLateral(Point point) {
        return point.x < 0 || point.x > 11 || board.isOccupied(point);
    }

    private boolean collidesVerticalBottom(Point point) {
        return point.y > 21 || board.isOccupied(point);
    }

    private boolean collidesVerticalTop(Point point) {
        return point.y < 0 || board.isOccupied(point);
    }

    public void attemptRotateRight() {
        List<Point> rotated = currentPiece.tryRotation();
        if (rotated.stream().anyMatch(board::isOccupied)) {
            // Collision with pieces, no rotation
            return;
        }
        if (rotated.stream().anyMatch(p -> p.x < 0)) {
            // Attempt wallkick on left wall
            if (rotated.stream().map(Point::right).noneMatch(p -> p.x < 0 || board.isOccupied(p))) {
                currentPiece.moveRight();
            } else {
                // Wallkick not possible, no rotation
                return;
            }
        } else if (rotated.stream().anyMatch(p -> p.x > 11)) {
            // Attempt wallkick on right wall
            if (rotated.stream().map(Point::left).noneMatch(p ->  p.x > 11 || board.isOccupied(p))) {
                currentPiece.moveRight();
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
                .noneMatch(this::collidesVerticalBottom)) {
            currentPiece.moveDown();
        } else {
            board.lock(currentPiece);
            spawnPiece();

            // if spawned piece hits anything upon spawning, game over
            if (currentPiece.blocksPositions().stream().anyMatch(this::collidesVerticalTop)) {
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

    public enum Status {
        NOT_STARTED, STARTED, LOST;
    }

}
