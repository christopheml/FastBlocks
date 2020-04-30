package com.github.christopheml.fastblocks.ui;

import com.github.christopheml.fastblocks.core.Piece;
import com.github.christopheml.fastblocks.core.Point;
import com.github.christopheml.fastblocks.core.Shape;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public class Painter {

    private final Canvas canvas;

    private final GraphicsContext gc;

    private final int blockSize;

    private final Image block;

    public Painter(Canvas canvas, int blockSize) {
        this.canvas = canvas;
        this.blockSize = blockSize;
        block = new Image(getClass().getResourceAsStream("/block.png"), blockSize, blockSize, true, false);
        gc = canvas.getGraphicsContext2D();
    }

    void drawPiece(Piece piece) {
        Point piecePosition = piece.position();
        gc.setFill(Color.web(piece.shape().color));

        for (Point blockPosition : piece.blocksPositions()) {
            int scaledX = blockPosition.x * blockSize;
            int scaledY = blockPosition.y * blockSize;
            gc.drawImage(block, scaledX, scaledY);
            gc.fillRect(scaledX, scaledY, blockSize, blockSize);
        }
    }

    public void clearCanvas() {
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void drawBackground() {
        Stop[] stops = new Stop[] { new Stop(0, Color.WHITE),
                new Stop(0.5, Color.LIGHTGRAY),
                new Stop(1.0, Color.DARKGRAY)};
        var gradient = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, stops);
        gc.setFill(gradient);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void drawBoard(Shape[][] board) {
        for (int x = 0; x < 12; ++x) {
            for (int y = 0; y < 22; ++y) {
                if (board[x][y] != null) {
                    drawBlock(x, y, board[x][y].color);
                }
            }
        }
    }

    private void drawBlock(int x, int y, String color) {
        gc.setFill(Color.web(color));
        int scaledX = x * blockSize;
        int scaledY = y * blockSize;
        gc.drawImage(block, scaledX, scaledY);
        gc.fillRect(scaledX, scaledY, blockSize, blockSize);
    }

}
