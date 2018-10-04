package com.codecool.snake.entities;

import com.codecool.snake.entities.powerups.GrowPowerup;
import com.codecool.snake.entities.powerups.HealthPowerup;
import javafx.scene.layout.Pane;

public class Stepper extends GameEntity implements Animatable {

    public static boolean healthPowerupExists;
    public static boolean growPowerupExists;
    private static int healthPowerupSpawn = 600;
    private static int growPowerupSpawn = 1200;

    public Stepper(Pane pane) {
        super(pane);
        healthPowerupExists = true;
        growPowerupExists = true;
    }

    @Override
    public void step() {
        /** Checks if healthPowerUp exists, if it's true, decrement spawn time by 1
         * if doesn't exists, create new HealthPowerup, set the spawn time to default */
        if (!healthPowerupExists) {
            healthPowerupSpawn--;
            if (healthPowerupSpawn <= 0) {
                new HealthPowerup(super.pane);
                healthPowerupSpawn = 600;
                healthPowerupExists = true;
            }
        }

        /** Checks if growPowerup exists, if it's true, decrement spawn time by 1
         * if doesn't exists, create new GrowPowerup, set spawn time to default */
        if (!growPowerupExists) {
            growPowerupSpawn--;
            if (growPowerupSpawn <= 0) {
                new GrowPowerup(super.pane);
                growPowerupSpawn = 1200;
                growPowerupExists = true;
            }
        }
    }

}
