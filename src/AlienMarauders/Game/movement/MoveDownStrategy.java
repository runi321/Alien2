package AlienMarauders.Game.movement;

import java.util.ArrayList;
import AlienMarauders.Game.entities.Enemy;


public class MoveDownStrategy implements MovementStrategy {

    private double speedMultiplier = 1.0;

    @Override
    public void moveEnemy(Enemy enemy, double time) {
        double s = enemy.getSpeed() * speedMultiplier;
        double ny = enemy.getPositionY() + s * time;
        enemy.moveTo(enemy.getPositionX(), ny);
    }

    @Override
    public void moveEnemies(ArrayList<Enemy> enemies, double time) {
        for (Enemy e : enemies) {
            if (e.isAlive()) {
                moveEnemy(e, time);
            }
        }
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