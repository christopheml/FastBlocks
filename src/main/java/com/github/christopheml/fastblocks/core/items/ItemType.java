package com.github.christopheml.fastblocks.core.items;

import com.github.christopheml.fastblocks.random.Rng;

public enum ItemType {

    SPECIAL_BLOCK_CLEAR('B'),
    CLEAR_LINE('C'),
    NUKE_FIELD('N'),
    RANDOM_BLOCK_CLEAR('R'),
    ADD_LINE('A');

    public final char letter;

    ItemType(char letter) {
        this.letter = letter;
    }

    public static ItemType random() {
        return Rng.pick(ItemType.class);
    }

}
