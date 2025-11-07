package AlienMarauders.Menu.Settingsmenu;

import AlienMarauders.Controller;
import AlienMarauders.Model;

public class SettingsController {

    private final Model model;
    private final SettingsView view;
    private final Controller rootController;

    public SettingsController(Model model, SettingsView view, Controller rootController) {
        this.model = model;
        this.view = view;
        this.rootController = rootController;

        attachHandlers();
    }

    private void attachHandlers() {
        // background
        view.getBackgroundSpace().setOnAction(e ->
                model.setBackgroundImage("space.png")
        );
        view.getBackgroundUfo().setOnAction(e ->
                model.setBackgroundImage("ufo.png")
        );


        

        // difficulty
        view.getDifficultyEasy().setOnAction(e ->
                model.setDifficulty(Model.Difficulty.EASY)
        );
        view.getDifficultyMedium().setOnAction(e ->
                model.setDifficulty(Model.Difficulty.MEDIUM)
        );
        view.getDifficultyHard().setOnAction(e ->
                model.setDifficulty(Model.Difficulty.HARD)
        );

        // back
        view.getBackBtn().setOnAction(e -> rootController.showMainMenu());
    }
}