package com.github.christopheml.fastblocks.ui.controllers;

import com.github.christopheml.fastblocks.sound.SoundEffect;
import com.github.christopheml.fastblocks.sound.SoundEffectPlayer;
import com.github.christopheml.fastblocks.ui.events.board.BoardEventListener;
import com.github.christopheml.fastblocks.ui.events.board.LinesClearedEvent;
import com.github.christopheml.fastblocks.ui.events.piece.PieceDroppedEvent;
import com.github.christopheml.fastblocks.ui.events.piece.PieceEventListener;

public class SoundController implements BoardEventListener, PieceEventListener {

    private final SoundEffectPlayer player;

    public SoundController(SoundEffectPlayer player) {
        this.player = player;
    }

    @Override
    public void onLinesCleared(LinesClearedEvent event) {
        switch(event.lineCount) {
            case 2:
                player.play(SoundEffect.TWO_LINES);
                break;
            case 3:
                player.play(SoundEffect.THREE_LINES);
                break;
            case 4:
                player.play(SoundEffect.FOUR_LINES);
        }
    }

    @Override
    public void onPieceDropped(PieceDroppedEvent event) {
        player.play(SoundEffect.PIECE_DROP);
    }

}
