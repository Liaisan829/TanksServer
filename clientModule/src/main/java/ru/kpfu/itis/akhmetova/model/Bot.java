package ru.kpfu.itis.akhmetova.model;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Random;

public class Bot {
    private Image image;
    private int side;
    private double positionX;
    private double positionY;
    private double velocityX;
    private double velocityY;
    private double width;
    private double height;

    public Bot() {
        positionX = 0;
        positionY = 0;
        velocityX = 0;
        velocityY = 0;
        side = 1;
    }

    public void startBotGame(Bot bot){
        Random random = new Random();
        int value = random.nextInt(10);
        if(value == 0){
            //TODO повернуть вперед и подвинуть
            moveAndRotateForward(bot);
        }

        if(value == 1){
            moveAndRotateBackward(bot);
        }

        if(value == 2){
            moveAndRotateLeft(bot);
        }

        if(value == 3){
            moveAndRotateRight(bot);
        }
    }

    public void moveAndRotateForward(Bot bot){
        bot.setSide(1);
        bot.setImage("/image/enemyForward.png");
        bot.setPosition(bot.getX(), bot.getY() + 100);
    }

    public void moveAndRotateBackward(Bot bot){
        bot.setSide(3);
        bot.setImage("/image/enemyBackward.png");
        bot.setPosition(bot.getX(), bot.getY() - 100);
    }

    public void moveAndRotateLeft(Bot bot){
        bot.setSide(0);
        bot.setImage("/image/enemyLeft.png");
        bot.setPosition(bot.getX() - 100, bot.getY());
    }

    public void moveAndRotateRight(Bot bot){
        bot.setSide(2);
        bot.setImage("/image/enemyRight.png");
        bot.setPosition(bot.getX() + 100, bot.getY());
    }

    public void setImage(Image i) {
        this.image = i;
        width = i.getWidth();
        height = i.getHeight();
    }

    public void setImage(String filename) {
        Image i = new Image(filename);
        setImage(i);
    }

    public void setPosition(double x, double y) {
        positionX = x;
        positionY = y;
    }

    public int getSide() {
        return side;
    }

    public void setSide(int side) {
        this.side = side;
    }

    public double getX() {
        return this.positionX;
    }

    public double getY() {
        return this.positionY;
    }

    public void setVelocity(double x, double y) {
        velocityX = x;
        velocityY = y;
    }

    public void addVelocity(double x, double y) {
        velocityX += x;
        velocityY += y;
    }

    public void update(double time) {
        positionX += velocityX * time;
        positionY += velocityY * time;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(image, positionX, positionY);
    }

    public Rectangle2D getBoundary() {
        return new Rectangle2D(positionX, positionY, width, height);
    }

    public boolean intersects(Sprite sprite) {
        return sprite.getBoundary().intersects(this.getBoundary());
    }

    public String toString() {
        return " Position: [" + positionX + "," + positionY + "]"
                + " Velocity: [" + velocityX + "," + velocityY + "]";
    }

}