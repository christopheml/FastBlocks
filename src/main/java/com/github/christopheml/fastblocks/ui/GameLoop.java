package com.github.christopheml.fastblocks.ui;

import com.github.christopheml.fastblocks.core.Game;
import com.github.christopheml.fastblocks.inputs.KeyHandler;
import com.github.christopheml.fastblocks.ui.animations.BoardFiller;
import javafx.animation.AnimationTimer;

/**
 * Coordinates all game components together in the proper timing.
 */
class GameLoop extends AnimationTimer {

    private final Game game;
    private final Painter painter;
    private final KeyHandler keyHandler;

    private final LoopTimer inputTimer = new LoopTimer(30);
    private final LoopTimer pieceGravityTimer = new LoopTimer(300);
    private final LoopTimer fillBoardTimer = new LoopTimer(80);

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
                fillBoardTimer.runOnInterval(now, () -> BoardFiller.fillBlocks(game.board(), 10));
                paintBoard();
                break;
            }
        }
    }

    private void handleInput() {
        if (keyHandler.isSpacePressed()) {
            game.attemptDrop();
        }
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
        }
        keyHandler.flush();
    }

    private void paintFrame() {
        paintBoard();
        painter.drawPiece(game.currentPiece());
    }

    private void paintBoard() {
        painter.clearCanvas();
        painter.drawBackground();
        painter.drawBoard(game.board());
    }

}
