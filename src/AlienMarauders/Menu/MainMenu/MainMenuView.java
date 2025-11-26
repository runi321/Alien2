package AlienMarauders.Menu.MainMenu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;

public class MainMenuView {

    private final BorderPane root;

    public MainMenuView(
        MainMenuModel model,
        Runnable onStartGame,
        Runnable onChat,
        Runnable onSettings,
        Runnable onExit
    ) {
        root = new BorderPane();

        bindBackground(root, model);

        Label title = new Label("Alien Marauders");
        title.setStyle("-fx-font-size: 36px; -fx-text-fill: white;");

        Button startBtn    = new Button("Start game");
        Button chatBtn     = new Button("Chat");
        Button settingsBtn = new Button("Settings");
        Button exitBtn     = new Button("Exit");

        startBtn.setOnAction(e -> onStartGame.run());
        chatBtn.setOnAction(e -> onChat.run());
        settingsBtn.setOnAction(e -> onSettings.run());
        exitBtn.setOnAction(e -> onExit.run());

        startBtn.setMaxWidth(Double.MAX_VALUE);
        chatBtn.setMaxWidth(Double.MAX_VALUE);
        settingsBtn.setMaxWidth(Double.MAX_VALUE);
        exitBtn.setMaxWidth(Double.MAX_VALUE);

        VBox center = new VBox(15, title, startBtn, chatBtn, settingsBtn, exitBtn);
        center.setAlignment(Pos.CENTER);
        center.setPadding(new Insets(40));

        root.setCenter(center);
    }

    private void bindBackground(BorderPane node, MainMenuModel model) {
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