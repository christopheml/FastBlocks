package com.github.christopheml.fastblocks.core;

public interface Block {

    String image();

    String color();

    Point position();

    void destroy(Game game);

}
