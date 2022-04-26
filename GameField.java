package ru.takemakekeep.chainofcakes;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;
import android.view.MotionEvent;

import java.io.IOException;

public class GameField{
    private Button[] buttons;
    private Image[][] imagesOriginal;
    private Image[] images2;
    private Bitmap[][] images;
    private boolean isVisible;
    protected int mainPointX;
    //protected int mainPointXNeed;
    protected int maxInGroups;
    private int maxX, maxY;
    public boolean start = false;
    private int speedView;
    private int touchDownX, touchDownY;
    private int touchUpX, touchUpY;
    private int sizeBlock;
    private int[][] field;
    protected GameBlocks gameBlocks;
    protected int counter;
    private Context context;
    private int maxCounter;
    private Image imgFon;
    protected boolean sound;
//    SoundPool mSoundPool;
//    int pressBtn;

    GameField(Button[] btn, Image[][] img, Image[] img2, Image imgFon, int sizeDispX, int sizeDispY, int cntr, Context context, boolean snd) {
//        mSoundPool = new SoundPool(2, AudioManager.STREAM_MUSIC,0);
//        try {
//            pressBtn = mSoundPool.load(context.getAssets().openFd("button-press.ogg"), 1);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        this.imgFon = imgFon;
        this.context = context;
        sound = snd;
        counter = cntr;
        maxX = sizeDispX;
        maxY = sizeDispY;
        start = false;
        isVisible = false;
        buttons = btn;
        imagesOriginal = img;
        images2 = img2;
        mainPointX = maxX;
       // mainPointXNeed = mainPointX;
        speedView = 1000;
    }
    public void resume(int sizeFieldX, int sizeFieldY, int numberOfColor, int numNewBlockStep, int numMaxInGrps, int maxCounter, boolean snd) {
        this.maxCounter = maxCounter;
        maxInGroups = numMaxInGrps;
        if (gameBlocks != null) {
            Log.d("mSoundPool", "0 ");
            if (gameBlocks.mSoundPool != null) {
                gameBlocks.mSoundPool.release();
                gameBlocks.mSoundPool = null;
                Log.d("mSoundPool", "1 ");
            }
        }
        sound = snd;
        buttons[0].setPosBtnX(buttons[0].getWidth()/5 + mainPointX);
        buttons[0].setPosBtnY(buttons[0].getHeight()/2);
        buttons[1].setPosBtnX(maxX);
        buttons[1].setPosBtnY(buttons[0].getHeight()/2);
        buttons[2].setPosBtnX(maxX+buttons[2].getWidth());
        buttons[2].setPosBtnY((maxY/3)*2);

        if (sizeFieldX >= sizeFieldY) {
            sizeBlock = (int)(maxX - maxX / 5.586) / sizeFieldX;
        }
        else {
            sizeBlock = (int)(maxX - maxX / 5.586) / sizeFieldY;
        }

        images = new Bitmap[imagesOriginal.length][];
        for (int i = 0; i < images.length; i++) {
            images[i] = new Bitmap[imagesOriginal[i].length];
            for (int j = 0; j < images[i].length; j++) {
                images[i][j] = Bitmap.createScaledBitmap(imagesOriginal[i][j].getBitmap(),
                        sizeBlock, sizeBlock, false);
            }
        }
        field = new int[2][];
        field[0] = new int[sizeFieldX];
        field[1] = new int[sizeFieldY];
        for(int i = 0; i < sizeFieldX; i++) {
            field[0][i] = sizeBlock * i + maxX / 2 - sizeBlock  * sizeFieldX / 2;
        }
        for(int j = 0; j < sizeFieldY; j++) {
            field[1][j] = sizeBlock * j / 2+ (maxY * 22 / 50 + maxY * 5 / 16) - (sizeBlock * sizeFieldY) / 4 - sizeBlock / 2;
        }

        gameBlocks = new GameBlocks(sizeFieldX, sizeFieldY, numberOfColor, numNewBlockStep, numMaxInGrps, images,field, counter, context, maxCounter, sound);

    }
    public void start(int sizeFieldX, int sizeFieldY, int numberOfColor, int numStartBlock, int numNewBlockStep, int numMaxInGrps, int maxCounter, boolean snd) {

        this.maxCounter = maxCounter;
        maxInGroups = numMaxInGrps;


        if (gameBlocks != null) {
            Log.d("mSoundPools", "0 ");
            if (gameBlocks.mSoundPool != null) {
                gameBlocks.mSoundPool.release();
                gameBlocks.mSoundPool = null;
                Log.d("mSoundPools", "1 ");
            }
        }
        sound = snd;
        buttons[0].setPosBtnX(buttons[0].getWidth()/5 + mainPointX);
        buttons[0].setPosBtnY(buttons[0].getHeight()/2);
        buttons[1].setPosBtnX(maxX);
        buttons[1].setPosBtnY(buttons[0].getHeight()/2);
        buttons[2].setPosBtnX(maxX+buttons[2].getWidth());
        buttons[2].setPosBtnY((maxY/3)*2);
        if(counter < maxCounter) {
            buttons[1].setPosBtnX(maxX);
        }
        else buttons[1].setPosBtnX(maxX-buttons[0].getWidth()-buttons[0].getWidth()/20);
        buttons[1].setPosBtnY(maxX/10);
        if (sizeFieldX >= sizeFieldY) {
            sizeBlock = (int)(maxX - maxX / 5.586) / sizeFieldX;
        }
        else {
            sizeBlock = (int)(maxX - maxX / 5.586) / sizeFieldY;
        }

        images = new Bitmap[imagesOriginal.length][];
        for (int i = 0; i < images.length; i++) {
            images[i] = new Bitmap[imagesOriginal[i].length];
            for (int j = 0; j < images[i].length; j++) {
                images[i][j] = Bitmap.createScaledBitmap(imagesOriginal[i][j].getBitmap(),
                        sizeBlock, sizeBlock, false);
            }
        }
        Log.d("imageDeb","10 " + images.length);
        Log.d("imageDeb","20 " + images[images.length - 1].length);
        field = new int[2][];
        field[0] = new int[sizeFieldX];
        field[1] = new int[sizeFieldY];
        for(int i = 0; i < sizeFieldX; i++) {
            field[0][i] = sizeBlock * i + maxX / 2 - sizeBlock  * sizeFieldX / 2;
        }
        for(int j = 0; j < sizeFieldY; j++) {
            field[1][j] = sizeBlock * j / 2+ (maxY * 22 / 50 + maxY * 5 / 16) - (sizeBlock * sizeFieldY) / 4 - sizeBlock / 2;
        }

        gameBlocks = new GameBlocks(sizeFieldX, sizeFieldY, numberOfColor, numNewBlockStep, numMaxInGrps, images,field, counter, context, maxCounter, sound);
        gameBlocks.addBlock(numStartBlock);

        start = true;
        isVisible = true;
        //mainPointXNeed = 0;
        mainPointX = 0;
    }

