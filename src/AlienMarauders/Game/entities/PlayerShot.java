package AlienMarauders.Game.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PlayerShot extends Entity {

    private static final double SHOT_SPEED = 0.6; // pixels per ms upwards

    public PlayerShot(double x, double y) {
        super(x, y, 4, 12, null);
    }

    @Override
    public void move(double timeMillis) {
        double ny = posY - SHOT_SPEED * timeMillis;
        moveTo(posX, ny);
    }

    @Override
    public void render(GraphicsContext gc) {
        if (!alive) return;
        gc.setFill(Color.YELLOW);
        gc.fillRect(posX, posY, width, height);
    }
}
