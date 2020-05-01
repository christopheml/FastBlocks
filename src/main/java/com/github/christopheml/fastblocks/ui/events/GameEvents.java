package com.github.christopheml.fastblocks.ui.events;

import com.github.christopheml.fastblocks.ui.events.board.BoardEvent;
import com.github.christopheml.fastblocks.ui.events.board.BoardEventListener;
import com.github.christopheml.fastblocks.ui.events.board.LinesClearedEvent;
import com.github.christopheml.fastblocks.ui.events.game.GameEvent;
import com.github.christopheml.fastblocks.ui.events.game.GameEventListener;
import com.github.christopheml.fastblocks.ui.events.game.GameStartEvent;
import com.github.christopheml.fastblocks.ui.events.piece.PieceDroppedEvent;
import com.github.christopheml.fastblocks.ui.events.piece.PieceEvent;
import com.github.christopheml.fastblocks.ui.events.piece.PieceEventListener;
import com.github.christopheml.fastblocks.ui.events.piece.PieceRotatedEvent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GameEvents {

    private final Map<Class<? extends BoardEvent>, Set<BoardEventListener>> boardEventHandlers = new HashMap<>();
    private final Map<Class<? extends PieceEvent>, Set<PieceEventListener>> pieceEventHandlers = new HashMap<>();
    private final Map<Class<? extends GameEvent>, Set<GameEventListener>> gameEventHandlers = new HashMap<>();

    public void registerBoardEvent(Class<? extends BoardEvent> eventClass, BoardEventListener listener) {
        boardEventHandlers.computeIfAbsent(eventClass, k -> new HashSet<>()).add(listener);
    }

    public void registerPieceEvent(Class<? extends PieceEvent> eventClass, PieceEventListener listener) {
        pieceEventHandlers.computeIfAbsent(eventClass, k -> new HashSet<>()).add(listener);
    }

    public void registerGameEvent(Class<? extends GameEvent> eventClass, GameEventListener listener) {
        gameEventHandlers.computeIfAbsent(eventClass, k -> new HashSet<>()).add(listener);
    }

    public void fireEvent(LinesClearedEvent event) {
        if (boardEventHandlers.containsKey(event.getClass())) {
            boardEventHandlers.get(event.getClass()).forEach(listener -> {
                listener.onLinesCleared(event);
            });
        }
    }

    public void fireEvent(PieceDroppedEvent event) {
        if (pieceEventHandlers.containsKey(event.getClass())) {
            pieceEventHandlers.get(event.getClass()).forEach(listener -> {
                listener.onPieceDropped(event);
            });
        }
    }

    public void fireEvent(PieceRotatedEvent event) {
        if (pieceEventHandlers.containsKey(event.getClass())) {
            pieceEventHandlers.get(event.getClass()).forEach(listener -> {
                listener.onPieceRotated(event);
            });
        }
    }

    public void fireEvent(GameStartEvent event) {
        if (gameEventHandlers.containsKey(event.getClass())) {
            gameEventHandlers.get(event.getClass()).forEach(listener -> {
                listener.onGameStarted(event);
            });
        }
    }

}
