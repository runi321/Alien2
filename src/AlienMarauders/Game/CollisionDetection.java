package AlienMarauders.Game;
import AlienMarauders.Game.entities.Enemy;
import AlienMarauders.Game.entities.Entity;

public class CollisionDetection {

    public static boolean Aabb(Entity a, Entity b) {
        return a.getPositionX() + a.getWidth() > b.getPositionX() &&
               b.getPositionX() + b.getWidth() > a.getPositionX() &&
               a.getPositionY() + a.getHeight() > b.getPositionY() &&
               b.getPositionY() + b.getHeight() > a.getPositionY();
    }
}