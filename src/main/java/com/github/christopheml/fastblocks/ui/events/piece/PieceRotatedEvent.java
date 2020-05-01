package com.github.christopheml.fastblocks.ui.events.piece;

import com.github.christopheml.fastblocks.ui.events.Event;
import com.github.christopheml.fastblocks.ui.events.EventListener;

public class PieceRotatedEvent extends Event {

    @Override
    public void propagateTo(EventListener listener) {
        ((PieceEventListener) listener).onPieceRotated(this);
    }

}
