package com.github.christopheml.fastblocks.core.blocks;

import com.github.christopheml.fastblocks.core.Point;

public class DeadBlock extends Block {

    private final String color;

    public DeadBlock(Point position, String color) {
        super(position);
        this.color = color;
    }

    @Override
    public String color() {
        return color;
    }


}
