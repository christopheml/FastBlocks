package com.github.christopheml.fastblocks.ui.events;

public class LineClearEvent implements GameEvent {

    public final int lineCount;

    public LineClearEvent(int lineCount) {
        this.lineCount = lineCount;
    }

}
