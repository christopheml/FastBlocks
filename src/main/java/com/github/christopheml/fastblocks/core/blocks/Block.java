package com.github.christopheml.fastblocks.core.blocks;

import com.github.christopheml.fastblocks.core.Point;

public abstract class Block {

    private Point position;

    public Block(Point position) {
        this.position = position;
    }

    public String image() {
        return "block.png";
    }

    public String color() {
        return "";
    }

    public Point position() {
        return position;
    }

    public void updateHeight(int line) {
        position = Point.p(position.x, line);
    }


}
