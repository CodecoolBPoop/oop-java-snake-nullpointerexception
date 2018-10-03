package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;

// a simple enemy TODO make better ones.
public class SimpleEnemy extends GameEntity implements Animatable, Interactable {

    private Point2D heading;
    private static final int damage = 10;

    public SimpleEnemy(Pane pane) {
        super(pane);

        setImage(Globals.simpleEnemy);
        pane.getChildren().add(this);
        int speed = 1;
        Random rnd = new Random();
        placeEnemy(rnd);

        double direction = rnd.nextDouble() * 360;
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);
    }

    private void placeEnemy(Random rnd) {
        // get sneakhead from pane's nodes
        SnakeHead player = (SnakeHead) pane.getChildren().stream()
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
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(SnakeHead player) {
        player.changeHealth(-damage);
        destroy();
    }

    @Override
    public String getMessage() {
        return "10 damage";
    }
}
