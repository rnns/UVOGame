package com.example.nns.ballgamev1;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by nns on 08/07/15.
 */
public class MainThread extends Thread {

    private int FPS = 30;
    private double averageFPS;
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;
    public static Canvas canvas;

    public MainThread(SurfaceHolder sH, GamePanel gP) {
        super();
        this.surfaceHolder = sH;
        this.gamePanel = gP;
    }

    @Override
    public void run() {

        long startTime, elapsedTime, waitTime;
        long totalTime = 0, frameCount = 0;
        long targetTime = 1000/FPS;

        while(running) {
            startTime = System.nanoTime();
            canvas = null;

            // lock the canvas for pixel editing
            try {

                canvas = this.surfaceHolder.lockCanvas();

                synchronized (surfaceHolder) {

                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);

                }
            } catch (Exception e) {

                e.printStackTrace();

            } finally {

                if (canvas != null) {
                    try {

                        this.surfaceHolder.unlockCanvasAndPost(canvas);

                    } catch (Exception e) {

                        e.printStackTrace();

                    }
                }
            }



            elapsedTime = (startTime-System.nanoTime())/1000000;

            waitTime = targetTime - elapsedTime;

            try {
                this.sleep(waitTime);
            } catch (Exception e) {}

            totalTime += System.nanoTime()-startTime;
            frameCount++;

            if (frameCount == FPS) {
                averageFPS = 1000/((totalTime/frameCount)/1000000);
                frameCount = 0;
                totalTime = 0;
                System.out.println("Average FPS: "+averageFPS);
            }
        }
    }

    public void setRunning(boolean t) {
        this.running = t;
    }
}
