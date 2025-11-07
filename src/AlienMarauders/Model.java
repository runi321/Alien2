package AlienMarauders;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model {

    public enum Difficulty {
        EASY, MEDIUM, HARD
    }

    // background that all menus should react to
    private final ObjectProperty<String> backgroundImage =
            new SimpleObjectProperty<>("space.png");

    // starting difficulty, default MEDIUM 
    private final ObjectProperty<Difficulty> difficulty =
            new SimpleObjectProperty<>(Difficulty.MEDIUM);

    // chat user list
    private final ObservableList<String> usersBacking =
            FXCollections.observableArrayList(
                    "Pedda",
                    "Post",
                    "Mogens"
            );
    private final ListProperty<String> chatUsers =
            new SimpleListProperty<>(usersBacking);

    // chat messages
    private final ObservableList<String> messagesBacking =
            FXCollections.observableArrayList();
    private final ListProperty<String> chatMessages =
            new SimpleListProperty<>(messagesBacking);

    public ObjectProperty<String> backgroundImageProperty() {
        return backgroundImage;
    }

    public String getBackgroundImage() {
        return backgroundImage.get();
    }

    public void setBackgroundImage(String image) {
        this.backgroundImage.set(image);
    }

    public ObjectProperty<Difficulty> difficultyProperty() {
        return difficulty;
    }

    public Difficulty getDifficulty() {
        return difficulty.get();
    }

    public void setDifficulty(Difficulty d) {
        difficulty.set(d);
    }

    public ListProperty<String> chatUsersProperty() {
        return chatUsers;
    }

    public ListProperty<String> chatMessagesProperty() {
        return chatMessages;
    }
}
