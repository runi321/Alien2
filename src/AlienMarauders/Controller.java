package AlienMarauders;

import AlienMarauders.Game.GameController;
import AlienMarauders.Menu.Chatmenu.ChatController;
import AlienMarauders.Menu.MainMenu.MainMenuController;
import AlienMarauders.Menu.Settingsmenu.SettingsController;

public class Controller {

    private final Model model;
    private final View view;

    private final MainMenuController mainMenuController;
    private final SettingsController settingsController;
    private final ChatController chatController;
    private final GameController gameController;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        // create subcontrollers and hand them "this" so they can navigate
        mainMenuController = new MainMenuController(model, view.getMainMenuView(), this);
        settingsController = new SettingsController(model, view.getSettingsView(), this);
        chatController = new ChatController(model, view.getChatView(), this);
        gameController = new GameController(model, view.getGameview(), this);
    }

    // navigation methods used by subcontrollers
    public void showMainMenu() {
        view.show(view.getMainMenuView().getRoot());
    }

    public void showSettings() {
        view.show(view.getSettingsView().getRoot());
    }

    public void showChat() {
        view.show(view.getChatView().getRoot());
    }

    public void showGame() {
        view.show(view.getGameview().getRoot());
    }

    public void exitApplication() {
        javafx.application.Platform.exit();
    }
}