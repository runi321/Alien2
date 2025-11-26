package AlienMarauders.Menu.Settingsmenu;

import AlienMarauders.Controller;
import AlienMarauders.Model;
import javafx.scene.layout.Region;

public class SettingsController {

    private final SettingsModel model;
    private final SettingsView view;
    private final Controller rootController;

    public SettingsController(Model rootModel, Controller rootController) {
        this.model = new SettingsModel(rootModel);
        this.rootController = rootController;

        this.view = new SettingsView(
            this.model,
            () -> this.model.setBackgroundImage("space.png"),
            () -> this.model.setBackgroundImage("ufo.png"),
            () -> this.model.setDifficulty(Model.Difficulty.EASY),
            () -> this.model.setDifficulty(Model.Difficulty.MEDIUM),
            () -> this.model.setDifficulty(Model.Difficulty.HARD),
            rootController::showMainMenu
        );
    }

    public Region getView() {
        return view.getRoot();
    }
}