package com.github.christopheml.fastblocks.core;

import java.util.Random;

/**
 * Main game logic.
 */
public class Game {

    private Piece currentPiece;

    private final Shape[][] board = new Shape[12][22];

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
        return point.x < 0 || point.x > 11 || board[point.x][point.y] != null;
    }

    private boolean collidesVerticalBottom(Point point) {
        return point.y > 21 || board[point.x][point.y] != null;
    }

    private boolean collidesVerticalTop(Point point) {
        return point.y < 0 || board[point.x][point.y] != null;
    }

    public void attemptRotateRight() {
        currentPiece.rotateRight();
    }

    public void attemptMoveDown() {

        if (currentPiece.blocksPositions().stream()
                .map(Point::down)
                .noneMatch(this::collidesVerticalBottom)) {
            currentPiece.moveDown();
        } else {
            lockPiece();
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

    private void lockPiece() {
        currentPiece.blocksPositions().forEach(p -> board[p.x][p.y] = currentPiece.shape());
    }

    public Piece currentPiece() {
        return currentPiece;
    }

    public void start() {
        status = Status.STARTED;
        spawnPiece();
    }

    public Shape[][] board() {
        return board;
    }

    public Status status() {
        return status;
    }

    public enum Status {
        NOT_STARTED, STARTED, LOST;
    }

}