    public void start() {
       // mainPointXNeed = 0;
        mainPointX = 0;
    }


    public boolean isStart() {
        return start;
    }

    public boolean isVisible() {
        if (mainPointX == 0) {
            return true;
        }
        return false;
    }
    public void prev() {
        //mainPointXNeed = maxX;
        mainPointX = maxX;
    }

    public Bitmap getElem(int i) {
        if (i < field[0].length * field[1].length) {
            Log.d("imageDeb","1 " + images.length);
            Log.d("imageDeb","2 " + (images[images.length - 1]==null));

            if(images!=null && images[images.length - 1]!=null) {
                if (i == 0) return images[images.length - 1][7];
                if (i == field[0].length * field[1].length - 1) return images[images.length - 1][6];
                if (i == field[0].length - 1) return images[images.length - 1][5];
                if (i == field[0].length * (field[1].length - 1))
                    return images[images.length - 1][8];

                if (i > field[0].length * (field[1].length - 1))
                    return images[images.length - 1][3];
                if ((i + 1) % field[0].length == 0) return images[images.length - 1][4];
                if (i < field[0].length) return images[images.length - 1][2];
                if (i % field[0].length == 0) return images[images.length - 1][1];

                return images[images.length - 1][0];
            }
            else return null;
        }
        i = i - field[0].length * field[1].length;
        if (i < field[0].length * field[1].length)  {
            return gameBlocks.getBitmap(i);
        }
        i = i - field[0].length * field[1].length;
        if (i == 0) return imgFon.getBitmap();
        i = i - 1;
        if (i < 3) return buttons[i].getBitmap();
        i = i - 3;
        switch (i) {
            case 0: return images2[10].getBitmap();
            case 1: return images2[11].getBitmap();
        }
        if (maxInGroups < 10) {
            return images2[maxInGroups].getBitmap();
        }
        else {
            if (i == 2) return images2[1].getBitmap();
            return images2[maxInGroups- 10].getBitmap();
        }

        /*
        if (i < buttons.length) return buttons[i].getBitmap();
        else if (i < buttons.length + field[0].length * field[1].length) {
            i = i - buttons.length;
            if (i == 0) return images[images.length - 1][7];
            if (i == field[0].length * field[1].length - 1) return images[images.length - 1][6];
            if (i == field[0].length - 1) return images[images.length - 1][5];
            if (i == field[0].length * (field[1].length - 1)) return images[images.length - 1][8];

            if (i > field[0].length * (field[1].length - 1)) return images[images.length - 1][3];
            if ((i + 1) % field[0].length == 0) return images[images.length - 1][4];
            if (i < field[0].length) return images[images.length - 1][2];
            if (i % field[0].length == 0) return images[images.length - 1][1];

            return images[images.length - 1][0];
        }
		else {
			i = i - buttons.length - field[0].length * field[1].length;
			return gameBlocks.getBitmap(i);
		}
		*/
    }

