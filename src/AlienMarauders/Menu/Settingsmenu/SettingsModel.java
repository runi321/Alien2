package AlienMarauders.Menu.Settingsmenu;

import AlienMarauders.Model;
import javafx.beans.property.ObjectProperty;

public class SettingsModel {

    private final Model rootModel;

    public SettingsModel(Model rootModel) {
        this.rootModel = rootModel;
    }

    // --- background ---

    public ObjectProperty<String> backgroundImageProperty() {
        return rootModel.backgroundImageProperty();
    }

    public String getBackgroundImage() {
        return rootModel.getBackgroundImage();
    }

    public void setBackgroundImage(String fileName) {
        rootModel.setBackgroundImage(fileName);
    }

    // --- difficulty ---

    public ObjectProperty<Model.Difficulty> difficultyProperty() {
        return rootModel.difficultyProperty();
    }

    public Model.Difficulty getDifficulty() {
        return rootModel.getDifficulty();
    }

    public void setDifficulty(Model.Difficulty d) {
        rootModel.setDifficulty(d);
    }
}
