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
public class Apple implements Food {

    private ImageView appleView = new ImageView();
    private Image apple = new Image(getClass().getResourceAsStream("apple.png"));

    @Override
    public ImageView getFood() {
        appleView.setImage(apple);
        return appleView;
    }
    
}
