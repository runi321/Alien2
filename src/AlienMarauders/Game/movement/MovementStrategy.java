package AlienMarauders.Game.movement;

import java.util.ArrayList;
import AlienMarauders.Game.entities.Enemy;

public interface MovementStrategy {
    void moveEnemy(Enemy enemy, double time);
    void moveEnemies(ArrayList<Enemy> enemies, double time);
    void setSpeedMultiplier(double speedMultiplier);
    double getSpeedMultiplier();
}