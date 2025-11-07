package AlienMarauders.Game;

import AlienMarauders.Model;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class Gameview {

    private final BorderPane root;
    private final Button backBtn;

    public Gameview(Model model) {
        root = new BorderPane();
        bindBackground(root, model);

        Label placeholder = new Label("Game screen (placeholder)");
        placeholder.setStyle("-fx-font-size: 26px; -fx-text-fill: white;");

        BorderPane.setAlignment(placeholder, Pos.CENTER);
        root.setCenter(placeholder);

        backBtn = new Button("Main menu");
        BorderPane.setMargin(backBtn, new Insets(10));
        root.setBottom(backBtn);
    }

private void bindBackground(BorderPane node, Model model) {
    Runnable apply = () -> {
        String name = model.getBackgroundImage(); // "space.png" or "ufo.png"
        var url = getClass().getResource("/AlienMarauders/Myndir/" + name);
        node.setStyle(url != null
                ? "-fx-background-image: url('" + url.toExternalForm() + "'); -fx-background-size: cover;"
                : "-fx-background-color: #f7f7f7ff;");
    };
    model.backgroundImageProperty().addListener((o, a, b) -> apply.run());
    apply.run(); // initial background
}

    public BorderPane getRoot() {
        return root;
    }

    public Button getBackBtn() {
        return backBtn;
    }
}