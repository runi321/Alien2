package AlienMarauders.Menu.Chatmenu;

import AlienMarauders.Controller;
import AlienMarauders.Model;
import javafx.scene.input.KeyCode;

public class ChatController {

    private final Model model;
    private final ChatView view;
    private final Controller rootController;

    public ChatController(Model model, ChatView view, Controller rootController) {
        this.model = model;
        this.view = view;
        this.rootController = rootController;

        attachHandlers();
    }

    private void attachHandlers() {
        view.getSendButton().setOnAction(e -> sendMessage());
        view.getInputField().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                sendMessage();
            }
        });

        view.getMainMenuButton().setOnAction(e -> rootController.showMainMenu());
    }

    private void sendMessage() {
        String text = view.getInputField().getText().trim();
        if (!text.isEmpty()) {
            // could add username, for now just "Me:"
            model.chatMessagesProperty().add("Me: " + text);
            view.getInputField().clear();

            // scroll to bottom
            int lastIndex = model.chatMessagesProperty().size() - 1;
            if (lastIndex >= 0) {
                view.getMessagesList().scrollTo(lastIndex);
            }
        }
    }
}