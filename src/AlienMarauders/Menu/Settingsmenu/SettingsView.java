package AlienMarauders.Menu.Settingsmenu;

import AlienMarauders.Model;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;

public class SettingsView {

    private final BorderPane root;

    public SettingsView(
        SettingsModel model,
        Runnable onBackgroundSpace,
        Runnable onBackgroundUfo,
        Runnable onDifficultyEasy,
        Runnable onDifficultyMedium,
        Runnable onDifficultyHard,
        Runnable onBack
    ) {
        root = new BorderPane();

        // background binding
        bindBackground(root, model);

        // UI header
        Label header = new Label("Settings");
        header.setStyle("-fx-font-size: 28px; -fx-text-fill: white;");

        // Background menu
        Menu backgroundMenu = new Menu("Background");
        MenuItem space = new MenuItem("Space");
        MenuItem ufo   = new MenuItem("UFO");

        // UI element wires to logic
        space.setOnAction(e -> onBackgroundSpace.run());
        ufo.setOnAction(e -> onBackgroundUfo.run());

        backgroundMenu.getItems().addAll(space, ufo);

        // Difficulty menu
        Menu difficultyMenu = new Menu("Difficulty");
        MenuItem easy   = new MenuItem("Easy");
        MenuItem medium = new MenuItem("Medium");
        MenuItem hard   = new MenuItem("Hard");

        // attach Runnables
        easy.setOnAction(e -> onDifficultyEasy.run());
        medium.setOnAction(e -> onDifficultyMedium.run());
        hard.setOnAction(e -> onDifficultyHard.run());

        difficultyMenu.getItems().addAll(easy, medium, hard);

        // Menu bar
        MenuBar bar = new MenuBar(backgroundMenu, difficultyMenu);

        // Top layout
        VBox topBox = new VBox(10, header, bar);
        topBox.setPadding(new Insets(20));
        topBox.setAlignment(Pos.CENTER_LEFT);

        // Back button
        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> onBack.run());

        VBox bottomBox = new VBox(backBtn);
        bottomBox.setPadding(new Insets(20));
        bottomBox.setAlignment(Pos.CENTER_LEFT);

        // assign to layout
        root.setTop(topBox);
        root.setBottom(bottomBox);
    }

    private void bindBackground(BorderPane node, SettingsModel model) {
        Runnable apply = () -> {
            String name = model.getBackgroundImage();
            var url = getClass().getResource("/AlienMarauders/Myndir/" + name);
            if (url != null) {
                node.setStyle(
                    "-fx-background-image: url('" + url.toExternalForm() + "');" +
                    "-fx-background-size: cover;"
                );
            } else {
                node.setStyle("-fx-background-color: #111;");
            }
        };
        model.backgroundImageProperty().addListener((obs, oldV, newV) -> apply.run());
        apply.run();
    }

    public Region getRoot() {
        return root;
    }
}