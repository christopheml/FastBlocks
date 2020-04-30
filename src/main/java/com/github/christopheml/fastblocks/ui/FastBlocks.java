package com.github.christopheml.fastblocks.ui;

import com.github.christopheml.fastblocks.core.Game;
import com.github.christopheml.fastblocks.inputs.KeyHandler;
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

    public void start(Stage stage) throws Exception {
        var game = new Game();
        // TODO: handle game configuration here

        Parent root = FXMLLoader.load(getClass().getResource("/main-window.fxml"));
        Scene scene = new Scene(root, Color.WHITESMOKE);

        var canvas = (Canvas) scene.lookup("#gameCanvas");
        Painter painter = new Painter(canvas, 24);


        scene.setOnKeyPressed(keyHandler);
        scene.setOnKeyReleased(keyHandler);

        stage.setTitle("FastBlocks");
        stage.setScene(scene);
        stage.show();

        AnimationTimer gameLoop = new GameLoop(game, painter, keyHandler);
        gameLoop.start();
    }

}
