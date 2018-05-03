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
public class Orange implements Food {

    private ImageView orangeView = new ImageView();
    private Image orange = new Image(getClass().getResourceAsStream("orange.png"));

    public ImageView getFood() {
        orangeView.setImage(orange);
        return orangeView;
    }

}