package AlienMarauders.Menu.Chatmenu;

import AlienMarauders.Controller;
import AlienMarauders.Model;
import javafx.scene.layout.Region;

public class ChatController {

    private final ChatModel model;
    private final ChatView view;
    private final Controller rootController;

    public ChatController(Model rootModel, Controller rootController) {
        this.model = new ChatModel(rootModel);
        this.rootController = rootController;

        this.view = new ChatView(
            this.model,
            rootModel,
            this::sendMessage,            // onSend
            rootController::showMainMenu  // onBack
        );
    }

    private void sendMessage() {
        String text = view.consumeInputText().trim();
        if (!text.isEmpty()) {
            model.addOwnMessage(text);
            view.scrollToBottom();
        }
    }

    public Region getView() {
        return view.getRoot();
    }
}