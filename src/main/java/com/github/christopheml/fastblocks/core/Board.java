package com.github.christopheml.fastblocks.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Represents the content of the board.
 */
public class Board {

    public static final int COLUMNS = 12;
    public static final int LINES = 22;

    private final List<Block[]> board = new ArrayList<>();

    public Board() {
        for (int i = 0; i < LINES; ++i) {
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

    boolean collidesVerticalBottom(Point point) {
        return point.y >= LINES || isOccupied(point);
    }

    boolean collidesLateral(Point point) {
        return collidesLeftSide(point) || collidesRightSide(point) || isOccupied(point);
    }

    boolean collidesRightSide(Point point) {
        return point.x >= COLUMNS;
    }

    boolean collidesLeftSide(Point point) {
        return point.x < 0;
    }

    public List<Block> getBlocks() {
        return board.stream().flatMap(Arrays::stream).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public int clearLines(Game game) {
        processDestroyedBlocks(game);

        board.removeIf(this::isFull);
        int removed = LINES - board.size();

        while (board.size() < LINES) {
            board.add(0, new Block[COLUMNS]);
        }
        for (int line = 0; line < LINES; ++line) {
            for (int i = 0; i < COLUMNS; ++i) {
                Block block = board.get(line)[i];
                if (block != null) {
                    block.updateLine(line);
                }
            }
        }

        return removed;
    }

    private void processDestroyedBlocks(Game game) {
        List<Block> toDestroy = board.stream().filter(this::isFull).flatMap(Arrays::stream).collect(Collectors.toList());
        toDestroy.forEach(block -> block.destroy(game));
    }

    private boolean isFull(Block[] line) {
        return Arrays.stream(line).noneMatch(Objects::isNull);
    }

    public void clear() {
        board.forEach(line -> {
            Arrays.fill(line, null);
        });
    }

}
