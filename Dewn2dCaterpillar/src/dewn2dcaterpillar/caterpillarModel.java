/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dewn2dcaterpillar;

import dewn2dcaterpillar.FoodPackage.FoodObject;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author dewn2d
 */
public class caterpillarModel {

    private KeyFrame keyframe;
    private Direction direction;
    private double rotation;
    private Timeline timeline;
    private boolean running = false;
    private double padding = -18;
    private AnchorPane board;
    private int score;

    private ArrayList<ImageView> bodyList;
    private ArrayList<ArrayList<Direction>> directChain = new ArrayList();
    private Direction currDirect = Direction.RIGHT;
    private double halfBoardY;
    private double halfBoardX;
    private int numBody;
    private FoodObject food;
    private double currFoodX;
    private double currFoodY;
    private int hitBox = 30;

    private final ImageView head;
    private final ImageView butt;
    private int weight = 0;

    public caterpillarModel(AnchorPane board) {

        bodyList = new ArrayList();

        this.board = board;

        halfBoardX = board.getPrefWidth() / 2;
        halfBoardY = board.getPrefHeight() / 2;
//        System.out.println(halfBoardX + " and " + halfBoardY);

        head = new ImageView();
        butt = new ImageView();

        head.setImage(new Image(getClass().getResourceAsStream("hungry_head.png")));
        butt.setImage(new Image(getClass().getResourceAsStream("hungry_butt.png")));

        bodyList.add(head);
        bodyList.add(butt);

        numBody = bodyList.size();
        int k = bodyList.size() - 1;

        for (int i = 0; i < bodyList.size(); i++) {

//            System.out.println(k);
            bodyList.get(i).setTranslateX(halfBoardX - i * 18);
            bodyList.get(i).setTranslateY(halfBoardY);
            k--;
            weight++;

        }

        bodyList.forEach(board.getChildren()::add);

    }

    public int getScore() {
        return this.score;
    }

    public int getWeight() {
        return weight;
    }

    public void updateBody() {

        if (bodyList.size() == 12) {
            return;
        }
        
        weight = 0;

        ImageView middle = new ImageView();
        middle.setImage(new Image(getClass().getResourceAsStream("hungry_middle.png")));

        board.getChildren().clear();
        randFood();
        bodyList.add(1, middle);

        int k = bodyList.size() - 1;

        directChain.clear();
        for (int i = 0; i < bodyList.size(); i++) {
            directChain.add(new ArrayList());
            for (int j = 0; j <= i * 12 + 1; j++) {
                directChain.get(i).add(currDirect);
//                System.out.println(directChain.get(i));
            }
            bodyList.get(i).setTranslateX(halfBoardX - i * 12);
            bodyList.get(i).setTranslateY(halfBoardY);
            weight++;
        }

//        temp.clear();
        bodyList.forEach(board.getChildren()::add);
    }

    public void setCurrDirection(Direction curr) {
        this.currDirect = curr;
    }

    public Direction getCurrDirection() {
        return currDirect;
    }

    private void update(int k) {
        switch (directChain.get(k).get(0)) {
            case RIGHT:
                if (bodyList.get(k).getTranslateX() + 1 == board.getPrefWidth() + padding) {
                    bodyList.get(k).setTranslateX(padding);
                } else {
                    bodyList.get(k).setTranslateX(bodyList.get(k).getTranslateX() + 1);
                }
                bodyList.get(k).setTranslateY(bodyList.get(k).getTranslateY());
                bodyList.get(k).setRotate(0);
                break;
            case LEFT:
                if (bodyList.get(k).getTranslateX() - 1 == padding) {
                    bodyList.get(k).setTranslateX(board.getPrefWidth() + padding);
                } else {
                    bodyList.get(k).setTranslateX(bodyList.get(k).getTranslateX() - 1);
                }
                bodyList.get(k).setTranslateY(bodyList.get(k).getTranslateY());
                bodyList.get(k).setRotate(180);
                break;
            case UP:
                if (bodyList.get(k).getTranslateY() - 1 == padding) {
                    bodyList.get(k).setTranslateY(board.getPrefHeight() + padding);
                } else {
                    bodyList.get(k).setTranslateY(bodyList.get(k).getTranslateY() - 1);
                }
                bodyList.get(k).setTranslateX(bodyList.get(k).getTranslateX());
                bodyList.get(k).setRotate(-90);
                break;
            case DOWN:
                if (bodyList.get(k).getTranslateY() - 1 == board.getPrefHeight() + padding) {
                    bodyList.get(k).setTranslateY(padding);
                } else {
                    bodyList.get(k).setTranslateY(bodyList.get(k).getTranslateY() + 1);
                }
                bodyList.get(k).setTranslateX(bodyList.get(k).getTranslateX());
                bodyList.get(k).setRotate(-270);
                break;
        }
        directChain.get(k).remove(0);
        board.getChildren().remove(food.getImageView());

        if ((int) bodyList.get(0).getTranslateX() <= (int) currFoodX + hitBox
                && (int) bodyList.get(0).getTranslateX() >= (int) currFoodX - hitBox
                && (int) bodyList.get(0).getTranslateY() <= (int) currFoodY + hitBox
                && (int) bodyList.get(0).getTranslateY() >= (int) currFoodY - hitBox) {

            randFood();
            score += food.getPoints();
        } else {
            if (k == 0) {
                food.foodRandMovement();
            }
            board.getChildren().add(food.getImageView());
            currFoodX = food.getImageView().getTranslateX();
            currFoodY = food.getImageView().getTranslateY();
        }
    }

    public AnchorPane randFood() {

        int randomNum = 1 + (int) (Math.random() * 8);
        food = new FoodObject(randomNum, padding);
        food.updateFood(board.getPrefWidth(), board.getPrefWidth());

        board.getChildren().add(food.getImageView());
        currFoodX = food.getImageView().getTranslateX();
        currFoodY = food.getImageView().getTranslateY();

        return board;
    }

    public void callUpdate(int k) {
        update(k);
    }

    public ArrayList<ImageView> getBodyList() {
        return bodyList;
    }

    public AnchorPane getBoard() {
        return board;
    }

    public void setBodyList(ArrayList body) {
        bodyList = body;
    }

    public ArrayList<ArrayList<Direction>> getDirectChain() {
        return directChain;
    }

    public void start() {
        timeline.play();
        running = true;
    }

    public void stop() {
        timeline.pause();
        running = false;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public Timeline getTimeLine() {
        return timeline;
    }

    public void setTimeLine(Timeline timeline) {
        this.timeline = timeline;
    }

    public boolean isRunning() {
        return running;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public KeyFrame getKeyFrame() {
        return keyframe;
    }

    public void setKeyFrame(KeyFrame keyframe) {
        this.keyframe = keyframe;
    }

    public double getFoodX() {
        return currFoodX;
    }

    public double getFoodY() {
        return currFoodY;
    }

    public void setFoodX(double input) {
        currFoodX = input;
    }

    public void setFoodY(double input) {
        currFoodY = input;
    }
}
