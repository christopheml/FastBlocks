package com.github.christopheml.fastblocks.ui;

import com.github.christopheml.fastblocks.core.Game;
import com.github.christopheml.fastblocks.di.DependencyInjectionControllerFactory;
import com.github.christopheml.fastblocks.inputs.KeyHandler;
import com.github.christopheml.fastblocks.sound.SoundEffectPlayer;
import com.github.christopheml.fastblocks.ui.controllers.LineCountController;
import com.github.christopheml.fastblocks.ui.controllers.SoundController;
import com.github.christopheml.fastblocks.ui.events.GameEvents;
import com.github.christopheml.fastblocks.ui.events.board.LinesClearedEvent;
import com.github.christopheml.fastblocks.ui.events.game.GameStartEvent;
import com.github.christopheml.fastblocks.ui.events.piece.PieceDroppedEvent;
import com.github.christopheml.fastblocks.ui.events.piece.PieceRotatedEvent;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class FastBlocks extends Application {

    private final KeyHandler keyHandler = new KeyHandler();

    private final GameEvents events = new GameEvents();

    public void start(Stage stage) throws Exception {
        var game = new Game(events);
        // TODO: handle game configuration here

        var fxmlLoader = new FXMLLoader(getClass().getResource("/main-window.fxml"));
        fxmlLoader.setControllerFactory(new DependencyInjectionControllerFactory(game));
        Parent root = fxmlLoader.load();

        var scene = new Scene(root, Color.WHITESMOKE);

        // Controllers
        var lineCountController = new LineCountController(scene);
        events.registerListener(lineCountController, LinesClearedEvent.class, GameStartEvent.class);

        var soundEffectPlayer = new SoundEffectPlayer();
        var soundController = new SoundController(soundEffectPlayer);
        events.registerListener(soundController, LinesClearedEvent.class, PieceDroppedEvent.class, PieceRotatedEvent.class);

        scene.setOnKeyPressed(keyHandler);
        scene.setOnKeyReleased(keyHandler);

        // Register keys
        keyHandler.register(KeyCode.SPACE, game::drop, 0, true);
        keyHandler.register(KeyCode.LEFT, game::moveLeft, 1, false);
        keyHandler.register(KeyCode.RIGHT, game::moveRight, 1, false);
        keyHandler.register(KeyCode.DOWN, game::moveDown, 2, false);
        keyHandler.register(KeyCode.UP, game::rotateRight, 3, false);

        stage.setTitle("FastBlocks");
        stage.setScene(scene);
        stage.show();

        var canvas = (Canvas) scene.lookup("#gameCanvas");
        var painter = new Painter(canvas, 24);

        AnimationTimer gameLoop = new GameLoop(game, painter, keyHandler);
        gameLoop.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
