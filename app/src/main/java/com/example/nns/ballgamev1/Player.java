package com.example.nns.ballgamev1;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by nns on 08/07/15.
 */
public class Player extends GameObject {

    private Bitmap sprite;
    private int score, bestScore;
    private boolean playing;
    private int speedX;
    private int speedY;
    //private Animation animation = new Animation();
    private long startTime;
    private int xMax, yMax;
    protected boolean playedOnce = false;

    public Player(Bitmap res, int w, int h, int ymax, int xmax) {
        xMax = xmax;
        yMax = ymax;
        x = (xMax/2)-(16);
        y = (int)(yMax - (yMax*0.15));
        dx = 0;
        dy = 0;
        score = 0;
        height = h;
        width = w;

        //Bitmap[] image = new Bitmap[numFrames];

        sprite = res;

        /*
        for (int i = 0, y = 0, counter = 0; counter < image.length; i++, counter++) {

            if (counter % numFX == 0) {
                y++;
                i = 0;
            }
            image[i] = Bitmap.createBitmap(sprite, i*width, y*height, width, height);
        }

        animation.setFrames(image);
        animation.setDelay(10);
        */

        startTime = System.nanoTime();
    }

    public void update() {

        long elapsedTime = (System.nanoTime()-startTime)/1000000;

        if (elapsedTime>100) {
            score++;
            startTime = System.nanoTime();
        }

        //animation.update();

        if (speedX != 0) {
            dx += speedX;
        }

        if (speedY != 0) {
            dy += speedY;
        }

        if (dx > 30) dx = 30;
        if (dx < -30) dx = -30;

        if (dy > 30) dy = 30;
        if (dy < -30) dy = -30;

        y += dy;
        x += dx;

        if (x < 0) {
            x = 0;
            dx = 0;
        } else if (x > xMax-64) {
            x = xMax-64;
            dx = 0;
        }

        if (y < 0) {
            y = 0;
            dy = 0;
        } else if (y > yMax-64) {
            y = yMax-64;
            dy = 0;
        }

    }

    public void draw(Canvas canvas) {
        //canvas.drawBitmap(animation.getImage(), x, y, null);
        canvas.drawBitmap(sprite, x, y, null);
    }

    public void incScore(int x) {score += x;}
    public int getScore(){ return score; }
    public boolean getPlaying() { return playing; }
    public void setPlaying(boolean b) { playing = b; }
    public void resetDX() { dx = 0; }
    public void resetScore() { score = 0; }
    public void resetPosition() {
        x = (xMax/2)-(32);
        y = (int)(yMax - (yMax*0.15));
    };
    public void setSpeedX(int sx) { this.speedX = sx;}
    public void setSpeedY(int sy) {this.speedY = sy;}
    public int getBestScore() {return bestScore;}
    public void setBestScore(int s) {bestScore = s;}
    public boolean getPlayedOnce() {return playedOnce;}
    public void setPlayedOnce(boolean b) {playedOnce = b;}

}
