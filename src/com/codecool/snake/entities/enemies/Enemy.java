package com.codecool.snake.entities.enemies;

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

// a simple enemy TODO make better ones.
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

        GameEntity player = currentlyUsedEntityList.stream()
                .filter(entity -> entity instanceof SnakeHead).findFirst().get();

        double Xcoord = rnd.nextDouble() * Globals.WINDOW_WIDTH;
        double Ycoord = rnd.nextDouble() * Globals.WINDOW_HEIGHT;
        // doesn't spawn near player
        while (Xcoord < player.getX() + 180 && Xcoord > player.getX() - 180
                && Ycoord < player.getY() + 180 && Ycoord > player.getY() - 180) {
            Xcoord = rnd.nextDouble() * Globals.WINDOW_WIDTH;
            Ycoord = rnd.nextDouble() * Globals.WINDOW_HEIGHT;
        }
        setX(Xcoord);
        setY(Ycoord);
    }

    @Override
    public abstract void step();

    @Override
    public abstract void apply(SnakeHead player);

    @Override
    public abstract String getMessage();
}