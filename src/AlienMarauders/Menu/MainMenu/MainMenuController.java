package AlienMarauders.Menu.MainMenu;

import AlienMarauders.Controller;
import AlienMarauders.Model;
import javafx.scene.layout.Region;

public class MainMenuController {

    private final MainMenuModel model;
    private final MainMenuView view;
    private final Controller rootController;

    public MainMenuController(Model rootModel, Controller rootController) {
        this.model = new MainMenuModel(rootModel);
        this.rootController = rootController;

        this.view = new MainMenuView(
            this.model,
            () -> rootController.showGame(),        // Start game
            () -> rootController.showChat(),        // Chat
            () -> rootController.showSettings(),    // Settings
            rootController::exitApplication         // Exit
        );
    }

    public Region getView() {
        return view.getRoot();
    }
}