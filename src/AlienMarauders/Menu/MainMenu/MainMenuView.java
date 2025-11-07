package AlienMarauders.Menu.MainMenu;

import AlienMarauders.Model;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.Region;

public class MainMenuView {

    private final VBox root;
    private final Button startBtn;
    private final Button chatBtn;
    private final Button settingsBtn;
    private final Button exitBtn;

    public MainMenuView(Model model) {
        root = new VBox(20);
        root.setPadding(new Insets(40));
        root.setAlignment(Pos.CENTER);

        // bind background to model
        bindBackground(root, model);

        Text title = new Text("Alien Marauders");
        title.setStyle("-fx-font-size: 42px; -fx-fill: white;");
        
        startBtn = new Button("Start game");
        chatBtn = new Button("Chat");
        settingsBtn = new Button("Settings");
        exitBtn = new Button("Exit");

        startBtn.setMinWidth(180);
        chatBtn.setMinWidth(180);
        settingsBtn.setMinWidth(180);
        exitBtn.setMinWidth(180);

        root.getChildren().addAll(title, startBtn, chatBtn, settingsBtn, exitBtn);
    }

private void bindBackground(Region node, Model model) {
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

    public VBox getRoot() {
        return root;
    }

    public Button getStartBtn() {
        return startBtn;
    }

    public Button getChatBtn() {
        return chatBtn;
    }

    public Button getSettingsBtn() {
        return settingsBtn;
    }

    public Button getExitBtn() {
        return exitBtn;
    }
}