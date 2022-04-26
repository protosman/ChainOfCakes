package ru.takemakekeep.chainofcakes;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;
import android.view.MotionEvent;

import java.io.IOException;

public class EditorLevelView{
    private Button[] buttons;
    private Image[] images;
    protected boolean isVisible;
    protected int mainPointX;
    protected int needPointX, pointX;
    protected int starNeedPointX;
    private int maxX, maxY;
    protected boolean start;
    private int speedView;
    private int touchX, touchY;
    protected int counter;
//
//    SoundPool mSoundPool;
//    int pressBtn;

    EditorLevelView (Button[] buttons, Image[] images, int sizeDispX, int sizeDispY, int cntr, Context context) {
//        mSoundPool = new SoundPool(2, AudioManager.STREAM_MUSIC,0);
//        try {
//            pressBtn = mSoundPool.load(context.getAssets().openFd("button-press.ogg"), 1);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        counter = cntr;
//        numStartBlock = 10;
//        numOfColor = 3;
//        numNewBlockInStep = 2;
//        numMaxBlockInGrps = 3;
        maxX = sizeDispX;
        maxY = sizeDispY;
        start = false;
        isVisible = false;
        this.buttons = buttons;
        this.images = images;
        mainPointX = maxX;
        needPointX = maxX;
        speedView = 1500;
//        sizeFieldX = 5;
//        sizeFieldY = 5;
//        otstup = buttons[1].getWidth()/20;
    }
    public void start(){
        buttons[0].setPosBtnY(buttons[0].getHeight()/2);
        buttons[1].setPosBtnY(buttons[1].getHeight() * 2);
        buttons[2].setPosBtnY(buttons[1].getPosY());
        buttons[3].setPosBtnY(buttons[1].getPosY());
        buttons[4].setPosBtnY(buttons[1].getHeight() * 3);
        buttons[5].setPosBtnY(buttons[4].getPosY());
        buttons[6].setPosBtnY(buttons[4].getPosY());
        buttons[7].setPosBtnY(buttons[1].getHeight() * 4);
        buttons[8].setPosBtnY(buttons[7].getPosY());
        buttons[9].setPosBtnY(buttons[7].getPosY());
        buttons[10].setPosBtnY(buttons[1].getHeight() * 5);
        buttons[11].setPosBtnY(buttons[10].getPosY());
        buttons[12].setPosBtnY(buttons[10].getPosY());
        buttons[13].setPosBtnY(buttons[1].getHeight() * 6);
        buttons[14].setPosBtnY(buttons[13].getPosY());
        buttons[15].setPosBtnY(buttons[13].getPosY());
        buttons[16].setPosBtnY(buttons[1].getPosY());
        buttons[17].setPosBtnY(buttons[1].getPosY());
        buttons[18].setPosBtnY(buttons[1].getPosY());
        buttons[19].setPosBtnY(buttons[4].getPosY());
        buttons[20].setPosBtnY(buttons[4].getPosY());
        buttons[21].setPosBtnY(buttons[4].getPosY());
        buttons[22].setPosBtnY(buttons[7].getPosY());
        buttons[23].setPosBtnY(buttons[7].getPosY());
        buttons[24].setPosBtnY(buttons[7].getPosY());
        buttons[25].setPosBtnY(buttons[10].getPosY());
        buttons[26].setPosBtnY(buttons[10].getPosY());
        buttons[27].setPosBtnY(buttons[10].getPosY());
        buttons[28].setPosBtnY(buttons[13].getPosY());
        buttons[29].setPosBtnY(buttons[13].getPosY());
        buttons[30].setPosBtnY(buttons[13].getPosY());
        buttons[31].setPosBtnY(buttons[1].getPosY());
        buttons[32].setPosBtnY(buttons[1].getPosY());
        buttons[33].setPosBtnY(buttons[1].getPosY());
        buttons[34].setPosBtnY(buttons[4].getPosY());
        buttons[35].setPosBtnY(buttons[4].getPosY());
        buttons[36].setPosBtnY(buttons[4].getPosY());
        buttons[37].setPosBtnY(buttons[7].getPosY());
        buttons[38].setPosBtnY(buttons[7].getPosY());
        buttons[39].setPosBtnY(buttons[7].getPosY());
        buttons[40].setPosBtnY(buttons[10].getPosY());
        buttons[41].setPosBtnY(buttons[10].getPosY());
        buttons[42].setPosBtnY(buttons[10].getPosY());
        buttons[43].setPosBtnY(buttons[13].getPosY());
        buttons[44].setPosBtnY(buttons[13].getPosY());
        buttons[45].setPosBtnY(buttons[13].getPosY());
        buttons[46].setPosBtnY(buttons[1].getPosY());
        buttons[47].setPosBtnY(buttons[1].getPosY());
        buttons[48].setPosBtnY(buttons[1].getPosY());
        buttons[49].setPosBtnY(buttons[4].getPosY());
        buttons[50].setPosBtnY(buttons[4].getPosY());

        mainPointX = 0;
        needPointX = 0;
        pointX = 0;
        start = true;
        isVisible = true;
    }
//    public int getNumberColor(){return numOfColor;}
//    public int getNumStartBlock() {return numStartBlock;}
//    public int getNumNewBlockInStep() {return numNewBlockInStep;}
//    public int getNumMaxBlockInGrps() {return numMaxBlockInGrps;}


