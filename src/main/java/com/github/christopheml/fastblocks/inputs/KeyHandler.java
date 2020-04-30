package com.github.christopheml.fastblocks.inputs;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.EnumSet;
import java.util.Set;

/**
 * Handles key input and tells which keys are active.
 */
public class KeyHandler implements EventHandler<KeyEvent> {

    private final Set<KeyCode> activeKeys = EnumSet.noneOf(KeyCode.class);

    @Override
    public void handle(KeyEvent event) {
        if (event.getEventType() == KeyEvent.KEY_PRESSED) {
            activeKeys.add(event.getCode());
        } else {
            activeKeys.remove(event.getCode());
        }
    }

    public boolean isLeftPressed() {
        return activeKeys.contains(KeyCode.LEFT);
    }

    public boolean isRightPressed() {
        return activeKeys.contains(KeyCode.RIGHT);
    }

    public boolean isDownPressed() {
        return activeKeys.contains(KeyCode.DOWN);
    }

    public boolean isUpPressed() {
        return activeKeys.contains(KeyCode.UP);
    }

    public void releaseUp() {
        activeKeys.remove(KeyCode.UP);
    }

}
