package ru.kpfu.itis.akhmetova.view;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import ru.kpfu.itis.akhmetova.MainApplication;

public class GameView extends BaseView {

    private GridPane gridPane = null;
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
        gridPane = new GridPane();

        Label first = new Label("first");
        Label second = new Label("second");
        Label third = new Label("third");
        Label fourth = new Label("fourth");
        Label fifth = new Label("fifth");
        Label sixth = new Label("sixth");
        Label seventh = new Label("seventh");
        Label eighth = new Label("eighth");
        Label ninth = new Label("ninth");

        gridPane.getColumnConstraints().add(new ColumnConstraints(120, 120, Double.MAX_VALUE));
        ColumnConstraints column2 = new ColumnConstraints(120, 120, Double.MAX_VALUE);
        gridPane.getColumnConstraints().add(column2);
        ColumnConstraints column3 = new ColumnConstraints(120, 120, Double.MAX_VALUE);
        gridPane.getColumnConstraints().add(column3);

        // определения строк
        gridPane.getRowConstraints().add(new RowConstraints(100));
        gridPane.getRowConstraints().add(new RowConstraints(100));
        gridPane.getRowConstraints().add(new RowConstraints(100));

        gridPane.setGridLinesVisible(true);

        GridPane.setMargin(fifth, new Insets(10, 10, 10, 10));

        gridPane.add(first, 0, 0);
        gridPane.add(second, 1, 0);
        gridPane.add(third, 2, 0);
        gridPane.add(fourth, 0, 1);
        gridPane.add(fifth, 1, 1);
        gridPane.add(sixth, 2, 1);
        gridPane.add(seventh, 0, 2);
        gridPane.add(eighth, 1, 2);
        gridPane.add(ninth, 2, 2);


    }
}
