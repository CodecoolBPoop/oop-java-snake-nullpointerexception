package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

import java.util.Random;

/* powerup that increase snakes health by 10 points */
public class HealthPowerup extends GameEntity implements Interactable {

    public HealthPowerup(Pane pane) {
        super(pane);
        setImage(Globals.healthPowerup);
        pane.getChildren().add(this);

        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
    }

    @Override
    public void apply(SnakeHead snakeHead){
        if (snakeHead.health != 100) {
            snakeHead.changeHealth(10);
        }
        destroy();
        int counter = 0;
/*
        while (Globals.steps % 600 != 0) {
            continue;
        }
*/
        new HealthPowerup(super.pane);
    }

    @Override
    public String getMessage(){
        return "Got +10 health";
    }
}
