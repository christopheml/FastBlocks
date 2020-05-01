package com.github.christopheml.fastblocks.sound;

public enum SoundEffect {

    PIECE_DROP("215162__otisjames__thud.wav"),
    TWO_LINES("450881__flakdemier__pop-horns-fall-emaj.wav"),
    THREE_LINES("450880__flakdemier__pop-horns-fall-f-min7.wav"),
    FOUR_LINES("450882__flakdemier__pop-horns-fall-a-unison.wav");

    public final String file;

    SoundEffect(String file) {
        this.file = file;
    }


}
