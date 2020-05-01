package com.github.christopheml.fastblocks.ui.events;

import com.github.christopheml.fastblocks.ui.events.board.BoardEvent;
import com.github.christopheml.fastblocks.ui.events.board.BoardEventListener;
import com.github.christopheml.fastblocks.ui.events.board.LinesClearedEvent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GameEvents {

    private final Map<Class<? extends BoardEvent>, Set<BoardEventListener>> boardEventHandlers = new HashMap<>();

    public void registerBoardEvent(Class<? extends BoardEvent> eventClass, BoardEventListener listener) {
        boardEventHandlers.computeIfAbsent(eventClass, k -> new HashSet<>()).add(listener);
    }

    public void fireEvent(LinesClearedEvent event) {
        if (boardEventHandlers.containsKey(event.getClass())) {
            boardEventHandlers.get(event.getClass()).forEach(listener -> {
                listener.onLinesCleared(event);
            });
        }
    }

}
