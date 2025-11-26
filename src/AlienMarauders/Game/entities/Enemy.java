package AlienMarauders.Game.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public class Enemy extends Entity {

    
    // this is used for animation purposes.
    private Image[] images;
    private int numImages;

    // Initial position of the enemy
    private double posX0;
    private double posY0;

    // Find appropriate hp and speed
    private int hitPoints = 3;
    private double speed = 0.035;

    public Enemy(double posX, double posY,
                 double width, double height,
                 Image spriteSheet, int numImages) {
        super(posX, posY, width, height, null);
        this.posX0 = posX;
        this.posY0 = posY;
        this.numImages = numImages;
        this.images = new Image[numImages];
        loadSprites(spriteSheet);
        this.image = images[0]; // first frame for now
    }

    @Override
    public void move(double timeMillis) {
        double ny = posY + speed * timeMillis;
        moveTo(posX, ny);
    }

    public void takeDamage(int amount) {
        hitPoints -= amount;
        if (hitPoints <= 0) {
            alive = false;
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        super.render(gc);
    }

    private void loadSprites(Image spriteSheet) {
        int w = (int) (spriteSheet.getWidth() / numImages);
        int h = (int) spriteSheet.getHeight();
        PixelReader pixelReader = spriteSheet.getPixelReader();

        for (int i = 0; i < numImages; i++) {
            WritableImage imageSection = new WritableImage(w, h);
            PixelWriter pixelWriter = imageSection.getPixelWriter();
            for (int y = 0; y < h; y++) {
                int offset = i * w;
                for (int x = offset; x < offset + w; x++) {
                    pixelWriter.setColor(
                            x - offset, y,
                            pixelReader.getColor(x, y));
                }
            }
            images[i] = imageSection;
        }
    }

    public double getPosX0() { return posX0; }
    public double getPosY0() { return posY0; }
    public double getSpeed() { return speed; }
}