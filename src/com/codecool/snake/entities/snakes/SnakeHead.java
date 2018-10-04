package com.codecool.snake.entities.snakes;

import com.codecool.snake.Game;
import com.codecool.snake.GameLoop;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.HealthBar;
import com.codecool.snake.entities.Interactable;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public class SnakeHead extends GameEntity implements Animatable {

    private boolean isSecondSnake = false;
    private static final float speed = 2;
    private static final float turnRate = 2;
    private GameEntity tail; // the last element. Needed to know where to add the next part.
    public int health;

    public SnakeHead(Pane pane, int xc, int yc) {
        /** Calls GameEntity constructor, sets coordinates, changes health to 100, adds tail to the head,
         * sets the image, adds the image to the Scene and adds 4 parts to the snakehead */
        super(pane);
        if (pane.getChildren().stream().anyMatch(entity -> entity instanceof SnakeHead))
            isSecondSnake = true;
        setX(xc);
        setY(yc);
        health = 100;
        tail = this;
        setImage(Globals.snakeHead);
        pane.getChildren().add(this);

        addPart(4);
    }

    public void step() {
        double dir = getRotate();
        if (isSecondSnake ? Globals.AkeyDown : Globals.leftKeyDown) {
            dir = dir - turnRate;
        }
        if (isSecondSnake ? Globals.DkeyDown : Globals.rightKeyDown) {
            dir = dir + turnRate;
        }
        // set rotation and position
        setRotate(dir);
        Point2D heading = Utils.directionToVector(dir, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

        // check if collided with an enemy or a powerup
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof SnakeBody && !((SnakeBody) entity).snakeHead.equals(this))
                    killSnake();
                if (entity instanceof Interactable) {
                    Interactable interactable = (Interactable) entity;
                    interactable.apply(this);
                    System.out.println(interactable.getMessage());
                }
            }
        }

        // check for game over condition
        if (isOutOfBounds() || health <= 0) {
            if (Globals.getGameObjects().stream().filter(entity -> entity instanceof SnakeHead).count() == 2)
                killSnake();
            else {
                Globals.gameLoop.stop();
                Globals.game.gameOver(Globals.getSnakeBodies());
            }

        }
    }

    public void addPart(int numParts) {
        for (int i = 0; i < numParts; i++) {
            SnakeBody newPart = new SnakeBody(pane, tail);
            newPart.snakeHead = this;
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

    private void killSnake() {
        if (Globals.oldGameObjects.stream().filter(ent -> ent instanceof SnakeHead).count() == 1) {
            return;
        }
        destroy();
        for (GameEntity entity: Globals.getGameObjects())
            if (entity instanceof SnakeBody)
                if (((SnakeBody) entity).snakeHead.equals(this))
                    entity.destroy();
    }

}
