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
    private final Painter itemPainter;
    private final KeyHandler keyHandler;

    private final LoopTimer inputTimer = new LoopTimer(30);
    private final LoopTimer pieceGravityTimer = new LoopTimer(300);
    private final LoopTimer fillBoardTimer = new LoopTimer(80);

    public GameLoop(Game game, Painter painter, Painter itemPainter, KeyHandler keyHandler) {
        this.game = game;
        this.painter = painter;
        this.itemPainter = itemPainter;
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
                inputTimer.runOnInterval(now, keyHandler::resolveInputs);
                pieceGravityTimer.runOnInterval(now, game::moveDown);
                paintFrame();
                break;
            }
            case LOST: {
                keyHandler.flush();
                fillBoardTimer.runOnInterval(now, () -> BoardFiller.fillBlocks(game.board(), 10));
                paintBoard();
                break;
            }
        }
    }

    private void paintFrame() {
        paintBoard();
        paintItems();
        painter.drawPiece(game.currentPiece());
    }

    private void paintItems() {
        itemPainter.drawItems(game.items());
    }

    private void paintBoard() {
        painter.clearCanvas();
        painter.drawBackground();
        painter.drawBoard(game.board());
    }

}
