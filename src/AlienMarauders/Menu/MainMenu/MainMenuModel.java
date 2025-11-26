package AlienMarauders.Menu.MainMenu;

import AlienMarauders.Model;
import javafx.beans.property.ObjectProperty;

public class MainMenuModel {

    private final Model rootModel;

    public MainMenuModel(Model rootModel) {
        this.rootModel = rootModel;
    }

    // background

    public ObjectProperty<String> backgroundImageProperty() {
        return rootModel.backgroundImageProperty();
    }

    public String getBackgroundImage() {
        return rootModel.getBackgroundImage();
    }
}