package com.github.christopheml.fastblocks.core.events.board;

import com.github.christopheml.fastblocks.core.events.Event;
import com.github.christopheml.fastblocks.core.events.EventListener;

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
