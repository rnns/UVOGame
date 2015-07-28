package com.example.nns.ballgamev1;

import android.graphics.Rect;

/**
 * Created by nns on 08/07/15.
 */
public abstract class GameObject {

    protected int x, y;
    protected int dx, dy;
    protected int width, height;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Rect getRectangle() {

        return new Rect(x, y, x+width, y+height);

    }

}
