package com.github.christopheml.fastblocks.ui.controllers;

import com.github.christopheml.fastblocks.core.events.board.BoardEventListener;
import com.github.christopheml.fastblocks.core.events.board.LinesClearedEvent;
import com.github.christopheml.fastblocks.core.events.game.GameEventListener;
import com.github.christopheml.fastblocks.core.events.game.GameStartEvent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

// FIXME Use a property binding to update the label automatically
public class LineCountController implements BoardEventListener, GameEventListener {

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

    @Override
    public void onGameStarted(GameStartEvent event) {
        this.lineCount = 0;
        label.setText("0");
    }

}
