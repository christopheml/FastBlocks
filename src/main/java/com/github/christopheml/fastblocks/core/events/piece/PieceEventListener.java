package com.github.christopheml.fastblocks.core.events.piece;

import com.github.christopheml.fastblocks.core.events.EventListener;

public interface PieceEventListener extends EventListener {

    void onPieceDropped(PieceDroppedEvent event);

    void onPieceRotated(PieceRotatedEvent event);

}
