package ru.kpfu.itis.akhmetova.view;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import ru.kpfu.itis.akhmetova.MainApplication;
import ru.kpfu.itis.akhmetova.model.Tank;

public class GameView extends BaseView {

    private GridPane gridPane = null;
    private Scene mainScene;
    private VBox vBox;
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
    private ImageView imageView;
    private Tank clientTank1;
    private final MainApplication application = BaseView.getApplication();

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
        imageView = new ImageView(new Image("tank.jpg", 50, 50, false, true));
        clientTank1 = new Tank(imageView, 0.0, 0.0, 0);
        gridPane = new GridPane();

        first = new Label("");
        second = new Label("");
        third = new Label("");
        fourth = new Label("");
        fifth = new Label("");
        sixth = new Label("");
        seventh = new Label("");
        eighth = new Label("");
        ninth = new Label("");

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

        mainScene = new Scene(gridPane, 600, 500);
        application.getPrimaryStage().setScene(mainScene);

        mainScene.setOnKeyPressed(
                key -> {
                    switch (key.getCode()) {
                        case UP:
                            //надо получить текущие координаты и прибавить к ним в соответсвии с кнопкой и передать их уже в метод
                            System.out.println("up");
                            clientTank1.setCurrentX(0.0);
                            clientTank1.setCurrentY(-1.0);
                            gridPane.requestLayout();
                            gridPane.getChildren().removeAll(imageView);
                            gridPane.requestLayout();
                            System.out.println(clientTank1.getCurrentX());
                            System.out.println(clientTank1.getCurrentY());
                            break;
                        case DOWN:
                            System.out.println("down");
                            clientTank1.setCurrentX(0.0);
                            clientTank1.setCurrentY(1.0);
                            System.out.println(clientTank1.getCurrentX());
                            System.out.println(clientTank1.getCurrentY());
                            break;
                        case LEFT:
                            System.out.println("left");
                            clientTank1.setCurrentX(-1.0);
                            clientTank1.setCurrentY(0.0);
                            System.out.println(clientTank1.getCurrentX());
                            System.out.println(clientTank1.getCurrentY());
                            break;
                        case RIGHT:
                            System.out.println("right");
                            moveClientTank(gridPane, clientTank1, 1, 0);
                            clientTank1.setCurrentX(1.0);
                            clientTank1.setCurrentY(0.0);
                            System.out.println(clientTank1.getCurrentX());
                            System.out.println(clientTank1.getCurrentY());
                            break;
                        case ENTER:
                            System.out.println("take money");
                            break;
                    }
                }
        );
    }

    public void moveClientTank(GridPane gridPane, Tank clientTank, int newX, int newY) {
        gridPane.getChildren().remove(clientTank.getImage());
//        gridPane.add(clientTank.getImage(), newX, newY);
        gridPane.requestLayout();
        gridPane.requestFocus();
        gridPane.relocate(600.0, 700.0);
    }
}

