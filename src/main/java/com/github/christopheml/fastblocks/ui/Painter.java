package com.github.christopheml.fastblocks.ui;

import com.github.christopheml.fastblocks.core.Block;
import com.github.christopheml.fastblocks.core.Board;
import com.github.christopheml.fastblocks.core.Piece;
import com.github.christopheml.fastblocks.core.Point;
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

    private final ImageCache imageCache = new ImageCache();

    public Painter(Canvas canvas, int blockSize) {
        this.canvas = canvas;
        this.blockSize = blockSize;
        gc = canvas.getGraphicsContext2D();
    }

    void drawPiece(Piece piece) {
        var block = imageCache.load("/block.png", blockSize, blockSize);
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

    public void drawBoard(Board board) {
        for (Block block : board.getBlocks()) {
            var image = imageCache.load(block.image(), blockSize, blockSize);
            drawBlock(block.position().x, block.position().y, image, block.color());
        }
    }

    private void drawBlock(int x, int y, Image image, String color) {
        int scaledX = x * blockSize;
        int scaledY = y * blockSize;
        gc.drawImage(image, scaledX, scaledY);
        if (!color.isEmpty()) {
            gc.setFill(Color.web(color));
            gc.fillRect(scaledX, scaledY, blockSize, blockSize);
        }
    }

}
