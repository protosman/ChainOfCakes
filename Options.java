package ru.takemakekeep.chainofcakes;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.MotionEvent;

import java.io.IOException;

public class Options {
    private Button[] buttons;
    private int mainPointX;
    private int maxX, maxY;
    protected boolean start;
    private int speedView;
    private int touchX, touchY;
    private  Image image;
    boolean sound, music;
    int resetPos;
//    SoundPool mSoundPool;
//    int pressBtn;

    Options(Button[] btns, Image img, int sizeDispX, int sizeDispY, Context context, boolean msc, boolean snd){
//        mSoundPool = new SoundPool(2, AudioManager.STREAM_MUSIC,0);
//        try {
//            pressBtn = mSoundPool.load(context.getAssets().openFd("button-press.ogg"), 1);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        image = img;
        maxX = sizeDispX;
        maxY = sizeDispY;
        start = false;
        buttons = btns;
        mainPointX = maxX;
        resetPos  = maxX;
        speedView = 1000;
        sound = snd;
        music = msc;

    }

    public void start(){
        resetPos = 0;
        mainPointX = 0;
        start = true;
       // buttons[0].setPosBtnX(buttons[0].getWidth()/20 + mainPointX);
        buttons[0].setPosBtnY(buttons[0].getHeight()/2);

       // buttons[1].setPosBtnX(buttons[1].getHeight());
        buttons[1].setPosBtnY(maxY -  buttons[1].getHeight() * 5 - buttons[1].getHeight() / 2);
       // buttons[2].setPosBtnX(maxX);
        buttons[2].setPosBtnY(maxY -  buttons[1].getHeight() * 5 - buttons[1].getHeight() / 2);

       // buttons[3].setPosBtnX(buttons[1].getHeight()*3);
        buttons[3].setPosBtnY(maxY -  buttons[1].getHeight() * 4 - buttons[1].getHeight() / 2);
      //  buttons[4].setPosBtnX(maxX);
        buttons[4].setPosBtnY(maxY -  buttons[1].getHeight() * 4 - buttons[1].getHeight() / 2);


      //  buttons[5].setPosBtnX((maxX - buttons[5].getWidth())/2);
        buttons[5].setPosBtnY(maxY -  buttons[1].getHeight() * 3 - buttons[1].getHeight() / 2);

      //  buttons[6].setPosBtnX(maxX/2);
        buttons[6].setPosBtnY(buttons[5].getPosY());

      //  buttons[7].setPosBtnX(maxX/2);
        buttons[7].setPosBtnY(buttons[5].getPosY());


    }

    public int getNumberOfElem() {
        return buttons.length + 1;
    }

    public boolean isVisible() {
        if (mainPointX == 0) {
            return true;
        }
        return false;
    }

    public boolean isStart() {
        return start;
    }

    public int getMainPointX() {
        return mainPointX;
    }

    public void next() {
        mainPointX = -maxX;

    }
    public void prev() {
        mainPointX = maxX;}

    public Bitmap getElem(int i) {
        if (i == 0) return image.getBitmap();
        i = i - 1;
        return buttons[i].getBitmap();
    }
    public float getPosX(int i) {
        if (i == 0) return (buttons[1].getHeight());
        i = i - 1;
        return buttons[i].getPosX();
    }
    public float getPosY(int i) {
        if (i == 0) return (maxY -  buttons[1].getHeight() * 5 - buttons[1].getHeight());
        i = i - 1;
        return buttons[i].getPosY();
    }

    public void update(short fps) {
        buttons[0].setPosBtnX(buttons[0].getWidth() / 5 + mainPointX);

        if(music) {
            buttons[1].setPosBtnX(maxX);
            buttons[2].setPosBtnX((maxX - buttons[2].getWidth())/2  + mainPointX);
        }
        else {
            buttons[1].setPosBtnX((maxX - buttons[1].getWidth())/2  + mainPointX);
            buttons[2].setPosBtnX(maxX);


        }
        if (sound) {
            buttons[3].setPosBtnX(maxX);
            buttons[4].setPosBtnX((maxX - buttons[4].getWidth())/2  + mainPointX);

        }
        else {
            buttons[3].setPosBtnX((maxX - buttons[3].getWidth())/2  + mainPointX);
            buttons[4].setPosBtnX(maxX);

        }


        buttons[5].setPosBtnX((maxX - buttons[5].getWidth())/2 + resetPos);

        buttons[6].setPosBtnX(maxX/2-buttons[6].getHeight() + resetPos + maxX);

        buttons[7].setPosBtnX(maxX/2 + resetPos + maxX);
    }

    public void touch(MotionEvent motionEvent) {
        if ((motionEvent.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_DOWN){
            touchX = (int) motionEvent.getX();
            touchY = (int) motionEvent.getY();
            for (int i = 0; i < buttons.length; i++) {
                buttons[i].touch(touchX,touchY);
//                if (buttons[i].pres && snd && pressBtn > 0) {
//                    mSoundPool.play(pressBtn, 1, 1, 0, 0, 1);
//                }
            }
        }

        if ((motionEvent.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP){
            touchX = (int) motionEvent.getX();
            touchY = (int) motionEvent.getY();
            for (int i = 0; i < buttons.length; i++) {
                buttons[i].unTouch(touchX,touchY);
            }
        }
    }
}
