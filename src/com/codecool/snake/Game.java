package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.HealthBar;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.HealthPowerup;
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

        new HealthPowerup(this);

        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
        new HealthBar(this, 790, 30, Globals.redHealth);
        new HealthBar(this, 790, 30, Globals.greenHealth);
    }

    public void spawnEntities() {
        new SimpleEnemy(this);
    }

    public void start() {

        /** Creates restart button */
        Button button = new Button("Restart");
        getChildren().add(button);
        button.setOnAction(value -> {
            restart();
        });

        /** get the Scene, set eventHandler to pressed left and right keys */
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = true; break;
                case RIGHT: Globals.rightKeyDown  = true; break;
            }
        });
        /** set eventHandler to release left and right keys */
        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = false; break;
                case RIGHT: Globals.rightKeyDown  = false; break;
            }
        });
        Globals.gameLoop = new GameLoop();
        Globals.gameLoop.start();
    }


    public void restart() {
        /** if restart button clicked on the Scene or on the pop-up, stops all Animatable objects,
         * clears the Scene, removes all objects from the gameObjects, creates new instances
         * and calls the start method.*/
        Globals.gameLoop.stop();
        getChildren().clear();
        Globals.gameObjects.removeAll(Globals.gameObjects);
        new SnakeHead(this, 500, 500);

        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);

        new HealthPowerup(this);

        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
        new HealthBar(this, 790, 30, Globals.redHealth);
        new HealthBar(this, 790, 30, Globals.greenHealth);
        start();
    }

    public void gameOver() {
        /** when snake's health reach zero or snakeHead is out of bounds, called this method
         * Creates a popup window, where asks if the player wants to restart or exit */
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
