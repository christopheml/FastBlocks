package com.github.christopheml.fastblocks;

import com.github.christopheml.fastblocks.ui.FastBlocks;

/**
 * Workaround class for building an executable non-modular jar.
 *
 * See https://openjfx.io/openjfx-docs/#maven
 */
public class Main {

    public static void main(String[] args) {
        FastBlocks.main(args);
    }

}
