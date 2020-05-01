package com.github.christopheml.fastblocks.ui.events;

@FunctionalInterface
public interface GameEventHandler {

    void process(GameEvent event);

}
