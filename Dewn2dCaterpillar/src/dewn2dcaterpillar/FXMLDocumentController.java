/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dewn2dcaterpillar;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class FXMLDocumentController implements Initializable {

    private gameTimer timer;
    private int k = 0;
    private int fScore;
    private Stage stage;
    private Scene gameScene;
    private Scene aboutScene;
    private Scene leaderScene;
    private AboutFXMLController aboutController;
    private LeaderboardFXMLController leaderController;
    private caterpillarModel caterpillar;
    private static DecimalFormat two = new DecimalFormat("00");
    @FXML
    private AnchorPane board;

    @FXML
    private AnchorPane menu;

    @FXML
    private Button startButton;

    @FXML
    private Text timerText;

    @FXML
    private Text scoreText;

    @FXML
    private Text weightText;

    @FXML
    private Text menuText;

    @FXML
    private Text finalScore;

    @FXML
    private Text finalScoreBanner;

    @FXML
    private void upAction(ActionEvent event) {
        System.out.println("I SAID DONT CLICK!!!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        finalScore.setVisible(false);
        finalScoreBanner.setVisible(false);
        timer = new gameTimer() {
        };
        caterpillar = new caterpillarModel(board);
        setCaterpillar();
        setTimer();
        caterpillar.updateBody();
        board = caterpillar.getBoard();
    }

    @FXML
    private void exitButtonAction(ActionEvent event) {
        System.exit(0);
    }

    public void start(Stage stage) {
        this.stage = stage;
        gameScene = stage.getScene();
    }

    @FXML
    private void resumeAction(ActionEvent event) {
        if (!caterpillar.isRunning()) {
            menu.setVisible(false);
            startButton.setText("Resume");
            timer.Start();
            caterpillar.start();
        }
    }

    @FXML
    private void difficultyAction(ActionEvent event) {
        caterpillar.updateBody();
        weightText.setText("Weight: " + Integer.toString(caterpillar.getWeight()));
        board = caterpillar.getBoard();
    }

    @FXML
    private void aboutAction(ActionEvent event) throws IOException {

        try {
            if (aboutScene == null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AboutFXML.fxml"));
                Parent aboutRoot = loader.load();

                aboutController = loader.getController();
                aboutController.gameScene = gameScene;
                aboutController.gameController = this;
                aboutScene = new Scene(aboutRoot);

            }

            stage.setScene(aboutScene);
            aboutController.start(stage);

        } catch (IOException ex) {

        }

    }

    @FXML
    private void leaderAction(ActionEvent event) throws IOException {

        try {
            if (leaderScene == null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("LeaderboardFXML.fxml"));
                Parent leaderRoot = loader.load();

                leaderController = loader.getController();
                leaderController.gameScene = gameScene;
                leaderController.gameController = this;
                leaderController.boardScore = finalScore.getText();
                leaderScene = new Scene(leaderRoot);

            }

            stage.setScene(leaderScene);
            leaderController.start(stage);

        } catch (IOException ex) {

        }

    }

    @FXML
    private void keyPressed(KeyEvent event) {

        switch (event.getCode()) {
            case UP:
                if (caterpillar.getCurrDirection() != Direction.DOWN) {
                    caterpillar.setCurrDirection(Direction.UP);
                }
                break;
            case DOWN:
                if (caterpillar.getCurrDirection() != Direction.UP) {
                    caterpillar.setCurrDirection(Direction.DOWN);
                }
                break;
            case RIGHT:
                if (caterpillar.getCurrDirection() != Direction.LEFT) {
                    caterpillar.setCurrDirection(Direction.RIGHT);
                }
                break;
            case LEFT:
                if (caterpillar.getCurrDirection() != Direction.RIGHT) {
                    caterpillar.setCurrDirection(Direction.LEFT);
                }
                break;
            case ENTER:
                if (!caterpillar.isRunning()) {
                    menu.setVisible(false);
                    startButton.setText("Resume");
                    timer.Start();
                    caterpillar.start();
                }
                break;
            case ESCAPE:
                if (caterpillar.isRunning()) {
                    finalScore.setVisible(false);
                    finalScoreBanner.setVisible(false);
                    menuText.setText("PAUSED");
                    caterpillar.stop();
                    timer.Stop();
                    menu.setVisible(true);
                }
                break;
        }

    }

    public void reset() {

        finalScore.setVisible(true);
        finalScoreBanner.setVisible(true);
        finalScore.setText(Integer.toString(caterpillar.getScore()));
        menuText.setText("GAME OVER");
        caterpillar.stop();
        timer.Stop();
        startButton.setText("New Game");
        menu.setVisible(true);
        timer = new gameTimer() {
        };
        caterpillar = new caterpillarModel(board);
        setCaterpillar();
        setTimer();
        fScore = caterpillar.getScore();
        caterpillar.updateBody();
        board = caterpillar.getBoard();
    }

    private void setTimer() {

        timer.setKeyFrame(new KeyFrame(Duration.millis(1000), (ActionEvent event1) -> {
            if (timer.getStatus()) {
                reset();
            } else {
                timer.callUpdate();
                timerText.setText(two.format(timer.getTime()));
            }
        }));

        Timeline temp2 = new Timeline(timer.getKeyFrame());
        timer.setTimeLine(temp2);
        timer.getTimeLine().setCycleCount(Animation.INDEFINITE);
    }

    private void setCaterpillar() {

        board = caterpillar.randFood();

        caterpillar.setKeyFrame(new KeyFrame(Duration.millis(1), (ActionEvent event1) -> {

            k = k % caterpillar.getBodyList().size();
            board = caterpillar.getBoard();

//            System.out.println((int)bodyList.get(0).getTranslateX() + " and " + (int)currFoodX);
            caterpillar.getDirectChain().get(k).add(caterpillar.getCurrDirection());
            caterpillar.callUpdate(k);
            scoreText.setText("Score: " + Integer.toString(caterpillar.getScore()));
            k++;

        }));

        Timeline temp = new Timeline(caterpillar.getKeyFrame());
        caterpillar.setTimeLine(temp);
        caterpillar.getTimeLine().setCycleCount(Animation.INDEFINITE);

    }

}
