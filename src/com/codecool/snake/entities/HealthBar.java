package com.codecool.snake.entities;

import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;


public class HealthBar extends GameEntity {

    private SnakeHead snakeHead;

    public SnakeHead getSnakeHead() { return snakeHead; }

    /** HealthBar objects can be created at start in 2 colors
    (png-s in resource directory, must be the same coordinate to overlap and green on the top to work */
    public HealthBar(Pane pane, int xc, int xy, Image barImage, SnakeHead player) {
        super(pane);
        snakeHead = player;
        setX(xc);
        setY(xy);
        setImage(barImage);
        pane.getChildren().add(this);
    }


}
