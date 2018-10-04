package com.codecool.snake.entities.snakes;

import com.codecool.snake.Game;
import com.codecool.snake.entities.*;
import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import javax.rmi.CORBA.Util;

public class SnakeHead extends GameEntity implements Animatable {

    private static final float speed = 2;
    private static final float turnRate = 2;
    private GameEntity tail; // the last element. Needed to know where to add the next part.
    public int health;
    public double dir;

    public SnakeHead(Pane pane, int xc, int yc) {
        /** Calls GameEntity constructor, sets coordinates, changes health to 100, adds tail to the head,
         * sets the image, adds the image to the Scene and adds 4 parts to the snakehead */
        super(pane);
        setX(xc);
        setY(yc);
        health = 100;
        tail = this;
        setImage(Globals.snakeHead);
        pane.getChildren().add(this);

        addPart(4);
    }

    public void step() {
        this.dir = getRotate();
        if (Globals.leftKeyDown) {
            this.dir = dir - turnRate;
        }
        if (Globals.rightKeyDown) {
            this.dir = dir + turnRate;
        }
        // set rotation and position
        setRotate(dir);
        Point2D heading = Utils.directionToVector(dir, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

        // check if collided with an enemy or a powerup
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof Interactable) {
                    Interactable interactable = (Interactable) entity;
                    interactable.apply(this);
                    System.out.println(interactable.getMessage());
                }
            }
        }

        // check for game over condition
        if (isOutOfBounds() || health <= 0) {
            Globals.game.gameOver(Globals.getSnakeBodies());
            System.out.println("Game Over");
            Globals.gameLoop.stop();
        }
    }

    public void addPart(int numParts) {
        for (int i = 0; i < numParts; i++) {
            SnakeBody newPart = new SnakeBody(pane, tail);
            tail = newPart;
        }
    }

    public void changeHealth(int diff) {
        health += diff;
        Globals.getGreenHealth().setFitWidth(health > 0 ? health : 1);
    }

    public int getHealth() {
        return health;
    }

    public double getDir() {
        return dir;
    }
}
