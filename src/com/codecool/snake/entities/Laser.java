package com.codecool.snake.entities;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public class Laser  extends GameEntity implements Animatable {

    private static int speed = 6;
    public Point2D heading;

    public Laser(Pane pane) {
        super(pane);
        setImage(Globals.laser);
        pane.getChildren().add(this);

        SnakeHead snake = (SnakeHead) Globals.getSnakeHead();
        setX(snake.getX());
        setY(snake.getY());
        this.heading = Utils.directionToVector(snake.getDir(), speed);
    }

    public void setHeading(Point2D heading) {
        this.heading = heading;
    }

    public static int getSpeed() {
        return speed;
    }

    public void step(){
        if (isOutOfBounds()) {
            destroy();
            Stepper.laserExists = false;
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof SimpleEnemy) {
                     Interactable enemy = (Interactable) entity;
                    enemy.apply((SnakeHead) Globals.getSnakeHead());
                    destroy();
                    Stepper.laserExists = false;
                    System.out.println("I shot the sheriff");
                }
            }
        }
    }




}
