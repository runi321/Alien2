package AlienMarauders.Menu.Chatmenu;

import AlienMarauders.Model;
import javafx.beans.property.ListProperty;

public class ChatModel {

    private final Model rootModel;

    public ChatModel(Model rootModel) {
        this.rootModel = rootModel;
    }

    public ListProperty<String> usersProperty() {
        return rootModel.chatUsersProperty();
    }

    public ListProperty<String> messagesProperty() {
        return rootModel.chatMessagesProperty();
    }

    public void addOwnMessage(String text) {
        rootModel.chatMessagesProperty().add("Me: " + text);
    }
}