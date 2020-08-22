package com.github.christopheml.fastblocks.core.events.game;

import com.github.christopheml.fastblocks.core.events.EventListener;

public interface GameEventListener extends EventListener {

    void onGameStarted(GameStartEvent event);

}
