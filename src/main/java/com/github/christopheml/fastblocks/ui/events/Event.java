package com.github.christopheml.fastblocks.ui.events;

public abstract class Event {

    public abstract void propagateTo(EventListener listener);

}
