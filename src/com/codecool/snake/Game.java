package com.codecool.snake;

import com.codecool.snake.entities.HealthBar;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;

public class Game extends Pane {

    public Game() {
        new SnakeHead(this, 500, 500);

        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);

        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
        new HealthBar(this, 790, 30, Globals.redHealth);
        new HealthBar(this, 790, 30, Globals.greenHealth);
    }

    public void start() {

        Globals.running = true;

        Button button = new Button("Restart");
        getChildren().add(button);
        button.setOnAction(value -> {
            restart();
        });


        Scene scene = getScene();
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = true; break;
                case RIGHT: Globals.rightKeyDown  = true; break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = false; break;
                case RIGHT: Globals.rightKeyDown  = false; break;
            }
        });
        Globals.gameLoop = new GameLoop();
        Globals.gameLoop.start();
        if (!Globals.running) {
            gameOver();
        }
    }

    public void restart() {
        Globals.gameLoop.stop();
        getChildren().clear();
        Globals.gameObjects.removeAll(Globals.gameObjects);
        new SnakeHead(this, 500, 500);

        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);

        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
        new HealthBar(this, 790, 30, Globals.redHealth);
        new HealthBar(this, 790, 30, Globals.greenHealth);
        start();
    }

    public void gameOver() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "GAME OVER");
        alert.setTitle("GAME OVER");
        alert.setHeaderText("");
        ButtonType buttonRestart = new ButtonType("Restart");
        ButtonType buttonExit = new ButtonType("Exit");
        alert.getButtonTypes().setAll(buttonRestart, buttonExit);
        alert.setOnHidden(e -> {
            if (alert.getResult() == buttonRestart) {
                restart();
            } else if (alert.getResult() == buttonExit) {
                Platform.exit();
            }
        });
        alert.show();
    }

}
