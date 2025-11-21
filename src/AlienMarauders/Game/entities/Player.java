package AlienMarauders.Game.entities;

import AlienMarauders.Game.Gameview;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player extends Entity {

    private double velocityX;
    private double velocityY;

    private double speed = 0.4; // tweak to taste

    private double moveLeft  = 0;
    private double moveRight = 0;
    private double moveUp    = 0;
    private double moveDown  = 0;

    public Player(double x, double y, double width, double height, Image image) {
        super(x, y, width, height, image);
    }

    @Override
    public void move(double timeMillis) {
        velocityX = moveLeft + moveRight;
        velocityY = moveUp + moveDown;

        double nx = posX + velocityX * timeMillis;
        double ny = posY + velocityY * timeMillis;

        nx = Math.max(0, Math.min(Gameview.WIDTH  - width, nx));
        ny = Math.max(0, Math.min(Gameview.HEIGHT - height, ny));

        moveTo(nx, ny);
    }

    public void movingLeft(boolean val) {
        moveLeft = val ? -speed : 0;
    }

    public void movingRight(boolean val) {
        moveRight = val ? speed : 0;
    }

    public void movingUp(boolean val) {
        moveUp = val ? -speed : 0;
    }

    public void movingDown(boolean val) {
        moveDown = val ? speed : 0;
    }

    @Override
    public void render(GraphicsContext gc) {
        super.render(gc);
    }
}
