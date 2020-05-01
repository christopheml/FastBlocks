package com.github.christopheml.fastblocks.ui.events.piece;

import com.github.christopheml.fastblocks.ui.events.EventListener;

public interface PieceEventListener extends EventListener {

    void onPieceDropped(PieceDroppedEvent event);

    void onPieceRotated(PieceRotatedEvent event);

}
