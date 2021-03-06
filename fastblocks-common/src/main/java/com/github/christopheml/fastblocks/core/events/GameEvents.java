package com.github.christopheml.fastblocks.core.events;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GameEvents {

    private final Map<Class<? extends Event>, Set<EventListener>> eventHandlers = new HashMap<>();

    public void registerEvent(Class<? extends Event> eventClass, EventListener listener) {
        eventHandlers.computeIfAbsent(eventClass, k -> new HashSet<>()).add(listener);
    }

    @SafeVarargs
    public final void registerListener(EventListener listener, Class<? extends Event>... eventClasses) {
        for (var eventClass : eventClasses) {
            registerEvent(eventClass, listener);
        }
    }

    public void fireEvent(Event event) {
        if (eventHandlers.containsKey(event.getClass())) {
            eventHandlers.get(event.getClass()).forEach(event::propagateTo);
        }
    }

}
