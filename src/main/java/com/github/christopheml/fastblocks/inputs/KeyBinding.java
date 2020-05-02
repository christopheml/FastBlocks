package com.github.christopheml.fastblocks.inputs;

import javafx.scene.input.KeyCode;

class KeyBinding {

    public final KeyCode keyCode;

    public final Runnable action;

    public final int priority;

    public final boolean exclusive;

    public KeyBinding(KeyCode keyCode, Runnable action, int priority, boolean exclusive) {
        this.keyCode = keyCode;
        this.priority = priority;
        this.exclusive = exclusive;
        this.action = action;
    }


}
