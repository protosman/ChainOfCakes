package ru.takemakekeep.chainofcakes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class GameEngine extends SurfaceView implements Runnable {
    int sizeX,sizeY;
    Thread gameThread = null;
    SurfaceHolder gameHolder;
    volatile boolean playing;
    boolean paused = false;
    Canvas canvas;
    Paint paint;
    ViewFieldAndAllElem viewFieldAndAllElem;
    short fps;
    private long timeThisFrame;
    String site = "www.TMKstd.ru";


    GameEngine(Context context, int sizeDispX, int sizeDispY, int cntrs, boolean music, boolean sound){
        super(context);

        Log.d("GameEngine", "1");

        gameHolder = getHolder();
        paint = new Paint();

        sizeY = sizeDispY;
        sizeX = sizeDispX;
        viewFieldAndAllElem = new ViewFieldAndAllElem(context, sizeDispX, sizeDispY, cntrs, music,sound);

        Log.d("GameEngine", "2");


    }

    @Override
    public void run() {
        while (playing) {
            long startFrameTime = System.currentTimeMillis();
            if(!paused) {
                update();
            }
            draw();

            timeThisFrame = System.currentTimeMillis() - startFrameTime;
            if(timeThisFrame > 0) {
                fps = (short) (1000 / timeThisFrame);
            }
//            Log.d("FPSS",""+fps);


        }

    }
    public void update() {
        viewFieldAndAllElem.update(fps);
    }
    public void draw() {
        if(gameHolder.getSurface().isValid()) {
            canvas = gameHolder.lockCanvas();
            paint.setColor(Color.argb(255,0,0,0));
            paint.setTextSize(30);
            canvas.drawBitmap(viewFieldAndAllElem.background, 0,0,null);
            if(viewFieldAndAllElem.cookMan!=null){
                canvas.drawBitmap(viewFieldAndAllElem.cookMan.getBitmap(),
                        viewFieldAndAllElem.cookMan.getPosX(),
                        viewFieldAndAllElem.cookMan.getPosY(),
                        null);
            }

            if (viewFieldAndAllElem.mainMenuView.isVisible()) {
                for (int j = 0; j < viewFieldAndAllElem.mainMenuView.getNumberOfElem(); j++){
                    if(viewFieldAndAllElem.mainMenuView.getElem(j) != null) {
                        canvas.drawBitmap(viewFieldAndAllElem.mainMenuView.getElem(j),
                                viewFieldAndAllElem.mainMenuView.getPosX(j),
                                viewFieldAndAllElem.mainMenuView.getPosY(j),
                                null);
                    }
                }
            }
            if (viewFieldAndAllElem.editorLevelView.isVisible()) {
                for (int j = 0; j < viewFieldAndAllElem.editorLevelView.getNumberOfElem(); j++){
                    if(viewFieldAndAllElem.editorLevelView.getElem(j) != null) {
                        canvas.drawBitmap(viewFieldAndAllElem.editorLevelView.getElem(j),
                                viewFieldAndAllElem.editorLevelView.getPosX(j),
                                viewFieldAndAllElem.editorLevelView.getPosY(j),
                                null);
                    }
                }
            }
            if (viewFieldAndAllElem.gameField.isVisible()) {
                for (int j = 0; j < viewFieldAndAllElem.gameField.getNumberOfElem(); j++){
                    if(viewFieldAndAllElem.gameField.getElem(j) != null) {
                        canvas.drawBitmap(viewFieldAndAllElem.gameField.getElem(j),
                                viewFieldAndAllElem.gameField.getPosX(j),
                                viewFieldAndAllElem.gameField.getPosY(j),
                                null);
                    }
                }
            }
            if (viewFieldAndAllElem.credits.isVisible()) {
                for (int j = 0; j < viewFieldAndAllElem.credits.getNumberOfElem(); j++){
                    if(viewFieldAndAllElem.credits.getElem(j) != null) {
                        canvas.drawBitmap(viewFieldAndAllElem.credits.getElem(j),
                                viewFieldAndAllElem.credits.getPosX(j),
                                viewFieldAndAllElem.credits.getPosY(j),
                                null);
                    }
                }
                canvas.drawText(site, sizeX/2-(int)(paint.measureText(site)/2) ,viewFieldAndAllElem.credits.getPosY(0) + viewFieldAndAllElem.normalSizeBtn, paint);
                Log.d("GameEngine", " x= " + (int)(paint.measureText(site)/2) + " y= " + viewFieldAndAllElem.credits.getPosY(0) + viewFieldAndAllElem.normalSizeBtn);
            }
            if (viewFieldAndAllElem.options.isVisible()) {
                for (int j = 0; j < viewFieldAndAllElem.options.getNumberOfElem(); j++){
                    if(viewFieldAndAllElem.options.getElem(j) != null) {
                        canvas.drawBitmap(viewFieldAndAllElem.options.getElem(j),
                                viewFieldAndAllElem.options.getPosX(j),
                                viewFieldAndAllElem.options.getPosY(j),
                                null);
                    }
                }
            }


//--------------FPS-----------------
           // canvas.drawText("FPS: " + fps, 20 ,40, paint);
            gameHolder.unlockCanvasAndPost(canvas);
        }
    }


    public void pause() {
        playing = false;
        viewFieldAndAllElem.pause();
        try {
            gameThread.join();
        } catch (Exception e) {
            Log.e("Error joining thread  ", e.toString());
        }
    }
    public void resume(int counter,float level, int[] blocks,int maxCounter) {
//        viewFieldAndAllElem.setCounter(counter);
//        viewFieldAndAllElem.resume(level, blocks, maxCounter);
        Log.e("Error joining thread  ", "GEresume");
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        viewFieldAndAllElem.motion(motionEvent);
        return true;
    }
}
