package AlienMarauders.Game;

import AlienMarauders.Model;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class Gameview {

    public static final double WIDTH = 600;
    public static final double HEIGHT = 600;

    private final StackPane root;
    private final BorderPane layout;
    private final Canvas canvas;
    private final Button backBtn;

    public Gameview(Model model) {
        root = new StackPane();
        layout = new BorderPane();
        canvas = new Canvas(WIDTH, HEIGHT);

        bindBackground(root, model);

        layout.setCenter(canvas);
        StackPane.setAlignment(layout, Pos.CENTER);
        StackPane.setMargin(layout, new Insets(10));

        backBtn = new Button("Main menu");
        BorderPane.setMargin(backBtn, new Insets(10));
        layout.setBottom(backBtn);

        root.getChildren().add(layout);
    }

    private void bindBackground(StackPane node, Model model) {
        Runnable apply = () -> {
            String name = model.getBackgroundImage();
            var url = getClass().getResource("/AlienMarauders/Myndir/" + name);
            node.setStyle(url != null
                    ? "-fx-background-image: url('" + url.toExternalForm() + "'); -fx-background-size: cover;"
                    : "-fx-background-color: #000000;");
        };
        model.backgroundImageProperty().addListener((o, a, b) -> apply.run());
        apply.run();
    }

    public StackPane getRoot() {
        return root;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public GraphicsContext getGraphicsContext() {
        return canvas.getGraphicsContext2D();
    }

    public Button getBackBtn() {
        return backBtn;
    }
}