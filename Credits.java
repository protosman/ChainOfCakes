package ru.takemakekeep.chainofcakes;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.MotionEvent;

import java.io.IOException;

public class Credits{

    private Button button;
    private int mainPointX;
    private int maxX, maxY;
    protected boolean start;
    private int touchX, touchY;
    private  Image image;
//    SoundPool mSoundPool;
//    int pressBtn;

    Credits(Button btn, Image img, int sizeDispX, int sizeDispY, Context context) {
//        mSoundPool = new SoundPool(2, AudioManager.STREAM_MUSIC,0);
//        try {
//            pressBtn = mSoundPool.load(context.getAssets().openFd("button-press.ogg"), 1);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        button = btn;
        image = img;
        maxX = sizeDispX;
        maxY = sizeDispY;
        start = false;
        mainPointX = maxX;
    }

    public void start(){
        mainPointX = 0;
        start = true;
        button.setPosBtnY(button.getHeight()/2);
     //   image.setPosBtnY(maxY - image.getHeight() - image.getHeight()/4);
        image.setPosBtnY(maxY - (image.getHeight() /4) * 6);
    }
    public void update(short fps) {
        button.setPosBtnX(button.getWidth() / 5 + mainPointX);
        image.setPosBtnX(maxX/6 + mainPointX);
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

    public void prev() {
        mainPointX = maxX;}

    public void touch(MotionEvent motionEvent) {
        if ((motionEvent.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_DOWN){
            touchX = (int) motionEvent.getX();
            touchY = (int) motionEvent.getY();
                button.touch(touchX,touchY);
//                if (button.pres && snd && pressBtn > 0) {
//                    mSoundPool.play(pressBtn, 1, 1, 0, 0, 1);
//                }

        }

        if ((motionEvent.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP){
            touchX = (int) motionEvent.getX();
            touchY = (int) motionEvent.getY();
            button.unTouch(touchX,touchY);
        }
    }

    public int getNumberOfElem() {
        return 2;
    }

    public Bitmap getElem(int i) {
        if (i == 0) return image.getBitmap();
        return button.getBitmap();
    }

    public float getPosX(int i) {
        if (i == 0) return image.getPosX();
        return button.getPosX();
    }

    public float getPosY(int i) {
        if (i == 0) return image.getPosY();
        return button.getPosY();
    }
}
