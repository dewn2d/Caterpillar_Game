/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dewn2dcaterpillar;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dewn2d
 */
public class AboutFXMLController implements Initializable {

    private Stage stage;
    public Scene gameScene;
    public FXMLDocumentController gameController;

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextArea aboutText;
    
    @FXML
    private Button exitButton;
    
    @FXML
    private void exitButtonAction(ActionEvent event){
        System.exit(0);
    }
    
    @FXML
    private void backToGameAction(ActionEvent event){
        stage.setScene(gameScene);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        aboutText.setText("Hello! My name is Dwayne Williams and I am a Computer Engineering student"
                + " at th University of Missouri Columbia (Mizzou). For my final project in CS 3330 I created"
                + " this game. Feed The Hungry Caterpillar. This game started out as a snake clone with a theme"
                + " based on the popular childrens book 'The Hungry Caterpillar'. While completing this project"
                + " the things that gave me the most trouble assisted in giving this game an identity of its own"
                + " The game is simple to understand, you have one minute to guide this poor starving caterpillar"
                + " around to fill his tummy with as much food as possible. The faster the food the more its worth."
                + " also you can adjust the weight of the caterpillar the more he weights the slower the game. The game"
                + " is played using the arrow keys. The game can be paused by using the escape keys. Sorry the game does have bugs"
                + " but i hope you enjoy.");
    }

    public void start(Stage stage) {
        this.stage = stage;
    }

}
