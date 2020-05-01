package com.github.christopheml.fastblocks.ui.controllers;

import com.github.christopheml.fastblocks.ui.events.GameEvent;
import com.github.christopheml.fastblocks.ui.events.GameEventHandler;
import com.github.christopheml.fastblocks.ui.events.LineClearEvent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

public class LineCountController implements GameEventHandler {

    private final Label label;

    private int lineCount;

    public LineCountController(Scene scene) {
        label = (Label) scene.lookup("#lineCounter");
    }

    @Override
    public void process(GameEvent event) {
        this.lineCount = lineCount + ((LineClearEvent) event).lineCount;
        label.setText(String.valueOf(this.lineCount));
    }

}
