package com.github.christopheml.fastblocks.core.items;

import com.github.christopheml.fastblocks.core.Board;
import com.github.christopheml.fastblocks.core.Game;
import com.github.christopheml.fastblocks.core.Shape;
import com.github.christopheml.fastblocks.core.blocks.Block;
import com.github.christopheml.fastblocks.core.blocks.DeadBlock;
import com.github.christopheml.fastblocks.core.blocks.ItemBlock;
import com.github.christopheml.fastblocks.random.Rng;


/**
 * Applies items on the current player.
 */
public class ItemActions {

    private final Game game;

    public ItemActions(Game game) {
        this.game = game;
    }

    public void specialBlockClear() {
        game.board().stream().filter(block -> block instanceof ItemBlock).map(Block::position)
                .forEach(p -> game.board().line(p.y).fillBlock(p.x, new DeadBlock(p, Shape.random().color)));
    }

    public void clearLine() {
        game.board().clearLine();
    }

    public void nukeField() {
        game.board().clear();
    }

    public void randomBlockClear() {
        for (var i = 0; i < 10; i++) {
            game.board().line(Rng.nextInt(Board.LINES)).clearBlock(Rng.nextInt(Board.COLUMNS));
        }
    }

    public void addGarbageLine() {
        game.board().addGarbageLine();
    }

}
