package com.github.christopheml.fastblocks.core;

public class DeadBlock implements Block {

    private final Point position;
    private final String color;

    public DeadBlock(Point position, String color) {
        this.position = position;
        this.color = color;
    }

    @Override
    public String image() {
        return "/block.png";
    }

    @Override
    public String color() {
        return color;
    }

    @Override
    public Point position() {
        return position;
    }

    @Override
    public void destroy(Game game) {
        // Do nothing
    }

}
