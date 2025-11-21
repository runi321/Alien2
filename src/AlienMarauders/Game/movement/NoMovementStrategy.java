package AlienMarauders.Game.movement;

import java.util.ArrayList;
import AlienMarauders.Game.entities.Enemy;

public class NoMovementStrategy implements MovementStrategy {

    private double speedMultiplier = 1.0;

    @Override
    public void moveEnemy(Enemy enemy, double time) {
        // no movement
    }

    @Override
    public void moveEnemies(ArrayList<Enemy> enemies, double time) {
        // no movement
    }

    @Override
    public void setSpeedMultiplier(double speedMultiplier) {
        this.speedMultiplier = speedMultiplier;
    }

    @Override
    public double getSpeedMultiplier() {
        return speedMultiplier;
    }
}