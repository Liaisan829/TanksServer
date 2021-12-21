package ru.kpfu.itis.akhmetova.view;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import ru.kpfu.itis.akhmetova.IntValue;
import ru.kpfu.itis.akhmetova.LongValue;
import ru.kpfu.itis.akhmetova.MainApplication;
import ru.kpfu.itis.akhmetova.model.Tank;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class GameView extends BaseView {
    private final MainApplication application = BaseView.getApplication();
    private static Stage window;
    private Group root;
    private Scene theScene;
    private ActionEvent event = new ActionEvent();

    public GameView() throws Exception {
    }
//    private static JavaTanksServer gameServer;
//
//    public static void main(String[] args) {
//        gameServer = JavaTanksServer.getInstance();
//        launch();
//    }

    private void exit() {
        event.consume();
        System.out.println("Good bye!");
        Platform.exit();
    }

    private void createView() throws IOException {
        event.consume();

        window = application.getPrimaryStage();
        window.setTitle("GAME");

         root = new Group();
         theScene = new Scene(root);

        Canvas canvas = new Canvas(1000, 720);
        root.getChildren().add(canvas);

        ArrayList<String> input = new ArrayList<>();

//        ОЧЕРЕДИ ДВИЖЕНИЙ ТАНКА
        theScene.setOnKeyPressed(
                e -> {
                    String code = e.getCode().toString();
                    if (!input.contains(code))
                        input.add(code);
                });

        theScene.setOnKeyReleased(
                e -> {
                    String code = e.getCode().toString();
                    input.remove(code);
                });

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Font theFont = Font.font("Helvetica", FontWeight.BOLD, 24);
        gc.setFont(theFont);
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);

        Tank player = new Tank();
        player.setImage("/image/player.png");
        player.setPosition(0, 0);
        Tank enemy = new Tank();
        enemy.setImage("/image/enemy.png");
        enemy.setPosition(900, 600);
        enemy.render(gc);

        System.out.println("танки созданы");

        ArrayList<Tank> moneybagList = new ArrayList<>();

        Thread thread = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    Thread.sleep(4000);
                    Tank moneybag = new Tank();
                    //TODO почему деньги это танк
                    moneybag.setImage("/image/moneybag.png");
                    double px = 1950 * Math.random() + 50;
                    double py = 650 * Math.random() + 20;
                    moneybag.setPosition(px, py);
                    moneybagList.add(moneybag);
                } catch (InterruptedException e) {
                }
            }
        });
        System.out.println("деньги расставляются по рандому");

        thread.start();
        LongValue lastNanoTime = new LongValue(System.nanoTime());

        IntValue score = new IntValue(0);

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                // calculate time since last update.
                if (score.value * 100 >= 1000) {
                    if (thread.isAlive()) thread.interrupt();
                    System.out.println("VICTORY");
                    System.exit(0);
                }
                double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
                lastNanoTime.value = currentNanoTime;

                // game logic

                System.out.println("логика игры пошла");

                player.setVelocity(0, 0);
                if (input.contains("LEFT"))
                    player.addVelocity(-100, 0);
                if (input.contains("RIGHT"))
                    player.addVelocity(100, 0);
                if (input.contains("UP"))
                    player.addVelocity(0, -100);
                if (input.contains("DOWN"))
                    player.addVelocity(0, 100);

                player.update(elapsedTime);

                System.out.println("после клавиш и обновления");

                // collision detection

                Iterator<Tank> moneybagIter = moneybagList.iterator();
                while (moneybagIter.hasNext()) {
                    Tank moneybag = moneybagIter.next();
                    if (player.intersects(moneybag)) {
                        moneybagIter.remove();
                        score.value++;
                    }
                }

                System.out.println("танк забрал деньги");

                // render
                gc.clearRect(0, 0, 1280, 720);
                player.render(gc);
                enemy.render(gc);

                for (Tank moneybag : moneybagList)
                    moneybag.render(gc);

                String pointsText = "Cash: $" + (100 * score.value);
                gc.fillText(pointsText, 360, 36);
                gc.strokeText(pointsText, 360, 36);
            }
        }.start();
        window.setScene(theScene);
//        window.show();

    }

    @Override
    public Parent getView() throws IOException {
        if (root == null) {
            this.createView();
        }
        return root;
    }
}

