package com.example.nns.ballgamev1;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by nns on 08/07/15.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    protected static final int WIDTH = 600;
    protected static final int HEIGHT = 800;
    protected static final int MOVESPEED = 20;
    private MainThread gameThread;
    private Background background;
    private Player player;
    private ArrayList<Obstacle> obstacles;
    private long obstacleTimer;
    private Random rand = new Random();

    public GamePanel(Context context) {

        super(context);

        // add the callback to the surfaceholder to intercept events
        getHolder().addCallback(this);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        // set image of the background
        background = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.bg));

        //instantiate the player
        player = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.ufoo), 32, 32, getHeight(), getWidth());

        // instantiate obstacles
        obstacles = new ArrayList<Obstacle>();

        gameThread = new MainThread(holder, this);

        // start the game thread
        gameThread.setRunning(true);
        gameThread.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {


    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

        // stop the game thread
        boolean retry = true;

        while (retry) {

            try {

                gameThread.setRunning(false);
                gameThread.join();
                retry = false;
                gameThread = null;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            if (!player.getPlaying()) {

                player.setPlaying(true);

                player.resetScore();

                obstacles.add(new Obstacle((getWidth()/2)-16, 0, 32, 32, 0, BitmapFactory.decodeResource(getResources(), R.drawable.comet)));

                obstacleTimer = System.nanoTime();

            }

            return true;

        }

        return super.onTouchEvent(event);
    }

    public void update() {

        if (player.getPlaying()) {

            background.update();
            player.update();

            for(int i = 0; i < obstacles.size(); i++) {

                obstacles.get(i).update();

                if (collision(player, obstacles.get(i))) {

                    // if collided, reset all player status and restart from the beginning
                    // also clears the obstacles vector

                    obstacles.clear();
                    player.resetPosition();
                    player.resetDX();
                    player.setPlaying(false);
                    player.setPlayedOnce(true);

                    if (player.getScore() > player.getBestScore()) {
                        player.setBestScore(player.getScore());
                    }

                    break;

                } else {

                    if (obstacles.get(i).getY() > getHeight()) {
                        player.incScore(10);
                        obstacles.remove(i);
                        i--;
                    }

                }
            }

            if (((System.nanoTime() - obstacleTimer)/1000000) > 2000-player.getScore()/4) {

                int n = rand.nextInt(getWidth());

                if (n > (getWidth()-32)) n = getWidth()-32;

                obstacles.add(new Obstacle(n, 0, 32, 32, player.getScore(), BitmapFactory.decodeResource(getResources(), R.drawable.comet)));

                obstacleTimer = System.nanoTime();
            }
        }

    }

    public void draw(Canvas canvas) {

        final float scaleFactorX = getWidth()/(WIDTH);
        final float scaleFactorY = getHeight()/(HEIGHT);

        if (canvas != null) {

            final int savedState = canvas.save();

            canvas.scale(scaleFactorX, scaleFactorY);

            background.draw(canvas);

            player.draw(canvas);

            if (!player.getPlaying() && !player.getPlayedOnce()) {
                drawStartingText(canvas);
            } else if (!player.getPlaying()) {
                drawLoserText(canvas);
            } else {
                drawScore(canvas);
            }

            for (int i = 0; i < obstacles.size(); i++) {
                obstacles.get(i).draw(canvas);
            }

            canvas.restoreToCount(savedState);

        }

    }

    public void speedX(int speedx) {
        player.setSpeedX(speedx);
    }
    public void speedY(int speedy) {player.setSpeedY(speedy);}

    public boolean collision(GameObject a, GameObject b) {
        return Rect.intersects(a.getRectangle(), b.getRectangle());

    }

    public void drawStartingText(Canvas canvas) {

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(30);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        canvas.drawText("Bem vindo!", WIDTH / 2 - 80, HEIGHT / 2, paint);
        canvas.drawText("Para jogar, pressione a tela.", WIDTH/2-80, HEIGHT/2 + 40, paint);
        canvas.drawText("Movemente-se mexendo o celular!", WIDTH/2-80, HEIGHT/2 + 80, paint);

    }

    public void drawLoserText(Canvas canvas) {

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(40);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        canvas.drawText("Você Perdeu!", WIDTH / 2 - 80, HEIGHT / 2, paint);
        canvas.drawText("Last Score: "+player.getScore(), WIDTH/2-80, HEIGHT/2 + 40, paint);
        canvas.drawText("Best Score: "+player.getBestScore(), WIDTH/2-80, HEIGHT/2 + 80, paint);

    }

    public void drawScore(Canvas canvas) {

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(30);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        canvas.drawText("Score: "+player.getScore(), WIDTH-50, 50, paint);

    }

}