    public boolean isVisible() {
        if (mainPointX == 0) {
            return true;
        }
        return false;
    }

    public boolean isStart() {
        return start;
    }
    public void setMainPointX(int x) {
        mainPointX = x;
        needPointX = x;
        pointX = x;
    }

    public void prev() {
        mainPointX = maxX;
        needPointX = maxX;
        pointX = maxX;
    }

    public void next() {
        mainPointX = -maxX;
        needPointX = -maxX;
        pointX = -maxX;
    }
    public int getMainPointX() {
        return mainPointX;
    }

    public Bitmap getElem(int i) {
        if(i < buttons.length) {
            if (i < counter / 50 + 2) {
                return buttons[i].getBitmap();
            }
            return buttons[i].pressed;
        }
        i = i - buttons.length;
        if (i < 29) {
            if (i < 9) return images[i + 1].getBitmap();
            if (i % 2 == 1) return images[1].getBitmap();
            else return images[(i - 10) / 2].getBitmap();
        }
        i = i - 29;
        if (i<20) {
            if (i%2 == 0) return images[2].getBitmap();
            else return images[(i-1)/2].getBitmap();
        }
        i = i - 20;
        if (i<20) {
            if (i%2 == 0) return images[3].getBitmap();
            else return images[(i-1)/2].getBitmap();
        }
        i = i - 20;
        if (i<20) {
            if (i%2 == 0) return images[4].getBitmap();
            else return images[(i-1)/2].getBitmap();
        }
        i = i - 20;
        if (i<20) {
            if (i%2 == 0) return images[5].getBitmap();
            else return images[(i-1)/2].getBitmap();
        }
        return images[0].getBitmap();
    }
    public float getPosX(int i) {
        if(i < buttons.length) {
            return buttons[i].getPosX();
        }
        i = i - buttons.length;
        if (i < 9) {
            if (i % 3 == 0) return buttons[1].getPosX() + images[0].getHeight() / 2;
            if ((i - 1) % 3 == 0) return buttons[2].getPosX() + images[0].getHeight() / 2;
            if ((i - 2) % 3 == 0) return buttons[3].getPosX() + images[0].getHeight() / 2;
        }
        i = i - 9;
        if (i < 12) {
            if (i % 6 == 0) return buttons[1].getPosX();
            if ((i - 1) % 6 == 0) return buttons[1].getPosX() + images[0].getHeight();
            if ((i - 2) % 6 == 0) return buttons[2].getPosX();
            if ((i - 3) % 6 == 0) return buttons[2].getPosX() + images[0].getHeight();
            if ((i - 4) % 6 == 0) return buttons[3].getPosX();
            if ((i - 5) % 6 == 0) return buttons[3].getPosX() + images[0].getHeight();
        }
        i = i - 12;
        if (i < 30) {
            if (i % 6 == 0) return buttons[16].getPosX();
            if ((i - 1) % 6 == 0) return buttons[16].getPosX() + images[0].getHeight();
            if ((i - 2) % 6 == 0) return buttons[17].getPosX();
            if ((i - 3) % 6 == 0) return buttons[17].getPosX() + images[0].getHeight();
            if ((i - 4) % 6 == 0) return buttons[18].getPosX();
            if ((i - 5) % 6 == 0) return buttons[18].getPosX() + images[0].getHeight();
        }
        i = i - 30;
        if (i < 30) {
            if (i % 6 == 0) return buttons[31].getPosX();
            if ((i - 1) % 6 == 0) return buttons[31].getPosX() + images[0].getHeight();
            if ((i - 2) % 6 == 0) return buttons[32].getPosX();
            if ((i - 3) % 6 == 0) return buttons[32].getPosX() + images[0].getHeight();
            if ((i - 4) % 6 == 0) return buttons[33].getPosX();
            if ((i - 5) % 6 == 0) return buttons[33].getPosX() + images[0].getHeight();
        }
        i = i - 30;
        if (i < 30) {
            if (i % 6 == 0) return buttons[46].getPosX();
            if ((i - 1) % 6 == 0) return buttons[46].getPosX() + images[0].getHeight();
            if ((i - 2) % 6 == 0) return buttons[47].getPosX();
            if ((i - 3) % 6 == 0) return buttons[47].getPosX() + images[0].getHeight();
            if ((i - 4) % 6 == 0) return buttons[48].getPosX();
            if ((i - 5) % 6 == 0) return buttons[48].getPosX() + images[0].getHeight();
        }

        return 0;
    }

