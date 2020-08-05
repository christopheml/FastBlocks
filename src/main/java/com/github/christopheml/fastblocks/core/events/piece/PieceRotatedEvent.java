package com.github.christopheml.fastblocks.core.events.piece;

import com.github.christopheml.fastblocks.core.events.Event;
import com.github.christopheml.fastblocks.core.events.EventListener;

public class PieceRotatedEvent extends Event {

    @Override
    public void propagateTo(EventListener listener) {
        ((PieceEventListener) listener).onPieceRotated(this);
    }

}
