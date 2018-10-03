package com.codecool.snake.entities;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;


public class HealthBar extends GameEntity {

    /* HealthBar objects can be created at start in 2 colors
    (png-s in resource directory, must be the same coordinate to overlap and green on the top to work */
    public HealthBar(Pane pane, int xc, int xy, Image barImage) {
        super(pane);
        setX(xc);
        setY(xy);
        setImage(barImage);
        pane.getChildren().add(this);
    }


}
