package com.github.christopheml.fastblocks.ui.events.board;

import com.github.christopheml.fastblocks.ui.events.Event;
import com.github.christopheml.fastblocks.ui.events.EventListener;

public class LinesClearedEvent extends Event {

    public final int lineCount;

    public LinesClearedEvent(int lineCount) {
        this.lineCount = lineCount;
    }

    @Override
    public void propagateTo(EventListener listener) {
        ((BoardEventListener) listener).onLinesCleared(this);
    }

}
