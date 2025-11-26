package AlienMarauders.Menu.Chatmenu;

import AlienMarauders.Model;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;

public class ChatView {

    private final BorderPane root;

    private final ListView<String> messagesList;
    private final ListView<String> usersList;
    private final TextField inputField;

    public ChatView(
        ChatModel chatModel,
        Model rootModel,
        Runnable onSend,
        Runnable onBack
    ) {
        root = new BorderPane();

        bindBackground(root, rootModel);

        // messages list
        messagesList = new ListView<>();
        messagesList.itemsProperty().bind(chatModel.messagesProperty());
        messagesList.setFocusTraversable(false);

        // users list
        usersList = new ListView<>();
        usersList.itemsProperty().bind(chatModel.usersProperty());
        usersList.setPrefWidth(180);

        // input + buttons
        inputField = new TextField();
        inputField.setPromptText("Type your message...");

        Button sendButton = new Button("Send");
        Button mainMenuButton = new Button("Main menu");

        sendButton.setOnAction(e -> onSend.run());
        inputField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                onSend.run();
            }
        });
        mainMenuButton.setOnAction(e -> onBack.run());

        HBox bottom = new HBox(10, inputField, sendButton, mainMenuButton);
        bottom.setPadding(new Insets(10));
        HBox.setHgrow(inputField, Priority.ALWAYS);

        Label chatLabel = new Label("Chat room");
        chatLabel.setStyle("-fx-font-size: 22px; -fx-text-fill: white;");
        VBox top = new VBox(10, chatLabel);
        top.setPadding(new Insets(10));

        root.setTop(top);
        root.setCenter(messagesList);
        root.setRight(usersList);
        root.setBottom(bottom);
    }

    private void bindBackground(BorderPane node, Model rootModel) {
        Runnable apply = () -> {
            String name = rootModel.getBackgroundImage();
            var url = getClass().getResource("/AlienMarauders/Myndir/" + name);
            if (url != null) {
                node.setStyle(
                    "-fx-background-image: url('" + url.toExternalForm() + "');" +
                    "-fx-background-size: cover;"
                );
            } else {
                node.setStyle("-fx-background-color: #111;");
            }
        };
        rootModel.backgroundImageProperty().addListener((obs, o, n) -> apply.run());
        apply.run();
    }

    public BorderPane getRoot() {
        return root;
    }

    // helpers for controller (no direct UI access)

    public String consumeInputText() {
        String text = inputField.getText();
        inputField.clear();
        return text;
    }

    public void scrollToBottom() {
        int lastIndex = messagesList.getItems().size() - 1;
        if (lastIndex >= 0) {
            messagesList.scrollTo(lastIndex);
        }
    }
}