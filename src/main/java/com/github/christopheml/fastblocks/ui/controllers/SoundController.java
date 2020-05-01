package com.github.christopheml.fastblocks.ui.controllers;

import com.github.christopheml.fastblocks.sound.SoundEffect;
import com.github.christopheml.fastblocks.sound.SoundEffectPlayer;
import com.github.christopheml.fastblocks.ui.events.board.BoardEventListener;
import com.github.christopheml.fastblocks.ui.events.board.LinesClearedEvent;
import com.github.christopheml.fastblocks.ui.events.piece.PieceDroppedEvent;
import com.github.christopheml.fastblocks.ui.events.piece.PieceEventListener;
import com.github.christopheml.fastblocks.ui.events.piece.PieceRotatedEvent;

public class SoundController implements BoardEventListener, PieceEventListener {

    private final SoundEffectPlayer player;

    public SoundController(SoundEffectPlayer player) {
        this.player = player;
    }

    @Override
    public void onLinesCleared(LinesClearedEvent event) {
        switch(event.lineCount) {
            case 2:
                player.play(SoundEffect.TWO_LINES, 0.6d);
                break;
            case 3:
                player.play(SoundEffect.THREE_LINES, 0.75d);
                break;
            case 4:
                player.play(SoundEffect.FOUR_LINES);
        }
    }

    @Override
    public void onPieceDropped(PieceDroppedEvent event) {
        player.play(SoundEffect.PIECE_DROP);
    }

    @Override
    public void onPieceRotated(PieceRotatedEvent event) {
        player.play(SoundEffect.ROTATE, 0.4d);
    }

}
