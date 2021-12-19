package ru.kpfu.itis.akhmetova.view;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import ru.kpfu.itis.akhmetova.MainApplication;

public class ChatView extends BaseView {

    private TextArea input;
    private TextArea conversation;
    private AnchorPane pane = null;
    private final MainApplication application = BaseView.getApplication();
    private final EventHandler onKeyEvent = new EventHandler<KeyEvent>() {

        @Override
        public void handle(KeyEvent event) {
            if (event.getCode() == KeyCode.ENTER) {
                String sender = application.getUserConfig().getName();
                String message = input.getText() + "\n";
                application.getChatClient().sendMessage(sender + " " + message);
                conversation.appendText("you: " + message);

                input.clear();
                event.consume();
            }
        }
    };

    public ChatView() throws Exception {
    }

    @Override
    public Parent getView() {
        if (pane == null) {
            this.createView();
        }

        return pane;
    }

    private void createView() {
        pane = new AnchorPane();

        conversation = new TextArea();
        conversation.setEditable(false);
        conversation.setWrapText(true);

        AnchorPane.setTopAnchor(conversation, 10.0);
        AnchorPane.setLeftAnchor(conversation, 10.0);
        AnchorPane.setRightAnchor(conversation, 10.0);

        pane.getChildren().add(conversation);

        input = new TextArea();
        input.setMaxHeight(50);

        AnchorPane.setBottomAnchor(conversation, 10.0);
        AnchorPane.setLeftAnchor(conversation, 10.0);
        AnchorPane.setRightAnchor(conversation, 10.0);

        input.addEventHandler(KeyEvent.KEY_PRESSED, onKeyEvent);
        pane.getChildren().add(input);
    }

    public void appendMessageToConversation(String message) {
        if (message != null) conversation.appendText(message);
    }
}