package com.github.christopheml.fastblocks.ui.controllers;

import com.github.christopheml.fastblocks.ui.events.board.BoardEventListener;
import com.github.christopheml.fastblocks.ui.events.board.LinesClearedEvent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

public class LineCountController implements BoardEventListener {

    private final Label label;

    private int lineCount;

    public LineCountController(Scene scene) {
        label = (Label) scene.lookup("#lineCounter");
    }

    @Override
    public void onLinesCleared(LinesClearedEvent event) {
        this.lineCount += event.lineCount;
        label.setText(String.valueOf(this.lineCount));
    }

}
