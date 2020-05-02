package com.github.christopheml.fastblocks.core.items;

import com.github.christopheml.fastblocks.core.Game;


/**
 * Applies items on the current player.
 */
public class ItemActions {

    private final Game game;

    public ItemActions(Game game) {
        this.game = game;
    }

    public void specialBlockClear() {
        game.board().specialBlockClear();
    }

    public void clearLine() {
        game.board().clearLine();
    }

    public void nukeField() {
        game.board().clear();
    }

    public void randomBlockClear() {

    }

}
