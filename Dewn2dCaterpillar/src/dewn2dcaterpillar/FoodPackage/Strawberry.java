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
public class Strawberry implements Food{

    private ImageView strawberryView = new ImageView();
    private Image strawberry = new Image(getClass().getResourceAsStream("strawberry.png"));

    public ImageView getFood() {
        strawberryView.setImage(strawberry);
        return strawberryView;
    }

}
