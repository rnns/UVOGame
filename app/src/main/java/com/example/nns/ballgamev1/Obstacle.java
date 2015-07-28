package com.example.nns.ballgamev1;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;

/**
 * Created by nns on 16/07/15.
 */
public class Obstacle extends GameObject {

    private Bitmap sprite;
    private Random rand = new Random();
    private int speed;

    public Obstacle(int x, int y, int w, int h, int score, Bitmap res) {
        super.x = x;
        super.y = y;
        width = w;
        height = h;

        speed = 7 + (int) (rand.nextDouble()*score/30);

        // limit speed if necessary

        sprite = res;

    }

    public void update() {
        y += speed;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(sprite, x, y, null);
    }


}
