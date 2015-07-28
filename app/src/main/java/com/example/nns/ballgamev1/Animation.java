package com.example.nns.ballgamev1;

import android.graphics.Bitmap;

/**
 * Created by nns on 08/07/15.
 */
public class Animation {

    private Bitmap[] frames;
    private int currentFrame;
    private long startTime, delay;
    private boolean playedOnce;


    public void setFrames(Bitmap[] frames) {
        this.frames = frames;
        currentFrame = 0;
        startTime = System.nanoTime();
    }

    public void setDelay(long d) { this.delay = d; };
    public void setFrame(int i) { currentFrame = i; };

    public Bitmap getImage() {
        return frames[currentFrame];
    }

    public int getFrame() { return currentFrame; };

    public void update() {
        long elapsedTime = (System.nanoTime()-startTime)/1000000;

        if (elapsedTime > delay) {
            currentFrame++;
            startTime = System.nanoTime();
        }

        if (currentFrame == frames.length) {
            currentFrame = 0;
            playedOnce = true;
        }
    }
}
