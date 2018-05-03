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
public class Icecream implements Food {

    private ImageView icecreamView = new ImageView();
    private Image icecream = new Image(getClass().getResourceAsStream("icecream.png"));

    public ImageView getFood() {
        icecreamView.setImage(icecream);
        return icecreamView;
    }

}
