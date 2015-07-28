package com.example.nns.ballgamev1;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by nns on 08/07/15.
 */
public class Background {

    private Bitmap image;
    private int x, y;
    private int speed = GamePanel.MOVESPEED;

    public Background(Bitmap img) {
        this.image = img;
    }

    public void update() {

        y += speed;

        if (y < -GamePanel.HEIGHT) {
            y = 0;
        }

    }

    public void draw(Canvas canvas) {

        if (y == GamePanel.HEIGHT) y = 0;

        canvas.drawBitmap(image, x, y, null);

        if (y > 0) {
            canvas.drawBitmap(image, x, y-GamePanel.HEIGHT, null);
        }

    }
}
