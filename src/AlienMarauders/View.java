package AlienMarauders;

import AlienMarauders.Game.Gameview;
import AlienMarauders.Menu.Chatmenu.ChatView;
import AlienMarauders.Menu.MainMenu.MainMenuView;
import AlienMarauders.Menu.Settingsmenu.SettingsView;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

public class View {

    private final StackPane root;
    private final Scene scene;

    private final MainMenuView mainMenuView;
    private final SettingsView settingsView;
    private final ChatView chatView;
    private final Gameview gameview;

    public View(Model model) {
        root = new StackPane();
        scene = new Scene(root, 1000, 650);

        // create subviews
        mainMenuView = new MainMenuView(model);
        settingsView = new SettingsView(model);
        chatView = new ChatView(model);
        gameview = new Gameview(model);

        // add them all to the stack, will show/hide by toFront()
        root.getChildren().addAll(
                mainMenuView.getRoot(),
                settingsView.getRoot(),
                chatView.getRoot(),
                gameview.getRoot()
        );

        // by default they’re all there; controller decides which one is on top
    }

    public Scene getScene() {
        return scene;
    }

    public MainMenuView getMainMenuView() {
        return mainMenuView;
    }

    public SettingsView getSettingsView() {
        return settingsView;
    }

    public ChatView getChatView() {
        return chatView;
    }

    public Gameview getGameview() {
        return gameview;
    }

    /** Bring a node to the front */
    public void show(Node node) {
        root.getChildren().setAll(node);
    }

    /** root if someone needs it */
    public StackPane getRoot() {
        return root;
    }
}