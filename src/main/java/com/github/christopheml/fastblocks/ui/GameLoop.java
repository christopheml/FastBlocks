package com.github.christopheml.fastblocks.ui;

import com.github.christopheml.fastblocks.core.Game;
import com.github.christopheml.fastblocks.inputs.KeyHandler;
import javafx.animation.AnimationTimer;

/**
 * Coordinates all game components together in the proper timing.
 */
class GameLoop extends AnimationTimer {

    private final Game game;
    private final Painter painter;
    private final KeyHandler keyHandler;

    private long lastFallUpdate;
    private long lastMoveUpdate;

    public GameLoop(Game game, Painter painter, KeyHandler keyHandler) {
        this.game = game;
        this.painter = painter;
        this.keyHandler = keyHandler;
        lastFallUpdate = 0;
        lastMoveUpdate = 0;
    }

    @Override
    public void start() {
        super.start();
        game.start();
    }

    @Override
    public void handle(long now) {
        switch (game.status()) {
            case STARTED: {
                if (elapsedMs(now, lastMoveUpdate) >= 50) {
                    if (keyHandler.isLeftPressed()) {
                        game.attemptMoveLeft();
                    } else if (keyHandler.isRightPressed()) {
                        game.attemptMoveRight();
                    }
                    if (keyHandler.isDownPressed()) {
                        game.attemptMoveDown();
                    }
                    lastMoveUpdate = now;
                }

                if (elapsedMs(now, lastFallUpdate) >= 300) {
                    game.attemptMoveDown();
                    lastFallUpdate = now;
                }

                painter.clearCanvas();
                painter.drawBackground();
                painter.drawBoard(game.board());
                painter.drawPiece(game.currentPiece());
                break;
            }
            case LOST: {
                stop();
                break;
            }
        }
    }

    public long elapsedMs(long now, long then) {
        return (now - then) / 1000000;
    }
}
