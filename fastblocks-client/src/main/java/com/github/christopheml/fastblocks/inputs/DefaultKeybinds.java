package com.github.christopheml.fastblocks.inputs;

import com.github.christopheml.fastblocks.core.Game;
import javafx.scene.input.KeyCode;

public class DefaultKeybinds {

    public static void applyTo(Game game, KeyHandler keyHandler) {
        keyHandler.register(KeyCode.SPACE, game::drop, 0, true);
        keyHandler.register(KeyCode.LEFT, game::moveLeft, 1, false);
        keyHandler.register(KeyCode.RIGHT, game::moveRight, 1, false);
        keyHandler.register(KeyCode.DOWN, game::moveDown, 2, false);
        keyHandler.register(KeyCode.UP, game::rotateRight, 3, false);
        keyHandler.register(KeyCode.E, game::useItemOnSelf, 1, false);
    }

}
