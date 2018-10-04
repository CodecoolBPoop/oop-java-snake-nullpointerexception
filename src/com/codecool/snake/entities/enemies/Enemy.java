package com.codecool.snake.entities.enemies;

import com.codecool.snake.Game;
import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

abstract class Enemy extends GameEntity implements Animatable, Interactable {

    Enemy(Pane pane) {
        super(pane);
        pane.getChildren().add(this);
        placeEnemy();
    }

     void placeEnemy() {
        Random rnd = new Random();
        // at the start of the game sneakhead can only be get from newGameobjects
        List<GameEntity> currentlyUsedEntityList = Globals.gameObjects.isEmpty() ?
                Globals.newGameObjects : Globals.gameObjects;

        // doesn't spawn near player
        double Ycoord = rnd.nextDouble() * Globals.WINDOW_HEIGHT;
        double Xcoord = rnd.nextDouble() * Globals.WINDOW_WIDTH;
        while (isOnEntity(Xcoord, Ycoord, currentlyUsedEntityList)) {
            Ycoord = rnd.nextDouble() * Globals.WINDOW_HEIGHT;
            Xcoord = rnd.nextDouble() * Globals.WINDOW_WIDTH;
        }
        setX(Xcoord);
        setY(Ycoord);
    }

    private boolean isOnEntity(double Xcoord, double Ycoord, List<GameEntity> entities) {
        int distanceToKeep = 180;
        for (GameEntity entity: entities) {
            if (Xcoord < entity.getX() + distanceToKeep && Xcoord > entity.getX() - distanceToKeep
                    && Ycoord < entity.getY() + distanceToKeep && Ycoord > entity.getY() - distanceToKeep)
                return true;
        }
        return false;
    }

    @Override
    public abstract void step();

    @Override
    public abstract void apply(SnakeHead player);

    @Override
    public abstract String getMessage();
}