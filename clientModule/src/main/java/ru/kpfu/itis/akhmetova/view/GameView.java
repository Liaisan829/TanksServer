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
import ru.kpfu.itis.akhmetova.model.Bot;
import ru.kpfu.itis.akhmetova.model.Sprite;

import java.util.ArrayList;
import java.util.Iterator;

public class GameView extends BaseView {
    private final MainApplication application = BaseView.getApplication();
    private static Stage window;
    private Group root;
    private Scene theScene;
    private Scene finishScene;
    private ActionEvent event = new ActionEvent();

    public GameView() throws Exception {
    }

    private void exit() {
        event.consume();
        System.out.println("Good bye!");
        Platform.exit();
    }

    private void createView() {
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

        Sprite player = new Sprite();
        player.setImage("/image/playerForward.png");
        player.setPosition(1, 1);
        player.render(gc);

        Bot bot = new Bot();
        bot.setImage("/image/enemyForward.png");
        bot.setPosition(200, 200);
        bot.render(gc);
        bot.startBotGame(bot);

        ArrayList<Sprite> moneybagList = new ArrayList<>();

        Thread thread = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    Thread.sleep(4000);
                    Sprite moneybag = new Sprite();
                    moneybag.setImage("/image/moneybag.png");
                    double px = 1950 * Math.random() + 50;
                    double py = 650 * Math.random() + 20;
                    moneybag.setPosition(px, py);
                    moneybagList.add(moneybag);
                } catch (InterruptedException e) {
                }
            }
        });

        thread.start();
        LongValue lastNanoTime = new LongValue(System.nanoTime());

        IntValue score = new IntValue(0);

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                // calculate time since last update.
                if (score.value * 100 >= 1000) {
                    if (thread.isAlive()) thread.interrupt();
                    System.out.println("VICTORY");

                    System.exit(0);
                }
                double elapsedTime = (currentNanoTime - lastNanoTime.value) / 100000000.0;
                lastNanoTime.value = currentNanoTime;

                // game logic
                player.setVelocity(0, 0);
                if (input.contains("LEFT")) {
                    player.setSide(0);
                    player.setImage("/image/playerLeft.png");
                    player.setPosition(player.getX() - 100, player.getY());
                }
                if (input.contains("RIGHT")) {
                    player.setSide(2);
                    player.setImage("/image/playerRight.png");
                    player.setPosition(player.getX() + 100, player.getY());
                }
                if (input.contains("UP")) {
                    player.setSide(1);
                    player.setImage("/image/playerForward.png");
                    player.setPosition(player.getX(), player.getY() + 100);
                }
                if (input.contains("DOWN")) {
                    player.setSide(3);
                    player.setImage("/image/playerBackward.png");
                    player.setPosition(player.getX(), player.getY() - 100);
                }

                player.update(elapsedTime);

                // collision detection танк дотронулся до денег и они исчезают
                Iterator<Sprite> moneybagIter = moneybagList.iterator();
                while (moneybagIter.hasNext()) {
                    Sprite moneybag = moneybagIter.next();
                    if (player.intersects(moneybag)) {
                        moneybagIter.remove();
                        score.value++;
                    }
                }

                // render
                gc.clearRect(0, 0, 1280, 720);
                player.render(gc);
                bot.render(gc);

                for (Sprite moneybag : moneybagList)
                    moneybag.render(gc);

                String pointsText = "Cash: $" + (100 * score.value);
                gc.fillText(pointsText, 360, 36);
                gc.strokeText(pointsText, 360, 36);

                if(score.value == 1000){
                    exit();
                }
            }
        };
        animationTimer.start();
        window.setScene(theScene);
    }

    @Override
    public Parent getView(){
        if (root == null) {
            this.createView();
        }
        return root;
    }
}