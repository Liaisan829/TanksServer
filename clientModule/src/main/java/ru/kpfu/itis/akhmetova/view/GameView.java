package ru.kpfu.itis.akhmetova.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import ru.kpfu.itis.akhmetova.MainApplication;

public class GameView extends BaseView {

    private GridPane gridPane = null;
    private VBox vBox;
    private StackPane stackPane;
    private Button changeColor;
    private Label first;
    private Label second;
    private Label third;
    private Label fourth;
    private Label fifth;
    private Label sixth;
    private Label seventh;
    private Label eighth;
    private Label ninth;
    private Image image;
    private final MainApplication application = BaseView.getApplication();
//    private final EventHandler<ActionEvent> changeColorOfCell = new EventHandler<ActionEvent>() {
//        @Override
//        public void handle(ActionEvent event) {
//            stackPane = new StackPane();
//            stackPane.getChildren().add(second);
//            stackPane.setStyle("-fx-background-color: blue");
//        }
//    };

    public GameView() throws Exception {
    }

    @Override
    public Parent getView() {
        if (gridPane == null) {
            this.createView();
        }

        return gridPane;
    }

    private void createView() {
        gridPane = new GridPane();
        stackPane = new StackPane();
        changeColor = new Button("change color");
        changeColor.setMaxWidth(190);
//        changeColor.setOnAction(changeColorOfCell);

        first = new Label("first");
        second = new Label("second");
        third = new Label("");
        fourth = new Label("");
        fifth = new Label("");
        sixth = new Label("");
        seventh = new Label("");
        eighth = new Label("");
        ninth = new Label("");

        image = new Image("tank.jpg", 50, 50, false, true);
        ImageView imageView = new ImageView(image);
//        imageView.setX(70);
//        imageView.setY(70);

//        stackPane.getChildren().add(first);
//        stackPane.setStyle("-fx-background-color: blue");

        gridPane.getColumnConstraints().add(new ColumnConstraints(150, 150, Double.MAX_VALUE));
        ColumnConstraints column2 = new ColumnConstraints(150, 150, Double.MAX_VALUE);
        gridPane.getColumnConstraints().add(column2);
        ColumnConstraints column3 = new ColumnConstraints(150, 150, Double.MAX_VALUE);
        gridPane.getColumnConstraints().add(column3);

        gridPane.getRowConstraints().add(new RowConstraints(150));
        gridPane.getRowConstraints().add(new RowConstraints(150));
        gridPane.getRowConstraints().add(new RowConstraints(150));

        gridPane.setGridLinesVisible(true);

        gridPane.add(imageView, 0, 0);
        gridPane.add(second, 1, 0);
        gridPane.add(third, 2, 0);
        gridPane.add(fourth, 0, 1);
        gridPane.add(fifth, 1, 1);
        gridPane.add(sixth, 2, 1);
        gridPane.add(seventh, 0, 2);
        gridPane.add(eighth, 1, 2);
        gridPane.add(ninth, 2, 2);

        System.out.println(gridPane.getChildren().get(1).getLayoutX());
//        gridPane.getChildren().add(stackPane);
//        gridPane.getChildren().add(changeColor);

//        application.getPrimaryStage().

        Scene mainScene = new Scene(gridPane, 600, 500);
        application.getPrimaryStage().setScene(mainScene);

        mainScene.setOnKeyPressed(
                key -> {
                    switch (key.getCode()) {
                        case UP:
                            System.out.println("up");
                            break;
                        case DOWN:
                            System.out.println("down");
                            break;
                        case LEFT:
                            System.out.println("left");
                            break;
                        case RIGHT:
                            System.out.println("right");
                            break;
                    }
                }
        );

    }
}
