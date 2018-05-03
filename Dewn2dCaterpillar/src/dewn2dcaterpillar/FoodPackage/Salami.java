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
public class Salami implements Food {

    private ImageView salamiView = new ImageView();
    private Image salami = new Image(getClass().getResourceAsStream("meat.png"));

    public ImageView getFood() {
        salamiView.setImage(salami);
        return salamiView;
    }

}
