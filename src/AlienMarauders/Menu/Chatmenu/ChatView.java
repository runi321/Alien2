package AlienMarauders.Menu.Chatmenu;

import AlienMarauders.Model;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ChatView {

    private final BorderPane root;

    private final ListView<String> messagesList;
    private final ListView<String> usersList;
    private final TextField inputField;
    private final Button sendButton;
    private final Button mainMenuButton;

    public ChatView(Model model) {
        root = new BorderPane();
        bindBackground(root, model);

        // message list (center)
        messagesList = new ListView<>();
        messagesList.itemsProperty().bind(model.chatMessagesProperty());
        messagesList.setFocusTraversable(false);

        // users (right)
        usersList = new ListView<>();
        usersList.itemsProperty().bind(model.chatUsersProperty());
        usersList.setPrefWidth(180);

        // bottom input area
        inputField = new TextField();
        inputField.setPromptText("Type your message...");
        sendButton = new Button("Send");
        mainMenuButton = new Button("Main menu");

        HBox bottom = new HBox(10, inputField, sendButton, mainMenuButton);
        bottom.setPadding(new Insets(10));
        HBox.setHgrow(inputField, Priority.ALWAYS);

        Label chatLabel = new Label("Chat room");
        chatLabel.setStyle("-fx-font-size: 22px; -fx-text-fill: white;");
        VBox top = new VBox(10, chatLabel);
        top.setPadding(new Insets(10, 10, 0, 10));

        root.setTop(top);
        root.setCenter(messagesList);
        root.setRight(usersList);
        root.setBottom(bottom);
    }

private void bindBackground(BorderPane node, Model model) {
    Runnable apply = () -> {
        String name = model.getBackgroundImage();                  // e.g. "space.png" / "ufo.png"
        var url = getClass().getResource("/AlienMarauders/Myndir/" + name);
        node.setStyle(url != null
            ? "-fx-background-image: url('" + url.toExternalForm() + "'); -fx-background-size: cover;"
            : "-fx-background-color: #111;");
    };
    model.backgroundImageProperty().addListener((o, a, b) -> apply.run());
    apply.run();                                                  // initial background
}
    public BorderPane getRoot() {
        return root;
    }

    public TextField getInputField() {
        return inputField;
    }

    public Button getSendButton() {
        return sendButton;
    }

    public Button getMainMenuButton() {
        return mainMenuButton;
    }

    public ListView<String> getMessagesList() {
        return messagesList;
    }
}