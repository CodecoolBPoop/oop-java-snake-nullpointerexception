package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;

// a simple enemy TODO make better ones.
public abstract class Enemy extends GameEntity implements Animatable, Interactable {

    private Point2D heading;
    private static final int damage = 10;

    public Enemy(Pane pane) {
        super(pane);
        pane.getChildren().add(this);
        placeEnemy();

    }

    protected void placeEnemy() {
        Random rnd = new Random();
        GameEntity player = Globals.newGameObjects.stream()
                .filter(entity -> entity instanceof SnakeHead).findFirst().get();

        double Xcoord = rnd.nextDouble() * Globals.WINDOW_WIDTH;
        double Ycoord = rnd.nextDouble() * Globals.WINDOW_HEIGHT;
        // doesn't spawn near player
        while (Xcoord < player.getX() + 100 && Xcoord > player.getX() - 100
                && Ycoord < player.getY() + 100 && Ycoord > player.getY() - 100) {
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