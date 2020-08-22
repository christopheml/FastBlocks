package com.github.christopheml.fastblocks.inputs;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Handles key input and tells which keys are active.
 */
public class KeyHandler implements EventHandler<KeyEvent> {

    private final Set<KeyCode> activeKeys = EnumSet.noneOf(KeyCode.class);

    private final List<KeyBinding> bindings = new ArrayList<>();

    @Override
    public void handle(KeyEvent event) {
        if (event.getEventType() == KeyEvent.KEY_PRESSED) {
            activeKeys.add(event.getCode());
        }
    }

    public void flush() {
        activeKeys.clear();
    }

    public void register(KeyCode keyCode, Runnable action, int priority, boolean exclusive) {
        bindings.add(new KeyBinding(keyCode, action, priority, exclusive));
        bindings.sort(Comparator.comparing(keyBinding -> keyBinding.priority));
    }

    public void resolveInputs() {
        activeKeys.forEach(keyCode -> {
            var activeBindings = bindings.stream().filter(keyBinding -> keyBinding.keyCode == keyCode).collect(Collectors.toList());
            for (var activeBinding : activeBindings) {
                activeBinding.action.run();
                if (activeBinding.exclusive) {
                    break;
                }
            }
        });
        activeKeys.clear();
    }

}
