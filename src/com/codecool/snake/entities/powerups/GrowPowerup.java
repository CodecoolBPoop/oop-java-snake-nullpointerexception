package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.Stepper;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

import java.util.Random;

public class GrowPowerup extends GameEntity implements Interactable {

    public GrowPowerup(Pane pane) {
        super(pane);
        setImage(Globals.growPowerup);
        pane.getChildren().add(this);

        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
    }

    @Override
    public void apply(SnakeHead snakeHead){
        snakeHead.addPart(8);
        destroy();
        Stepper.growPowerupExists = false;
    }

    @Override
    public String getMessage(){
        return "Your snake got longer ;) ";
    }
}
