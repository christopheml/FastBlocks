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

    private final LoopTimer inputTimer = new LoopTimer(50);
    private final LoopTimer pieceGravityTimer = new LoopTimer(300);


    public GameLoop(Game game, Painter painter, KeyHandler keyHandler) {
        this.game = game;
        this.painter = painter;
        this.keyHandler = keyHandler;
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
                inputTimer.runOnInterval(now, this::handleInput);
                pieceGravityTimer.runOnInterval(now, game::attemptMoveDown);
                paintFrame();
                break;
            }
            case LOST: {
                stop();
                break;
            }
        }
    }

    private void handleInput() {
        if (keyHandler.isLeftPressed()) {
            game.attemptMoveLeft();
        } else if (keyHandler.isRightPressed()) {
            game.attemptMoveRight();
        }
        if (keyHandler.isDownPressed()) {
            game.attemptMoveDown();
        }
        if (keyHandler.isUpPressed()) {
            game.attemptRotateRight();
            keyHandler.releaseUp();
        }
    }

    private void paintFrame() {
        painter.clearCanvas();
        painter.drawBackground();
        painter.drawBoard(game.board());
        painter.drawPiece(game.currentPiece());
    }

}
