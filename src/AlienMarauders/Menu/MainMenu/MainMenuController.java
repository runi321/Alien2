package AlienMarauders.Menu.MainMenu;

import AlienMarauders.Controller;
import AlienMarauders.Model;

public class MainMenuController {

    private final Model model;
    private final MainMenuView view;
    private final Controller rootController;

    public MainMenuController(Model model, MainMenuView view, Controller rootController) {
        this.model = model;
        this.view = view;
        this.rootController = rootController;

        attachHandlers();
    }

    private void attachHandlers() {
        view.getStartBtn().setOnAction(e -> rootController.showGame());
        view.getChatBtn().setOnAction(e -> rootController.showChat());
        view.getSettingsBtn().setOnAction(e -> rootController.showSettings());
        view.getExitBtn().setOnAction(e -> rootController.exitApplication());
    }
}