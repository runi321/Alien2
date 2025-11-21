package AlienMarauders.Game.formation;
import AlienMarauders.Game.entities.Enemy;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class RowFormation implements Formation {

    private final ArrayList<Enemy> enemies = new ArrayList<>();
    private final Image spriteSheet;

    public RowFormation(Image spriteSheet) {
        this.spriteSheet = spriteSheet;
    }

    @Override
    public void createEnemies() {
        enemies.clear();

        int cols = 6;
        double startX = 60;
        double spacingX = 80;
        double y = 60;

        for (int i = 0; i < cols; i++) {
            double x = startX + i * spacingX;
            enemies.add(new Enemy(x, y, 40, 40, spriteSheet, 2));
        }
    }

    @Override
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
}