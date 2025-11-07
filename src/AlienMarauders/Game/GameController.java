package AlienMarauders.Game;

import AlienMarauders.Controller;
import AlienMarauders.Model;

public class GameController {

    private final Model model;
    private final Gameview view;
    private final Controller rootController;

    public GameController(Model model, Gameview view, Controller rootController) {
        this.model = model;
        this.view = view;
        this.rootController = rootController;

        attachHandlers();
    }

    private void attachHandlers() {
        view.getBackBtn().setOnAction(e -> rootController.showMainMenu());
    }
}