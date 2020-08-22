package com.github.christopheml.fastblocks.ui.controllers;

import com.github.christopheml.fastblocks.core.Game;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Controller instanciated automatically upon loading the FXML file for the game.
 */
public class MainUiController {

    private final Game game;

    public MainUiController(Game game) {
        this.game = game;
    }

    @FXML
    private void newGameAction(ActionEvent event) {
        game.start();
    }

    @FXML
    private void quitAction(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

}
