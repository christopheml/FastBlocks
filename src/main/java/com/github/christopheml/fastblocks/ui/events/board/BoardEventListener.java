package com.github.christopheml.fastblocks.ui.events.board;

import com.github.christopheml.fastblocks.ui.events.EventListener;

public interface BoardEventListener extends EventListener {

    void onLinesCleared(LinesClearedEvent event);

}
