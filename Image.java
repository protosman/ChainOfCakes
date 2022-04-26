package ru.takemakekeep.chainofcakes;;

import android.graphics.Bitmap;
import android.graphics.Rect;

public class Image {
    protected Bitmap normal;
    protected Rect rectBtn;

    Image (Bitmap bmNorm) {
        normal = bmNorm;
        rectBtn = new Rect(0,0,normal.getWidth(),normal.getHeight());
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
        return normal;
    }
    protected void setBitmap(Bitmap bitmap) {
        normal = bitmap;
    }

    protected int getWidth() {
        return normal.getWidth();
    }
    protected int getHeight() {
        return normal.getHeight();
    }
}
