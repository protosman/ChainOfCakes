package ru.takemakekeep.chainofcakes;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;
import android.view.MotionEvent;

import java.io.IOException;

public class MainMenuView{
    private Button[] buttons;
    private int mainPointX;
    private int mainPointXNeed;
    private int maxX, maxY;
    protected boolean start;
    private int speedView;
    private int touchX, touchY;
    private Image[] imgNum;
    int counter;
//    SoundPool mSoundPool;
//    int pressBtn;

    MainMenuView(Button[] btns, int sizeDispX, int sizeDispY, Image[] imageNum, int cntr, Context context){
//        mSoundPool = new SoundPool(2, AudioManager.STREAM_MUSIC,0);
//        try {
//            pressBtn = mSoundPool.load(context.getAssets().openFd("button-press.ogg"), 1);
//        } catch (IOException e) {
//            Log.d("MainMenu", "load exc " + e);
//            e.printStackTrace();
//        }

        maxX = sizeDispX;
        maxY = sizeDispY;
        start = false;
        buttons = btns;
        mainPointX = 0;
        mainPointXNeed = mainPointX;
        speedView = 1000;
        imgNum = imageNum;
        counter = cntr;
        if ( counter == 0) {
            buttons[0].setPosBtnY(maxY);
            buttons[1].setPosBtnY(maxY - buttons[0].getHeight() * 6);
        }
        else {
            buttons[1].setPosBtnY(maxY);
            buttons[0].setPosBtnY(maxY - buttons[0].getHeight() * 6);
        }

        buttons[0].setPosBtnX((maxX + mainPointX - buttons[0].getWidth()) / 2 + mainPointX);
        buttons[1].setPosBtnX((maxX + mainPointX - buttons[0].getWidth()) / 2 + mainPointX);
        for(int i = 2; i < buttons.length; i++) {
            buttons[i].setPosBtnX((maxX + mainPointX - buttons[0].getWidth()) / 2 + mainPointX);
            buttons[i].setPosBtnY(maxY - buttons[0].getHeight() * (6 - i + 1));
        }
    }


    public void start(){
        mainPointXNeed = 0;
        mainPointX = 0;
        start = true;
    }
    public int getNumberOfElem() {
        return buttons.length + 5 + 1;
    }


    public boolean isVisible() {
        return true;
    }

    public boolean isStart() {
        return start;
    }

    public int getMainPointX() {
        return mainPointX;
    }

    public void next() {
        mainPointXNeed = -maxX;
        mainPointX = -maxX;

    }
    public void prev() {
        mainPointXNeed = 0;
        mainPointX = 0;}

    public Bitmap getElem(int i) {
        if(i==0) {
            return imgNum[10].getBitmap();
        }
        i = i - 1;

        if (i < 5) {
            switch (i) {
                case 0:
                    return  imgNum[(int)(counter/10000)].getBitmap();
                case 1:
                    return  imgNum[(int)(counter%10000/1000)].getBitmap();
                case 2:
                    return  imgNum[(int)(counter%1000/100)].getBitmap();
                case 3:
                    return  imgNum[(int)(counter%100/10)].getBitmap();
                case 4:
                    return  imgNum[(int)(counter%10)].getBitmap();
                default: return null;
            }
        }
        i = i - 5;
        return buttons[i].getBitmap();
    }
    public float getPosX(int i) {
        if(i==0) {
            return buttons[2].getPosX() - buttons[2].getHeight();
        }
        i = i - 1;
        if (i < 5) {
            switch (i) {
                case 0:
                    return  maxX/2 - imgNum[0].getBitmap().getWidth() * 25/10;
                case 1:
                    return  maxX/2 - imgNum[0].getBitmap().getWidth() * 15/10;
                case 2:
                    return  maxX/2 - imgNum[0].getBitmap().getWidth() / 2;
                case 3:
                    return maxX/2 + imgNum[0].getBitmap().getWidth() /2;
                case 4:
                    return  maxX/2 + imgNum[0].getBitmap().getWidth() * 15/10;
                default: return 0;
            }
        }
        i = i - 5;
        return buttons[i].getPosX();
    }
    public float getPosY(int i) {
        if(i==0) {
            return buttons[2].getPosY() - buttons[2].getHeight() - buttons[2].getHeight()/2;
        }
        i = i - 1;
        if (i < 5) return imgNum[0].getBitmap().getHeight();
        i = i - 5;
        return buttons[i].getPosY();
    }

    public void update(short fps) {
        if (counter == 0) {
            buttons[0].setPosBtnY(maxY);
            buttons[1].setPosBtnY(maxY - buttons[0].getHeight() * 6);
        }
        else {
            buttons[1].setPosBtnY(maxY);
            buttons[0].setPosBtnY(maxY - buttons[0].getHeight() * 6);
        }
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setPosBtnX((maxX - buttons[0].getWidth()) / 2 + mainPointX);
        }
        if (mainPointX < mainPointXNeed) {
            if (mainPointXNeed - mainPointX > 15) {
                mainPointX = mainPointX + speedView / fps;
            } else {
                mainPointX = mainPointX + 1;
            }
        }
        if (mainPointX > mainPointXNeed) {

            if (mainPointX - mainPointXNeed > 15) {
                mainPointX = mainPointX - speedView / fps;
            } else {
                mainPointX = mainPointX - 1;
            }
        }
    }

    public void touch(MotionEvent motionEvent) {
        if ((motionEvent.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_DOWN){
            touchX = (int) motionEvent.getX();
            touchY = (int) motionEvent.getY();
            for (int i = 0; i < buttons.length; i++) {
                buttons[i].touch(touchX,touchY);
//                if (buttons[i].pres && snd && pressBtn > 0) {
//                    Log.d("MainMenu", " " + pressBtn);
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
