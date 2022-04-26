package ru.takemakekeep.chainofcakes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.Log;
import android.media.AudioManager;
import android.media.SoundPool;

import java.io.IOException;

public class Button {
    protected Bitmap normal;
    protected Bitmap pressed;
    protected Rect rectBtn;
    protected boolean pres;
    protected boolean tripped;
    protected int touchX,touchY;

    Button(Bitmap bmNorm, Bitmap bmPres) {

        normal = bmNorm;
        pressed = bmPres;
        rectBtn = new Rect(0,0,normal.getWidth(),normal.getHeight());
        pres = false;
        tripped = false;
    }

    protected void setPosBtnX(int x){
        rectBtn.left = x;
        rectBtn.right = x + normal.getWidth();
    }
    protected void setPosBtnY(int y) {
        rectBtn.top = y;
        rectBtn.bottom = y + normal.getHeight();
    }
    protected int getPosX() {
        return rectBtn.left;
    }
    protected int getPosY() {
        return rectBtn.top;
    }
    protected Bitmap getBitmap() {
        if (pres) return pressed;
        return normal;
    }
    protected void touch(int x, int y) {
        touchX = x;
        touchY = y;
        if (rectBtn.contains(x, y)) {
            pres = true;
        }
        else pres = false;
    }
    protected void unTouch(int x, int y) {

        if (rectBtn.contains(x, y)&&Math.abs(touchX-x)<normal.getWidth()/2) {
            tripped = true;
        }
        else {
            tripped = false;
        }
        pres = false;
    }

    protected int getWidth() {
        return normal.getWidth();
    }
    protected int getHeight() {
        return normal.getHeight();
    }
}
