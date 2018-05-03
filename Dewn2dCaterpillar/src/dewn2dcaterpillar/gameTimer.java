/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dewn2dcaterpillar;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

/**
 *
 * @author dewn2d
 */
public abstract class gameTimer {

    private Timeline timeline;
    private KeyFrame keyFrame;
    private double timeSeconds = 0.0f;
    private double tickTimeInSeconds = 1.0f;
    private boolean done;

    public gameTimer() {

    }

    private void update() {

        if (timeSeconds == 59) {
            done = true;
        } else {
            timeSeconds += tickTimeInSeconds;
        }

    }
    
    public boolean getStatus(){
        return done;
    }

    public void Start() {
        timeline.play();
    }

    public void Stop() {
        timeline.stop();
    }

    public void callUpdate() {
        update();
    }

    public double getTime() {
        return timeSeconds;
    }

    public void setTimeLine(Timeline input) {
        timeline = input;
    }

    public Timeline getTimeLine() {
        return timeline;
    }

    public void setKeyFrame(KeyFrame input) {
        keyFrame = input;
    }

    public KeyFrame getKeyFrame() {
        return keyFrame;
    }

}