    public float getPosX(int i) {
        if (i < field[0].length * field[1].length) {
            return field[0][i - field[0].length * (int)(i/field[0].length)] + mainPointX;
        }
        i = i - field[0].length * field[1].length;

        if (i < field[0].length * field[1].length) {
            return gameBlocks.getPosX(i) + mainPointX;
        }
        i = i - field[0].length * field[1].length;
        if (i == 0) return buttons[2].getPosX() - buttons[2].getWidth() / 2;
        i = i - 1;
        if (i < 3 )return buttons[i].getPosX();
        i = i - 3;
        switch (i) {
            case 0: return maxX / 2 - images2[0].getWidth()* 15 / 10;
            case 1: return maxX / 2 - images2[0].getWidth() / 2;
        }
        if (maxInGroups < 10) return maxX / 2 + images2[0].getWidth() / 2;
        if (i == 2) return maxX / 2 + images2[0].getWidth() / 2;
        return maxX / 2 + images2[0].getWidth() *15/10;
        /*
        if (i < buttons.length) return buttons[i].getPosX();
        else if (i < field[0].length * field[1].length + buttons.length) {
			i = i - buttons.length;
	        return field[0][i - field[0].length * (int)(i/field[0].length)] + mainPointX;
		}
		else {
			i = i - buttons.length - field[0].length * field[1].length;
			return gameBlocks.getPosX(i) + mainPointX;
		}

         */
    }

    public float getPosY(int i) {

        if (i < field[0].length * field[1].length) {
            return field[1][(int)(i/field[0].length)];
        }
        i = i - field[0].length * field[1].length;

        if (i < field[0].length * field[1].length) {
            return gameBlocks.getPosY(i);
        }
        i = i - field[0].length * field[1].length;
        if (i == 0) return buttons[2].getPosY() - buttons[2].getHeight()/2;
        i = i - 1;
        if (i < 3) return buttons[i].getPosY();
        return images2[0].getHeight() * 3;

        /*
        if (i < buttons.length) return buttons[i].getPosY();
        else if (i < field[0].length * field[1].length + buttons.length) {
			i = i - buttons.length;
	        return field[1][(int)(i/field[0].length)];
		}
		else {
			i = i - buttons.length - field[0].length * field[1].length;
			return gameBlocks.getPosY(i);
		}

         */
    }

    public void update(short fps) {
        gameBlocks.update(fps);
        if (gameBlocks.gameOver) {
            buttons[2].setPosBtnX(maxX/2 - buttons[2].getWidth() / 2 + mainPointX);
        }
        buttons[0].setPosBtnX(buttons[0].getWidth() / 5 + mainPointX);

        if(counter < maxCounter) {
            buttons[1].setPosBtnX(maxX);
        }
        else buttons[1].setPosBtnX(maxX-buttons[0].getWidth()-buttons[0].getWidth()/5 + mainPointX);
       /* if (mainPointX < mainPointXNeed) {
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

        */
    }

    public void touch(MotionEvent motionEvent, boolean snd) {
        gameBlocks.sound = snd;
        Log.d("debugGameField","1");
        if ((motionEvent.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_DOWN){
            touchDownX = (int) motionEvent.getX();
            touchDownY = (int) motionEvent.getY();
            for (int i = 0; i < buttons.length; i++) {
                buttons[i].touch(touchDownX,touchDownY);
//                if (buttons[i].pres && snd && pressBtn > 0) {
//                    mSoundPool.play(pressBtn, 1, 1, 0, 0, 1);
//                }
            }
        }

        if ((motionEvent.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP){
            gameBlocks.sortingY();
            touchUpX = (int) motionEvent.getX();
            touchUpY = (int) motionEvent.getY();
            for (int i = 0; i < buttons.length; i++) {
                buttons[i].unTouch(touchUpX,touchUpY);
            }
            if(!gameBlocks.gameOver && touchDownY>maxY/10) {
                if (gameBlocks.endStep && (Math.abs(touchDownX - touchUpX) > 20 || Math.abs(touchDownY - touchUpY) > 20)) {
                    gameBlocks.endStep = false;
                    if (Math.abs(touchDownX - touchUpX) > Math.abs(touchDownY - touchUpY)) {
                        if (touchDownX > touchUpX) {
                            gameBlocks.setState(gameBlocks.LEFT);
                            Log.d("debugGameField", "2");
                        } else {
                            gameBlocks.setState(gameBlocks.RIGHT);
                            Log.d("debugGameField", "3");
                        }
                    } else {
                        if (touchDownY > touchUpY) {
                            gameBlocks.setState(gameBlocks.UP);
                            Log.d("debugGameField", "4");
                        } else {
                            gameBlocks.setState(gameBlocks.DOWN);
                            Log.d("debugGameField", "5");
                        }
                    }
                }
            }
        }
    }
    public int getNumberOfElem() {
        if (maxInGroups < 10) return buttons.length + field[0].length * field[1].length * 2 + 1 + 3;
        return buttons.length + field[0].length * field[1].length * 2 + 1 + 4;
    }
    public int getMainPointX() {
        return mainPointX;
    }

}
