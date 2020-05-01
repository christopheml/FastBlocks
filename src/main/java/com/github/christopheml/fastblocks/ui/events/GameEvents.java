package com.github.christopheml.fastblocks.ui.events;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GameEvents {

    private final Map<Class<? extends Event>, Set<EventListener>> eventHandlers = new HashMap<>();

    public void registerEvent(Class<? extends Event> eventClass, EventListener listener) {
        eventHandlers.computeIfAbsent(eventClass, k -> new HashSet<>()).add(listener);
    }

    public void fireEvent(Event event) {
        if (eventHandlers.containsKey(event.getClass())) {
            eventHandlers.get(event.getClass()).forEach(event::propagateTo);
        }
    }

}
