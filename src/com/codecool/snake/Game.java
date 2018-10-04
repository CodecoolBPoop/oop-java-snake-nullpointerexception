package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.HealthBar;
import com.codecool.snake.entities.Stepper;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.GrowPowerup;
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

    private static boolean isMultiPlayer;

    private void makeEntities(){

        SnakeHead player1 = new SnakeHead(this, 500, 500);
        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);

        new HealthPowerup(this);
        new GrowPowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);

        new HealthBar(this, 790, 30, Globals.redHealth, player1);
        new HealthBar(this, 790, 30, Globals.greenHealth, player1);
    }

    public Game() {
        makeEntities();
    }


    public void setGame(Game game) {
        Globals.game = game;
    }

    public void start() {

        if (isMultiPlayer) {
            SnakeHead player2 = new SnakeHead(this, 200, 500);
            new HealthBar(this, 100, 30, Globals.redHealth, player2);
            new HealthBar(this, 100, 30, Globals.greenHealth, player2);
        }
        new Stepper(this);

        Globals.leftKeyDown = false;
        Globals.rightKeyDown = false;
        Globals.AkeyDown = false;
        Globals.DkeyDown = false;

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
                case A: Globals.AkeyDown = true; break;
                case D: Globals.DkeyDown = true; break;
            }
        });


        /** set eventHandler to release left and right keys */
        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = false; break;
                case RIGHT: Globals.rightKeyDown  = false; break;
                case A: Globals.AkeyDown = false; break;
                case D: Globals.DkeyDown = false; break;
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
        makeEntities();
        start();
    }

    public void showGameModeModal() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Select game mode.");
        alert.setTitle("SNAKE");
        ButtonType singlePlayer = new ButtonType("single player");
        ButtonType multiPlayer = new ButtonType("two player mode");
        alert.getButtonTypes().setAll(singlePlayer, multiPlayer);
        alert.setOnHidden(e -> {
            if (alert.getResult() == singlePlayer)
                isMultiPlayer = false;

            if (alert.getResult() == multiPlayer)
                isMultiPlayer = true;
            start();
        });
        alert.show();
    }

    public void gameOver(int score) {
        /** when snake's health reach zero or snakeHead is out of bounds, called this method
         * Creates a popup window, where asks if the player wants to restart or exit */
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "GAME OVER");
        alert.setTitle("GAME OVER");
        alert.setHeaderText("You died! Your snake's length is " + score);
        ButtonType buttonRestart = new ButtonType("Restart");
        ButtonType buttonExit = new ButtonType("Exit");
        alert.getButtonTypes().setAll(buttonRestart, buttonExit);
        alert.setOnHidden(e -> {
            if (alert.getResult() == buttonRestart)
                restart();

            if (alert.getResult() == buttonExit)
                Platform.exit();
        });
        alert.show();
    }

}
