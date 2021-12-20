package ru.kpfu.itis.akhmetova.model;

import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tank{
    private ImageView image;
    private double currentX;
    private double currentY;
    private int points;

    public Tank(ImageView image, double currentX, double currentY, int points) {
        this.image = image;
        this.currentX = currentX;
        this.currentY = currentY;
        this.points = points;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public double getCurrentX() {
        return currentX;
    }

    public void setCurrentX(double currentX) {
//        if (this.currentX < 0) {
//            System.out.println("граница ячеек");
//        }
        this.currentX = this.currentX + currentX;
    }

    public double getCurrentY() {
        return currentY;
    }

    public void setCurrentY(double currentY) {
        this.currentY = this.currentY + currentY;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = this.points + points;
    }

    @Override
    public String toString() {
        return "Tank{" +
                "image=" + image +
                ", currentX=" + currentX +
                ", currentY=" + currentY +
                ", points=" + points +
                '}';
    }

}
