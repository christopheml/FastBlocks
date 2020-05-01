package com.github.christopheml.fastblocks.ui.events.game;

import com.github.christopheml.fastblocks.ui.events.Event;
import com.github.christopheml.fastblocks.ui.events.EventListener;

public class GameStartEvent extends Event {

    @Override
    public void propagateTo(EventListener listener) {
        ((GameEventListener) listener).onGameStarted(this);
    }

}
