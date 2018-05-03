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
public class Blueberry implements Food {

    private ImageView blueberryView = new ImageView();
    private Image blueberry = new Image(getClass().getResourceAsStream("blueberry.png"));

    public ImageView getFood() {
        blueberryView.setImage(blueberry);
        return blueberryView;
    }
    
}