    public   float getPosY(int i) {
        if(i < buttons.length) {
            return buttons[i].getPosY();
        }
        i = i - buttons.length;
        if (i < 3) return buttons[1].getPosY();
        if (i < 6) return buttons[1].getHeight() * 3;
        if (i < 9) return buttons[1].getHeight() * 4;
        if (i < 15) return buttons[1].getHeight() * 5;
        if (i < 21) return buttons[1].getHeight() * 6;

        if (i < 27) return buttons[1].getPosY();
        if (i < 33) return buttons[1].getHeight() * 3;
        if (i < 39) return buttons[1].getHeight() * 4;
        if (i < 45) return buttons[1].getHeight() * 5;
        if (i < 51) return buttons[1].getHeight() * 6;

        if (i < 57) return buttons[1].getPosY();
        if (i < 63) return buttons[1].getHeight() * 3;
        if (i < 69) return buttons[1].getHeight() * 4;
        if (i < 75) return buttons[1].getHeight() * 5;
        if (i < 81) return buttons[1].getHeight() * 6;

        if (i < 87) return buttons[1].getPosY();
        if (i < 91) return buttons[1].getHeight() * 3;
        return 0;
    }

    public int getNumberOfElem() {
        return buttons.length + 91; // +18 картинка на всём экране
    }
    public void update(short fps) {
        buttons[0].setPosBtnX(buttons[0].getWidth()/5 + mainPointX);

        buttons[1].setPosBtnX(buttons[1].getWidth() + pointX);
        buttons[2].setPosBtnX(buttons[1].getWidth() * 25 / 10 + pointX);
        buttons[3].setPosBtnX(buttons[1].getWidth() * 4 + pointX);
        buttons[4].setPosBtnX(buttons[1].getPosX());
        buttons[5].setPosBtnX(buttons[2].getPosX());
        buttons[6].setPosBtnX(buttons[3].getPosX());
        buttons[7].setPosBtnX(buttons[1].getPosX());
        buttons[8].setPosBtnX(buttons[2].getPosX());
        buttons[9].setPosBtnX(buttons[3].getPosX());
        buttons[10].setPosBtnX(buttons[1].getPosX());
        buttons[11].setPosBtnX(buttons[2].getPosX());
        buttons[12].setPosBtnX(buttons[3].getPosX());
        buttons[13].setPosBtnX(buttons[1].getPosX());
        buttons[14].setPosBtnX(buttons[2].getPosX());
        buttons[15].setPosBtnX(buttons[3].getPosX());

        buttons[16].setPosBtnX(buttons[1].getPosX() + maxX);
        buttons[17].setPosBtnX(buttons[2].getPosX() + maxX);
        buttons[18].setPosBtnX(buttons[3].getPosX() + maxX);
        buttons[19].setPosBtnX(buttons[1].getPosX() + maxX);
        buttons[20].setPosBtnX(buttons[2].getPosX() + maxX);
        buttons[21].setPosBtnX(buttons[3].getPosX() + maxX);
        buttons[22].setPosBtnX(buttons[1].getPosX() + maxX);
        buttons[23].setPosBtnX(buttons[2].getPosX() + maxX);
        buttons[24].setPosBtnX(buttons[3].getPosX() + maxX);
        buttons[25].setPosBtnX(buttons[1].getPosX() + maxX);
        buttons[26].setPosBtnX(buttons[2].getPosX() + maxX);
        buttons[27].setPosBtnX(buttons[3].getPosX() + maxX);
        buttons[28].setPosBtnX(buttons[1].getPosX() + maxX);
        buttons[29].setPosBtnX(buttons[2].getPosX() + maxX);
        buttons[30].setPosBtnX(buttons[3].getPosX() + maxX);

        buttons[31].setPosBtnX(buttons[1].getPosX() + maxX * 2);
        buttons[32].setPosBtnX(buttons[2].getPosX() + maxX * 2);
        buttons[33].setPosBtnX(buttons[3].getPosX() + maxX * 2);
        buttons[34].setPosBtnX(buttons[1].getPosX() + maxX * 2);
        buttons[35].setPosBtnX(buttons[2].getPosX() + maxX * 2);
        buttons[36].setPosBtnX(buttons[3].getPosX() + maxX * 2);
        buttons[37].setPosBtnX(buttons[1].getPosX() + maxX * 2);
        buttons[38].setPosBtnX(buttons[2].getPosX() + maxX * 2);
        buttons[39].setPosBtnX(buttons[3].getPosX() + maxX * 2);
        buttons[40].setPosBtnX(buttons[1].getPosX() + maxX * 2);
        buttons[41].setPosBtnX(buttons[2].getPosX() + maxX * 2);
        buttons[42].setPosBtnX(buttons[3].getPosX() + maxX * 2);
        buttons[43].setPosBtnX(buttons[1].getPosX() + maxX * 2);
        buttons[44].setPosBtnX(buttons[2].getPosX() + maxX * 2);
        buttons[45].setPosBtnX(buttons[3].getPosX() + maxX * 2);

        buttons[46].setPosBtnX(buttons[1].getPosX() + maxX * 3);
        buttons[47].setPosBtnX(buttons[2].getPosX() + maxX * 3);
        buttons[48].setPosBtnX(buttons[3].getPosX() + maxX * 3);
        buttons[49].setPosBtnX(buttons[1].getPosX() + maxX * 3);
        buttons[50].setPosBtnX(buttons[2].getPosX() + maxX * 3);


        if (pointX < needPointX) {
            if (needPointX - pointX > maxX/5) {
                pointX = pointX + speedView / fps;
            }
            else if(needPointX - pointX > maxX/20) {
                pointX = pointX  + speedView/2 / fps;
            }
            else if(needPointX - pointX > maxX/40) {
                pointX = pointX  + speedView/4 / fps;
            }
            else {
                pointX = pointX + 1;
            }
        }
        if (pointX > needPointX) {

            if (pointX - needPointX > maxX/5) {
                pointX = pointX - speedView / fps;

            }
            else if(pointX - needPointX > maxX/20) {
                pointX = pointX -speedView/2 / fps;
            }
            else if(pointX - needPointX > maxX/40) {
                pointX = pointX -speedView/4 / fps;
            }
            else {
                pointX = pointX - 1;
            }
        }
    }
//    public int getSizeFieldX(){
//        return sizeFieldX;
//    }
//
//
//    public int getSizeFieldY() {
//        return sizeFieldY;
//    }

