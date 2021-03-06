package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.HealthBar;
import com.codecool.snake.entities.Laser;
import com.codecool.snake.entities.snakes.SnakeBody;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

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
    public static Image bigEnemy = new Image("big_enemy.png");
    public static Image powerupBerry = new Image("powerup_berry.png");
    public static Image greenHealth = new Image("green_bar.png");
    public static Image redHealth = new Image("red_bar.png");
    public static Image healthPowerup = new Image("health_powerup.png");
    public static Image growPowerup = new Image("grow_powerup.png");
    public static Image laser = new Image("laser.png");
    //.. put here the other images you want to use
    public static boolean AkeyDown;
    public static boolean DkeyDown;
    public static boolean shiftDown;
    public static boolean leftKeyDown;
    public static boolean rightKeyDown;
    public static List<GameEntity> gameObjects;
    public static List<GameEntity> newGameObjects; // Holds game objects crated in this frame.
    public static List<GameEntity> oldGameObjects; // Holds game objects that will be destroyed this frame.
    public static GameLoop gameLoop;
    public static Game game;
    public static BackgroundImage background = new BackgroundImage(new Image("space.jpg"), BackgroundRepeat.NO_REPEAT,
                                                                    BackgroundRepeat.NO_REPEAT,
                                                                    BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

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

    public static GameEntity getGreenHealth(SnakeHead sneak){
        /** Returns green Health bar */
        for(GameEntity entity : getGameObjects())
            if (entity.getImage().equals(greenHealth))
                if (((HealthBar) entity).getSnakeHead().equals(sneak))
                    return entity;
        return null;
    }

    public static GameEntity getSnakeHead(){
        for(GameEntity entity : getGameObjects()){
            if(entity instanceof SnakeHead){
                return entity;
            }
        }
        return null;
    }

    public static GameEntity getLaser(){
        for(GameEntity entity : getGameObjects()){
            if(entity instanceof Laser){
                return entity;
            }
        }
        return null;
    }

    public static int getSnakeBodies() {
        /** Iterates over the entities, check if actual entity is snakeBody, if it is, increment score */
        int score = 0;
        for (GameEntity entity : gameObjects) {
            if (entity instanceof SnakeBody) {
                score++;
            }
        }
        return score;
    }
}
