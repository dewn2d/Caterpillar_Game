/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dewn2dcaterpillar.FoodPackage;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author dewn2d
 */
public class FoodObject {

    private int points;
    private ImageView foodImageView = new ImageView();
    private Food foodObject;
    private double food;
    private double limitX;
    private double limitY;
    private double padding;
    private double speed = .3;
    private int randNum;
    private int k = 0;

    public FoodObject(int food, double pad) {
        switch (food) {
            case 1:
                foodObject = new Apple();
                break;
            case 2:
                foodObject = new Strawberry();
                break;
            case 3:
                foodObject = new Pear();
                break;
            case 4:
                foodObject = new Icecream();
                break;
            case 5:
                foodObject = new Salami();
                break;
            case 6:
                foodObject = new Sausage();
                break;
            case 7:
                foodObject = new Orange();
                break;
            case 8:
                foodObject = new Blueberry();
                break;
        }

        foodImageView = foodObject.getFood();
        this.padding = pad;
        this.points = food * 100;
        this.food = (double) food;

    }
    
    public int getPoints(){
        return this.points;
    }

    public void updateFood(double width, double height) {
        limitX = width;
        limitY = height;
        double randHeight = 1 + (Math.random() * (width));
        double randWidth = 1 + (Math.random() * (height));
        foodImageView.setTranslateX(randHeight);
        foodImageView.setTranslateY(randWidth);
    }

    public ImageView getImageView() {
        return foodImageView;
    }

    public Food getObject() {
        return foodObject;
    }

    public void foodRandMovement() {
        k = k % 50;
        if (k == 0) {
            randNum = 1 + (int) (Math.random() * 4);
        }

        switch (randNum) {
            case 1:
                if (foodImageView.getTranslateX() + 1 >= limitX + padding) {
                    foodImageView.setTranslateX(0);
                } else {
                    foodImageView.setTranslateX(foodImageView.getTranslateX() + speed *food);
                }
                break;
            case 2:
                if (foodImageView.getTranslateX() - 1 <= padding) {
                    foodImageView.setTranslateX(limitX);
                } else {
                    foodImageView.setTranslateX(foodImageView.getTranslateX() - speed *food);
                }
                break;
            case 3:
                if (foodImageView.getTranslateY() + 1 >= limitY + padding) {
                    foodImageView.setTranslateY(0);
                } else {
                    foodImageView.setTranslateY(foodImageView.getTranslateY() + speed *food);
                }
                break;
            case 4:
                if (foodImageView.getTranslateY() - 1 <= padding) {
                    foodImageView.setTranslateY(limitY);
                } else {
                    foodImageView.setTranslateY(foodImageView.getTranslateY() - speed *food);
                }
                break;

        }
        k++;
    }

}
