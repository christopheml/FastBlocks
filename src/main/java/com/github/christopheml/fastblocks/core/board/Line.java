package com.github.christopheml.fastblocks.core.board;

import com.github.christopheml.fastblocks.core.Board;
import com.github.christopheml.fastblocks.core.blocks.Block;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public class Line {

    private final Block[] blocks = new Block[Board.COLUMNS];

    public Line() {
    }

    public boolean isOccupied(int index) {
        return blocks[index] != null;
    }

    public void clearBlock(int index) {
        blocks[index] = null;
    }

    public void fillBlock(int index, Block block) {
        blocks[index] = block;
    }

    public void clear() {
        Arrays.fill(blocks, null);
    }

    public Stream<Block> toBlockStream() {
        return Arrays.stream(blocks);
    }

    public boolean isFull() {
        return Arrays.stream(blocks).noneMatch(Objects::isNull);
    }

    public void updateBlockHeight(int height) {
        for (var block : blocks) {
            if (block != null) {
                block.updateHeight(height);
            }
        }
    }

    public static Line empty() {
        return new Line();
    }

}
