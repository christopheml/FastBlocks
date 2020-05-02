package com.github.christopheml.fastblocks.ui;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class ImageCache {

    private final Map<String, Image> images = new HashMap<>();

    public Image load(String path, int width, int height) {
        String key = key(path, width, height);
        return images.computeIfAbsent(key,
                k -> new Image(getClass().getResourceAsStream("/images/" + path), width, height, true, false));
    }

    private String key(String path, int width, int height) {
        return path + "_" + width + "x" + height;
    }

}