    public void touch(MotionEvent motionEvent) {
        if ((motionEvent.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_DOWN){
            starNeedPointX = needPointX;
            touchX = (int) motionEvent.getX();
            touchY = (int) motionEvent.getY();
            for (int i = 0; i < buttons.length && i < counter/50 + 2; i++) {
                buttons[i].touch(touchX,touchY);
//                if (buttons[i].pres && snd && pressBtn > 0) {
//                    mSoundPool.play(pressBtn, 1, 1, 0, 0, 1);
//                }
            }
        }
        if ((motionEvent.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_MOVE){
            Log.d("editorLevel","1");
            needPointX = ((int)motionEvent.getX()- touchX + starNeedPointX);
//            if (pointX > -maxX){
//                Log.d("editorLevel","2");
//                needPointX = ((int)motionEvent.getX()-touchX);
//            }
//            else if (pointX > -maxX * 2) {
//                Log.d("editorLevel","3");
//                needPointX = ((int)motionEvent.getX()-touchX - maxX);
//            }
//            else if (pointX > -maxX *3) {
//                Log.d("editorLevel","4");
//                needPointX = ((int)motionEvent.getX()-touchX - maxX*2);
//            }
//            else if (pointX > -maxX*4) {
//                Log.d("editorLevel","5");
//                needPointX = ((int)motionEvent.getX()-touchX - maxX*3);
//            }
        }

        if ((motionEvent.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP){
            if(needPointX > -maxX/2) needPointX =0;
            else if(needPointX > -maxX*3/2) needPointX = -maxX;
            else if(needPointX >-maxX*5/2) needPointX = -maxX *2;
            else needPointX = -maxX*3;

            touchX = (int) motionEvent.getX();
            touchY = (int) motionEvent.getY();
            for (int i = 0; i < buttons.length; i++) {
                buttons[i].unTouch(touchX,touchY);
            }
        }
    }
}
