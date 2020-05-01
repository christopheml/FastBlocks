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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class FastBlocks extends Application {

    private final KeyHandler keyHandler = new KeyHandler();

    private final GameEvents events = new GameEvents();

    public void start(Stage stage) throws Exception {
        var game = new Game(events);
        // TODO: handle game configuration here

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main-window.fxml"));
        fxmlLoader.setControllerFactory(new DependencyInjectionControllerFactory(game));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root, Color.WHITESMOKE);

        var canvas = (Canvas) scene.lookup("#gameCanvas");
        Painter painter = new Painter(canvas, 24);

        SoundEffectPlayer soundEffectPlayer = new SoundEffectPlayer();

        // Controllers
        LineCountController lineCountController = new LineCountController(scene);
        events.registerEvent(LinesClearedEvent.class, lineCountController);
        events.registerEvent(GameStartEvent.class, lineCountController);

        SoundController soundController = new SoundController(soundEffectPlayer);
        events.registerEvent(LinesClearedEvent.class, soundController);
        events.registerEvent(PieceDroppedEvent.class, soundController);
        events.registerEvent(PieceRotatedEvent.class, soundController);

        scene.setOnKeyPressed(keyHandler);
        scene.setOnKeyReleased(keyHandler);

        stage.setTitle("FastBlocks");
        stage.setScene(scene);
        stage.show();

        AnimationTimer gameLoop = new GameLoop(game, painter, keyHandler);
        gameLoop.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
