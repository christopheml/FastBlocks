package com.github.christopheml.fastblocks.di;

import com.github.christopheml.fastblocks.core.Game;
import javafx.util.Callback;

import java.lang.reflect.Constructor;

/**
 * Instantiates FXML controllers and injects the game object in those aware of it.
 *
 * Usually, there's a framework doing this nicely, but there isn't any DI framework on this project,
 * so this is done manually with reflection.
 * Technique borrowed from here: https://stackoverflow.com/a/40539476
 */
public class DependencyInjectionControllerFactory implements Callback<Class<?>, Object> {

    private final Game game;

    public DependencyInjectionControllerFactory(Game game) {
        this.game = game;
    }

    @Override
    public Object call(Class<?> controllerType) {
        try {
            // Basically, we look for a controller taking a single Game argument and call it with the Game object.
            // Otherwise, default to the default constructor.
            for (var c : controllerType.getConstructors()) {
                if (c.getParameterCount() == 1) {
                    if (c.getParameterTypes()[0] == Game.class) {
                        return c.newInstance(game);
                    }
                }
            }
            return controllerType.getDeclaredConstructor().newInstance();
        } catch (Exception exc) {
            throw new RuntimeException(exc);
        }
    }

}
