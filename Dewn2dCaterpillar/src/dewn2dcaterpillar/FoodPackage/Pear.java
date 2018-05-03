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
public class Pear implements Food {

    private ImageView pearView = new ImageView();
    private Image pear = new Image(getClass().getResourceAsStream("pear.png"));

    public ImageView getFood() {
        pearView.setImage(pear);
        return pearView;
    }

}
