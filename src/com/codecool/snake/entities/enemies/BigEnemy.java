package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;

public class BigEnemy extends Enemy implements Animatable, Interactable {

    private Point2D heading;
    private static final int damage = 40;

    public BigEnemy(Pane pane) {
        super(pane);
        setImage(Globals.bigEnemy);
        setDirection();
        placeEnemy();
    }

    private void setDirection() {
        int speed = 1;
        double direction = new Random().nextDouble() * 360;
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);
    }

    public void step(){
        if (isOutOfBounds()) {
            destroy();
            new BigEnemy(super.pane);
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
        return "40 damage";
    }

}
