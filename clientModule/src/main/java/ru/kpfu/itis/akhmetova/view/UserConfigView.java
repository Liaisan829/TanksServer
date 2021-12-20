package ru.kpfu.itis.akhmetova.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import ru.kpfu.itis.akhmetova.MainApplication;
import ru.kpfu.itis.akhmetova.model.UserConfig;

import java.io.IOException;

public class UserConfigView extends BaseView {

    private Text welcomeText1;
    private AnchorPane pane = null;
    private TextField name;
    private TextField host;
    private TextField port;
    private Button start;
    private VBox vBox;
    private final MainApplication application = BaseView.getApplication();
    private final EventHandler<ActionEvent> startEvent = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == start) {
                UserConfig userConfig = new UserConfig();

                userConfig.setHost(host.getText());
                userConfig.setPort(Integer.parseInt(port.getText()));
                userConfig.setName(name.getText());

                application.setUserConfig(userConfig);

                try {
                    application.startChatClient();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                application.setView(application.getInfoView());
            }
        }
    };

    public UserConfigView() throws Exception {
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
        pane.setStyle("-fx-background-color: green");
        vBox = new VBox(10);

        welcomeText1 = new Text("Добро пожаловать в игру Танки!" + "\n" + "Укажите Ваше имя");
        welcomeText1.setStyle("-fx-font-size: 25");

        Label nameLabel = new Label("name");
        name = new TextField();

        Label hostLabel = new Label("host");
        host = new TextField();
        host.setText("127.0.0.1");

        Label portLabel = new Label("port");
        port = new TextField();
        port.setText("5555");

        vBox.getChildren().addAll(welcomeText1, nameLabel, name, hostLabel, host, portLabel, port);

        start = new Button("Start");
        start.setOnAction(startEvent);

        vBox.getChildren().add(start);

        AnchorPane.setTopAnchor(vBox, 100.0);
        AnchorPane.setLeftAnchor(vBox, 100.0);
        AnchorPane.setRightAnchor(vBox, 100.0);

        pane.getChildren().add(vBox);

    }
}