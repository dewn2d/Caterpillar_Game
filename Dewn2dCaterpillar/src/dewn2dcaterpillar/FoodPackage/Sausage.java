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
public class Sausage implements Food{

    private ImageView sausageView = new ImageView();
    private Image sausage = new Image(getClass().getResourceAsStream("meat2.png"));

    public ImageView getFood() {
        sausageView.setImage(sausage);
        return sausageView;
    }

}
