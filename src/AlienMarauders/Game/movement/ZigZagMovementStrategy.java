package AlienMarauders.Game.movement;

import java.util.ArrayList;
import AlienMarauders.Game.entities.Enemy;

public class ZigZagMovementStrategy implements MovementStrategy {

    private double speedMultiplier = 1.0;
    private double totalElapsedTime = 0.0;     // T

    private static final double AMPLITUDE = 75.0; // a
    private static final double OMEGA     = 0.0001; // ω

    @Override
    public void moveEnemy(Enemy enemy, double time) {
        // we just use moveEnemies
    }

    @Override
    public void moveEnemies(ArrayList<Enemy> enemies, double time) {
        totalElapsedTime += time;
        double T = totalElapsedTime;
        double angle = 2.0 * Math.PI * OMEGA * T;

        for (Enemy e : enemies) {
            if (!e.isAlive()) continue;

            double s = e.getSpeed() * speedMultiplier;

            double x0 = e.getPosX0();
            double xi = x0 + AMPLITUDE * Math.sin(angle);
            double yi = e.getPositionY() + s * time;

            e.moveTo(xi, yi);
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