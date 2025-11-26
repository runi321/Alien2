package AlienMarauders;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Region;

public class View {

    private final StackPane root;
    private final Scene scene;

    public View() {
        root = new StackPane();
        scene = new Scene(root, 1000, 650);
    }

    public Scene getScene() {
        return scene;
    }

    public void show(Region subViewRoot) {
        root.getChildren().setAll(subViewRoot);
    }
}