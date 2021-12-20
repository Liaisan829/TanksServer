package ru.kpfu.itis.akhmetova.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import ru.kpfu.itis.akhmetova.MainApplication;

public class ChatView extends BaseView {

    private TextArea input;
    private TextArea conversation;
    private AnchorPane pane = null;
    private Button back;
    private final MainApplication application = BaseView.getApplication();
    private final EventHandler onKeyEvent = new EventHandler<KeyEvent>() {

        @Override
        public void handle(KeyEvent event) {
            if (event.getCode() == KeyCode.ENTER) {
                String sender = application.getUserConfig().getName();
                String message = input.getText() + "\n";
                application.getChatClient().sendMessage(sender + " " + message );
                conversation.appendText("you: " + message);

                input.clear();
                event.consume();
            }
        }
    };

    private final EventHandler<ActionEvent> moveBack = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
                application.setView(application.getInfoView());
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
        back = new Button("Назад");
        back.setOnAction(moveBack);

        AnchorPane.setLeftAnchor(back, 10.0);
        AnchorPane.setTopAnchor(back, 10.0);
        back.setMaxWidth(80);

        conversation = new TextArea();
        conversation.setEditable(false);
        conversation.setWrapText(true);
        conversation.setMaxHeight(400);

        AnchorPane.setTopAnchor(conversation, 50.0);
        AnchorPane.setLeftAnchor(conversation, 10.0);
        AnchorPane.setRightAnchor(conversation, 10.0);
        AnchorPane.setBottomAnchor(conversation, 50.0);

        input = new TextArea();
        input.setMaxHeight(50);

        AnchorPane.setBottomAnchor(input, 5.0);
        AnchorPane.setLeftAnchor(input, 10.0);
        AnchorPane.setRightAnchor(input, 10.0);
        AnchorPane.setTopAnchor(input, 425.0);

        input.addEventHandler(KeyEvent.KEY_PRESSED, onKeyEvent);
        pane.getChildren().addAll(conversation, input, back);

    }

    public void appendMessageToConversation(String message) {
        if (message != null) conversation.appendText(message + "\n");
    }
}