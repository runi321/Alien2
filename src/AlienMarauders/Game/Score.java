package AlienMarauders.Game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Score {
    int score;

    public Score() {
        score = 0;
    }

    public void updateScore(int amount) {
        score += amount;
    }

    public void resetScore() {
        score = 0;
    }

    public void render(GraphicsContext gc) {
        gc.setFont(new Font(22));
        gc.setFill(Color.RED);
        gc.fillText("Score: " + score,
                Gameview.WIDTH * 0.025,
                Gameview.HEIGHT * 0.975);
    }
}