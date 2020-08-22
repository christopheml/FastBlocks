package com.github.christopheml.fastblocks.core.events.game;

import com.github.christopheml.fastblocks.core.events.Event;
import com.github.christopheml.fastblocks.core.events.EventListener;

public class GameStartEvent extends Event {

    @Override
    public void propagateTo(EventListener listener) {
        ((GameEventListener) listener).onGameStarted(this);
    }

}
