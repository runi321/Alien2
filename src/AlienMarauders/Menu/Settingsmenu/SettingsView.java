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

public class SettingsView {

    private final BorderPane root;
    private final MenuItem backgroundSpace;
    private final MenuItem backgroundUfo;

    private final MenuItem difficultyEasy;
    private final MenuItem difficultyMedium;
    private final MenuItem difficultyHard;

    private final Button backBtn;

    public SettingsView(Model model) {
        root = new BorderPane();
        bindBackground(root, model);

        Label header = new Label("Settings");
        header.setStyle("-fx-font-size: 28px; -fx-text-fill: white;");

        // menus
        Menu backgroundMenu = new Menu("Background");
        backgroundSpace = new MenuItem("Space");
        backgroundUfo = new MenuItem("UFO");
        backgroundMenu.getItems().addAll(backgroundSpace, backgroundUfo);

        Menu difficultyMenu = new Menu("Difficulty");
        difficultyEasy = new MenuItem("Easy");
        difficultyMedium = new MenuItem("Medium");
        difficultyHard = new MenuItem("Hard");
        difficultyMenu.getItems().addAll(difficultyEasy, difficultyMedium, difficultyHard);

        MenuBar bar = new MenuBar(backgroundMenu, difficultyMenu);

        VBox topBox = new VBox(10, header, bar);
        topBox.setPadding(new Insets(20));
        topBox.setAlignment(Pos.CENTER_LEFT);

        backBtn = new Button("Back to main menu");
        backBtn.setPadding(new Insets(10, 20, 10, 20));

        VBox bottomBox = new VBox(backBtn);
        bottomBox.setPadding(new Insets(20));
        bottomBox.setAlignment(Pos.CENTER_LEFT);

        root.setTop(topBox);
        root.setBottom(bottomBox);
    }

private void bindBackground(BorderPane node, Model model) {
    Runnable apply = () -> {
        String name = model.getBackgroundImage();                  // e.g. "space.png" / "ufo.png"
        var url = getClass().getResource("/AlienMarauders/Myndir/" + name);
        node.setStyle(url != null
            ? "-fx-background-image: url('" + url.toExternalForm() + "'); -fx-background-size: cover;"
            : "-fx-background-color: #111;");
    };
    model.backgroundImageProperty().addListener((o, a, b) -> apply.run());
    apply.run();                                                  // initial background
}

    public BorderPane getRoot() {
        return root;
    }

    public MenuItem getBackgroundSpace() {
        return backgroundSpace;
    }

    public MenuItem getBackgroundUfo() {
        return backgroundUfo;
    }

    public MenuItem getDifficultyEasy() {
        return difficultyEasy;
    }

    public MenuItem getDifficultyMedium() {
        return difficultyMedium;
    }

    public MenuItem getDifficultyHard() {
        return difficultyHard;
    }

    public Button getBackBtn() {
        return backBtn;
    }
}