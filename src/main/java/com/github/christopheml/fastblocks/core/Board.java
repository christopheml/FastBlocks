package com.github.christopheml.fastblocks.core;

import com.github.christopheml.fastblocks.core.blocks.Block;
import com.github.christopheml.fastblocks.core.blocks.DeadBlock;
import com.github.christopheml.fastblocks.core.blocks.ItemBlock;
import com.github.christopheml.fastblocks.core.items.ItemType;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents the content of the board.
 */
public class Board {

    public static final int COLUMNS = 12;
    public static final int LINES = 22;

    private final List<Block[]> board = new ArrayList<>();

    public Board() {
        for (var i = 0; i < LINES; ++i) {
            board.add(new Block[COLUMNS]);
        }
    }

    public void lock(Piece piece) {
        piece.blocksPositions().forEach(p -> fillBlock(p, piece.shape().color));
    }

    public void fillBlock(Point position, String color) {
        if (!isOccupied(position)) {
            board.get(position.y)[position.x] = new DeadBlock(position, color);
        }
    }

    public boolean isOccupied(Point p) {
        if (collidesLeftSide(p) || collidesRightSide(p) || p.y < 0 || p.y >= LINES) {
            return false;
        }
        return board.get(p.y)[p.x] != null;
    }

    boolean collidesVerticalTop(Point point) {
        return point.y < 0 || isOccupied(point);
    }

    public boolean collidesVerticalBottom(Point point) {
        return point.y >= LINES || isOccupied(point);
    }

    public boolean collidesLateral(Point point) {
        return collidesLeftSide(point) || collidesRightSide(point) || isOccupied(point);
    }

    public boolean collidesRightSide(Point point) {
        return point.x >= COLUMNS;
    }

    public boolean collidesLeftSide(Point point) {
        return point.x < 0;
    }

    public void spawnItems(int count) {
        var blocks = getBlocks();
        Collections.shuffle(blocks);
        blocks.stream().limit(count).map(Block::position)
                .forEach(p -> board.get(p.y)[p.x] = new ItemBlock(p, ItemType.random()));
    }

    public List<Block> getBlocks() {
        return board.stream().flatMap(Arrays::stream).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public int clearLines(Game game) {
        var fullLinesNumbers = IntStream.range(0, LINES)
                .filter(i -> isFull(board.get(i))).boxed().collect(Collectors.toList());

        var removedLinesCount = fullLinesNumbers.size();

        var deletedBlocks = deleteLines(fullLinesNumbers);

        fullLinesNumbers.forEach(i -> {
            board.add(0, new Block[COLUMNS]);
        });

        updateBlockCoordinates();

        extractItems(game, deletedBlocks, removedLinesCount);

        if (removedLinesCount > 1) {
            spawnItems(removedLinesCount);
        }

        return removedLinesCount;
    }

    private void extractItems(Game game, List<Block> deletedBlocks, int removedLinesCount) {
        List<ItemType> items = new ArrayList<>();
        deletedBlocks.stream()
                .filter(block -> block instanceof ItemBlock)
                .map(block -> ((ItemBlock) block).type)
                .forEach(item -> {
                    for (var i = 0; i < removedLinesCount; i++) {
                        items.add(item);
                    }
                });
        Collections.shuffle(items);
        items.forEach(game::gainItem);
    }

    private List<Block> deleteLines(List<Integer> lineNumbers) {
        var deletedBlocks = lineNumbers.stream().map(board::get).flatMap(Arrays::stream).collect(Collectors.toList());
        lineNumbers.sort(Comparator.reverseOrder());
        lineNumbers.forEach(i -> board.remove(i.intValue()));
        return deletedBlocks;
    }

    private void updateBlockCoordinates() {
        for (var line = 0; line < LINES; ++line) {
            for (var i = 0; i < COLUMNS; ++i) {
                var block = board.get(line)[i];
                if (block != null) {
                    block.updateLine(line);
                }
            }
        }
    }

    private boolean isFull(Block[] line) {
        return Arrays.stream(line).noneMatch(Objects::isNull);
    }

    public void clear() {
        board.forEach(line -> Arrays.fill(line, null));
    }

    public void specialBlockClear() {
        board.stream().flatMap(Arrays::stream).filter(block -> block instanceof ItemBlock).map(Block::position)
                .forEach(p -> board.get(p.y)[p.x] = new DeadBlock(p, Shape.random().color));
    }

}
