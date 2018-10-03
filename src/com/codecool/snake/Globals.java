package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// class for holding all static stuff
public class Globals {

    public static final double WINDOW_WIDTH = 1000;
    public static final double WINDOW_HEIGHT = 700;

    public static Image snakeHead = new Image("snake_head.png");
    public static Image snakeBody = new Image("snake_body.png");
    public static Image simpleEnemy = new Image("simple_enemy.png");
    public static Image powerupBerry = new Image("powerup_berry.png");
    public static Image greenHealth = new Image("green_bar.png");
    public static Image redHealth = new Image("red_bar.png");
    public static Image healthPowerup = new Image("health_powerup.png");
    //.. put here the other images you want to use
    public static boolean AkeyDown;
    public static boolean DkeyDown;
    public static boolean leftKeyDown;
    public static boolean rightKeyDown;
    public static List<GameEntity> gameObjects;
    public static List<GameEntity> newGameObjects; // Holds game objects crated in this frame.
    public static List<GameEntity> oldGameObjects; // Holds game objects that will be destroyed this frame.
    public static GameLoop gameLoop;

    static {
        gameObjects = new LinkedList<>();
        newGameObjects = new LinkedList<>();
        oldGameObjects = new LinkedList<>();
    }

    public static void addGameObject(GameEntity toAdd) {
        /** Add entities to newGameObjects - for example new parts of snakeBody */
        newGameObjects.add(toAdd);
    }

    public static void removeGameObject(GameEntity toRemove) {
        /** Add entities to oldGameObjects - for example destroyed entities (powerups, enemies) */
        oldGameObjects.add(toRemove);
    }

    public static List<GameEntity> getGameObjects() {
        /** Returns an unmodifiable List of the current active entities */
        return Collections.unmodifiableList(gameObjects);
    }

    public static GameEntity getGreenHealth(){
        /** Returns green Health bar */
        for(GameEntity entity : getGameObjects())
            if (entity.getImage().equals(greenHealth)) {
                return entity;
            }
        return null;
    }


}
