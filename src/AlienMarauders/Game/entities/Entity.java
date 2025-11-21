package AlienMarauders.Game.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Entity {

    protected double posX;
    protected double posY;
    protected double width;
    protected double height;
    protected Image image;
    protected boolean alive = true;

    protected Entity(double posX, double posY, double width, double height, Image image) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.image = image;
    }

    public abstract void move(double timeMillis);

    public void render(GraphicsContext gc) {
        if (image != null && alive) {
            gc.drawImage(image, posX, posY, width, height);
        }
    }

    public void moveTo(double x, double y) {
        this.posX = x;
        this.posY = y;
    }

    public double getPositionX() { return posX; }
    public double getPositionY() { return posY; }
    public double getWidth()     { return width; }
    public double getHeight()    { return height; }

    public boolean isAlive() { return alive; }
    public void kill() { alive = false; }
}