package ru.takemakekeep.chainofcakes;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.MotionEvent;

public class Cook {
    int posX;
    int posY;
    int maxX;
    int maxY;
    private int touchDownX, touchDownY;
    private int touchUpX, touchUpY;
    Image[][] images;
    int state;
    int animBitmap;
    int animNum;
    public final int STOPPED = 0;
    public final int UP = 1;
    public final int RIGHT = 2;
    public final int DOWN = 3;
    public final int LEFT = 4;
    Cook(Image[][] imgs, int x, int y) {
        maxX = x;
        maxY = y;
        images = imgs;
        animBitmap = 0;
        posX = maxX/2;
        posY = maxY*10/27;
        state = (int)(Math.random()*3+1);
    }
    void update(int fps) {
        if (animNum<=0){
            animNum = 30;
        }
        if(fps>0) {
            if((animNum==27 || animNum==13) && Math.random()<0.3){
                state = (int)(Math.random()*3+1);
            }
            switch (state) {
                case LEFT:
//                        if(posX>-images[0][0].getWidth()) {
                    if(posX>0) {
                        if(animBitmap != 0)  posX = posX - maxX / (3 * fps);
                    }
                    else {
                        double a = Math.random();
                        if(a<0.4) state = UP;
                        else if(a<0.8) state = DOWN;
                        else state = RIGHT;
                    }
                    break;
                case RIGHT:
//                        if(posX < maxX) {
                    if(posX < maxX-images[0][0].getWidth()) {
                        if(animBitmap != 0) posX = posX + maxX / (3 * fps);
                    }
                    else {
                        double a = Math.random();
                        if(a<0.4) state = UP;
                        else if(a<0.8) state = DOWN;
                        else state = LEFT;
                    }
                    break;
                case UP:
                    if(posY>maxY*10/32) {
                        if(animBitmap != 0) posY = posY - maxX / (3 * fps);
                    }
                    else {
                        double a = Math.random();
                        if(a<0.4) state = LEFT;
                        else if (a<0.8)state = RIGHT;
                        else state = DOWN;
                    }
                    break;
                case DOWN:
                    if(posY<maxY*100/236) {
                        if(animBitmap != 0) posY = posY + maxX / (3 * fps);
                    }
                    else {
                        double a = Math.random();
                        if(a<0.4) state = LEFT;
                        else if (a<0.8)state = RIGHT;
                        else state = UP;
                    }
                    break;
                default:
                    state = LEFT;
                    break;
            }
        }
        animNum--;
        if(animNum>25 || animNum>10&& animNum<16){
            animBitmap = 0;
        }
        else if (animNum>15){
            animBitmap = 1;
        }
        else animBitmap = 2;
    }
    int getPosX(){
        return posX;
    }
    int getPosY() {
        return posY;
    }
    public void touch(MotionEvent motionEvent) {
        if ((motionEvent.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_DOWN){
            touchDownX = (int) motionEvent.getX();
            touchDownY = (int) motionEvent.getY();
        }
        if ((motionEvent.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP){
            touchUpX = (int) motionEvent.getX();
            touchUpY = (int) motionEvent.getY();
            if ((Math.abs(touchDownX - touchUpX) > 20 || Math.abs(touchDownY - touchUpY) > 20)) {
                animNum=26;
                if (Math.abs(touchDownX - touchUpX) > Math.abs(touchDownY - touchUpY)) {
                    if (touchDownX > touchUpX) {
                        state = LEFT;
                    } else {
                        state = RIGHT;
                    }
                } else {
                    if (touchDownY > touchUpY) {
                        state = UP;
                    } else {
                        state = DOWN;
                    }
                }
            }

        }
    }
    Bitmap getBitmap() {
        return images[state-1][animBitmap].getBitmap();
    }
}
