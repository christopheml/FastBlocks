package com.github.christopheml.fastblocks.core.events;

public abstract class Event {

    public abstract void propagateTo(EventListener listener);

}
