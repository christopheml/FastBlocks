package com.github.christopheml.fastblocks.core;

import com.github.christopheml.fastblocks.sound.SoundEffect;
import com.github.christopheml.fastblocks.sound.SoundEffectPlayer;
import com.github.christopheml.fastblocks.ui.events.GameEvents;
import com.github.christopheml.fastblocks.ui.events.board.LinesClearedEvent;

import java.util.List;
import java.util.Random;

/**
 * Main game logic.
 */
public class Game {

    // FIXME: move this away with an event-based system
    private final SoundEffectPlayer soundEffectPlayer = new SoundEffectPlayer();
    private final GameEvents events;

    private Piece currentPiece;

    private final Board board = new Board();

    private Status status = Status.NOT_STARTED;

    public Game(GameEvents events) {
        this.events = events;
    }

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
            attemptLeftWallKick(rotated);
            return;
        } else if (rotated.stream().anyMatch(board::collidesRightSide)) {
            attemptRightWallKick(rotated);
            return;
        }
        currentPiece.rotateRight();
    }

    private void attemptRightWallKick(List<Point> rotated) {
        if (rotated.stream().map(Point::left).noneMatch(board::collidesLateral)) {
            currentPiece.moveLeft();
            currentPiece.rotateRight();
        }
    }

    private void attemptLeftWallKick(List<Point> rotated) {
        if (rotated.stream().map(Point::right).noneMatch(board::collidesLateral)) {
            currentPiece.moveRight();
            currentPiece.rotateRight();
        }
    }

    public void attemptMoveDown() {
        if (currentPiece.blocksPositions().stream()
                .map(Point::down)
                .noneMatch(board::collidesVerticalBottom)) {
            currentPiece.moveDown();
        } else {
            board.lock(currentPiece);
            clearLines();
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
        soundEffectPlayer.play(SoundEffect.PIECE_DROP);
    }

    public void clearLines() {
        int removedLines = board.clearLines(this);
        events.fireEvent(new LinesClearedEvent(removedLines));
        if (removedLines == 2) {
            soundEffectPlayer.play(SoundEffect.TWO_LINES);
        } else if (removedLines == 3) {
            soundEffectPlayer.play(SoundEffect.THREE_LINES);
        } else if (removedLines == 4) {
            soundEffectPlayer.play(SoundEffect.FOUR_LINES);
        }
    }

    public enum Status {
        NOT_STARTED, STARTED, LOST;
    }

}
