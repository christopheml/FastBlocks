package com.github.christopheml.fastblocks.core;

import com.github.christopheml.fastblocks.core.movement.Movement;
import com.github.christopheml.fastblocks.ui.events.GameEvents;
import com.github.christopheml.fastblocks.ui.events.board.LinesClearedEvent;
import com.github.christopheml.fastblocks.ui.events.game.GameStartEvent;
import com.github.christopheml.fastblocks.ui.events.piece.PieceDroppedEvent;
import com.github.christopheml.fastblocks.ui.events.piece.PieceRotatedEvent;

import java.util.List;
import java.util.Random;

/**
 * Main game logic.
 */
public class Game {

    private final GameEvents events;

    private Piece currentPiece;

    private final Board board;

    private final Movement movement;

    private Status status = Status.NOT_STARTED;

    public Game(GameEvents events) {
        this.events = events;
        board = new Board();
        movement = new Movement(board);
    }

    private void spawnPiece() {
        currentPiece = new Piece(Shape.random(), new Random().nextInt(5) + 4, 0);
    }

    public void moveLeft() {
        movement.left(currentPiece);
    }

    public void moveRight() {
        movement.right(currentPiece);
    }

    public void rotateRight() {
        if (movement.rotateRight(currentPiece) == Movement.Outcome.MOVED) {
            events.fireEvent(new PieceRotatedEvent());
        }
    }

    public void moveDown() {
        var outcome = movement.down(currentPiece);
        if (outcome == Movement.Outcome.LOCKED) {
            lockPiece();
        }
    }

    private void lockPiece() {
        board.lock(currentPiece);
        clearLines();
        spawnPiece();

        // if spawned piece hits anything upon spawning, game over
        if (currentPiece.blocksPositions().stream().anyMatch(board::collidesVerticalTop)) {
            lose();
        }
    }

    private void lose() {
        status = Status.LOST;
    }

    public Piece currentPiece() {
        return currentPiece;
    }

    public void start() {
        board.clear();
        status = Status.STARTED;
        spawnPiece();
        events.fireEvent(new GameStartEvent());
    }

    public Board board() {
        return board;
    }

    public Status status() {
        return status;
    }

    public void drop() {
        movement.drop(currentPiece);
        lockPiece();
        events.fireEvent(new PieceDroppedEvent());
    }

    public void clearLines() {
        var removedLines = board.clearLines(this);
        events.fireEvent(new LinesClearedEvent(removedLines));
    }

    public enum Status {
        NOT_STARTED, STARTED, LOST
    }

}
