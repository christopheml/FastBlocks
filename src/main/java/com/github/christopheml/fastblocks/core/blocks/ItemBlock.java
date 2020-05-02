package com.github.christopheml.fastblocks.core.blocks;

import com.github.christopheml.fastblocks.core.Point;
import com.github.christopheml.fastblocks.core.items.ItemType;

public class ItemBlock extends Block {

    public final ItemType type;

    public ItemBlock(Point position, ItemType type) {
        super(position);
        this.type = type;
    }

    @Override
    public String image() {
        return "item" + type.letter + ".png";
    }

}
