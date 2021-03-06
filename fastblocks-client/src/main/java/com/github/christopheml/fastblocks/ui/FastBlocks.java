package com.github.christopheml.fastblocks.ui;

import com.github.christopheml.fastblocks.core.Game;
import com.github.christopheml.fastblocks.core.events.GameEvents;
import com.github.christopheml.fastblocks.core.events.board.LinesClearedEvent;
import com.github.christopheml.fastblocks.core.events.game.GameStartEvent;
import com.github.christopheml.fastblocks.core.events.piece.PieceDroppedEvent;
import com.github.christopheml.fastblocks.core.events.piece.PieceRotatedEvent;
import com.github.christopheml.fastblocks.di.DependencyInjectionControllerFactory;
import com.github.christopheml.fastblocks.inputs.DefaultKeybinds;
import com.github.christopheml.fastblocks.inputs.KeyHandler;
import com.github.christopheml.fastblocks.sound.SoundEffectPlayer;
import com.github.christopheml.fastblocks.ui.controllers.LineCountController;
import com.github.christopheml.fastblocks.ui.controllers.SoundController;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
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

        setupKeyboard(game, scene);

        stage.setTitle("FastBlocks");
        stage.setScene(scene);
        stage.show();

        var canvas = (Canvas) scene.lookup("#gameCanvas");
        var painter = new Painter(canvas, 24);

        var itemCanvas = (Canvas) scene.lookup("#itemCanvas");
        var itemPainter = new Painter(itemCanvas, 24);

        AnimationTimer gameLoop = new GameLoop(game, painter, itemPainter, keyHandler);
        gameLoop.start();
    }

    private void setupKeyboard(Game game, Scene scene) {
        scene.setOnKeyPressed(keyHandler);
        scene.setOnKeyReleased(keyHandler);

        DefaultKeybinds.applyTo(game, keyHandler);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
