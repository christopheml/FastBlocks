package com.github.christopheml.fastblocks.ui.events;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GameEvents {

    private final Map<Class<? extends GameEvent>, Set<GameEventHandler>> handlers = new HashMap<>();

    public void onLineClear(GameEventHandler handler) {
        handlers.computeIfAbsent(LineClearEvent.class, k -> new HashSet<>()).add(handler);
    }

    public void fireEvent(GameEvent event) {
        if (handlers.containsKey(event.getClass())) {
            handlers.get(event.getClass()).forEach(handlers -> {
                handlers.process(event);
            });
        }
    }

}
