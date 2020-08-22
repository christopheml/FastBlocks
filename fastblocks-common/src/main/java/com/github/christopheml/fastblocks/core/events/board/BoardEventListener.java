package com.github.christopheml.fastblocks.core.events.board;

import com.github.christopheml.fastblocks.core.events.EventListener;

public interface BoardEventListener extends EventListener {

    void onLinesCleared(LinesClearedEvent event);

}
