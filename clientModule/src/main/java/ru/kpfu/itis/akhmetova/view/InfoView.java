package ru.kpfu.itis.akhmetova.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import ru.kpfu.itis.akhmetova.MainApplication;

import java.io.IOException;

public class InfoView extends BaseView {

    private AnchorPane pane = null;
    private Button play;
    private Button howToPlay;
    private Button chat;
    private Alert alert;
    private VBox vBox;
    private Image image;
    private final MainApplication application = BaseView.getApplication();
    private final EventHandler<ActionEvent> startGame = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == play) {
                //какая то логика начала игры, добавления пользователя и включения бота
//                UserConfig userConfig = new UserConfig();
//                userConfig.setHost(host.getText());
//                userConfig.setPort(Integer.parseInt(port.getText()));
//                userConfig.setName(name.getText());
//
//                application.setUserConfig(userConfig);
//                try {
//                    application.startChatClient();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                try {
                    application.setView(application.getGameView());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    private final EventHandler<ActionEvent> showChat = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == chat) {
                //какая то логика начала игры, добавления пользователя и включения бота
                try {
                    application.setView(application.getChatView());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    public InfoView() throws Exception {
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

        vBox = new VBox(40);

        play = new Button("Play");
        play.setOnAction(startGame);

        howToPlay = new Button("How to play");
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("How to play");
        alert.setContentText("hello i am alert");
        howToPlay.setOnAction(event -> alert.show());

        chat = new Button("Chat");
        chat.setOnAction(showChat);

        image = new Image("image/tank.jpg", 300, 300, false, true);
        ImageView imageView = new ImageView(image);
        imageView.setX(250);
        imageView.setY(70);

        vBox.getChildren().addAll(play, howToPlay, chat);

        pane.getChildren().addAll(vBox, imageView);

        AnchorPane.setTopAnchor(vBox, 100.0);
        AnchorPane.setLeftAnchor(vBox, 100.0);
        AnchorPane.setRightAnchor(vBox, 100.0);
    }
}
