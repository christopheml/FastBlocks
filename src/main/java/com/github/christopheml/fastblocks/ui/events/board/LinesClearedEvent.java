package com.github.christopheml.fastblocks.ui.events.board;

public class LinesClearedEvent implements BoardEvent {

    public final int lineCount;

    public LinesClearedEvent(int lineCount) {
        this.lineCount = lineCount;
    }

}
