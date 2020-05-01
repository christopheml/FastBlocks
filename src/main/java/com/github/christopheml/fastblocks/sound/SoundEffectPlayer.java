package com.github.christopheml.fastblocks.sound;

import javafx.scene.media.AudioClip;

import java.util.EnumMap;

public class SoundEffectPlayer {

    private final EnumMap<SoundEffect, AudioClip> effects = new EnumMap<>(SoundEffect.class);

    public SoundEffectPlayer() {
        for (SoundEffect availableEffect : SoundEffect.values()) {
            effects.put(availableEffect, new AudioClip(getClass().getResource("/sound/" + availableEffect.file).toExternalForm()));
        }
    }

    public void play(SoundEffect effect) {
        effects.get(effect).play();
    }

}
