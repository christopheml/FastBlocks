package com.github.christopheml.fastblocks.ui.events.game;

import com.github.christopheml.fastblocks.ui.events.EventListener;

public interface GameEventListener extends EventListener {

    void onGameStarted(GameStartEvent event);

}
