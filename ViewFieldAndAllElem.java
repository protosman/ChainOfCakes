package ru.takemakekeep.chainofcakes;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;
import android.view.MotionEvent;

import java.io.IOException;


public class ViewFieldAndAllElem {
    private int maxX;
    protected float level;
    protected int counter;
    protected Bitmap background;
    private Button[] buttonsMainMenu;
    private Button[] buttonsEditLvl;
    private Button[] buttonsGameField;
    private Button[] buttonsOptions;
    private Button buttonCredits;

    private Image[] imagesEditLvl;
    private Image[][] imagesGameField;
    private Image[] imagesGameField2;

    protected MainMenuView mainMenuView;
    protected EditorLevelView editorLevelView;
    protected GameField gameField;
    protected Options options;
    protected Credits credits;
    protected Cook cookMan;
    protected Image[][] cookManImage;

    public int normalSizeBtn;
    private Context context;

    boolean music,sound;
    SoundPool mSoundPool;
    int pressBtn;

    ViewFieldAndAllElem(Context context, int sizeDispX, int sizeDispY, int cntrs,boolean msc, boolean snd) {

        mSoundPool = new SoundPool(2, AudioManager.STREAM_MUSIC,0);
        try {
            pressBtn = mSoundPool.load(context.getAssets().openFd("button-press.ogg"), 1);
        } catch (IOException e) {
            Log.d("MainMenu", "load exc " + e);
            e.printStackTrace();
        }
        level = 1;
        counter = cntrs;
        music = msc;
        sound = snd;

        maxX = sizeDispX;
        this.context = context;
        background = BitmapFactory.decodeResource(context.getResources(), R.drawable.background);
        background = Bitmap.createScaledBitmap(background, sizeDispX, sizeDispY, false);
        normalSizeBtn = sizeDispX / 3;
        buttonsMainMenu = new Button[5];
        Log.d("GameViewField", "1");
        buttonsMainMenu[0] = new Button(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.resume_eng_normal),
                        normalSizeBtn, normalSizeBtn / 2,false),
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.resume_eng_pressed),
                        normalSizeBtn, normalSizeBtn / 2,false));

        Log.d("GameViewField", "2");
        buttonsMainMenu[1] = new Button(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.newgame_eng_normal),
                        normalSizeBtn, normalSizeBtn / 2,false),
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.newgame_eng_pressed),
                        normalSizeBtn, normalSizeBtn / 2,false));
        buttonsMainMenu[2] = new Button(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.levels_eng_normal),
                        normalSizeBtn, normalSizeBtn / 2,false),
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.levels_eng_pressed),
                        normalSizeBtn, normalSizeBtn / 2,false));
        buttonsMainMenu[3] = new Button(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.options_eng_normal),
                        normalSizeBtn, normalSizeBtn / 2,false),
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.options_eng_pressed),
                        normalSizeBtn, normalSizeBtn / 2,false));
        buttonsMainMenu[4] = new Button(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.credits_eng_normal),
                        normalSizeBtn, normalSizeBtn / 2,false),
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.credits_eng_pressed),
                        normalSizeBtn, normalSizeBtn / 2,false));
        imagesEditLvl = new Image[11];

        imagesGameField = new Image[11][];

        imagesGameField[0] = new Image[16];
        imagesGameField[1] = new Image[16];
        imagesGameField[2] = new Image[16];
        imagesGameField[3] = new Image[16];
        imagesGameField[4] = new Image[16];

        imagesGameField[5] = new Image[4];
        imagesGameField[6] = new Image[4];
        imagesGameField[7] = new Image[4];
        imagesGameField[8] = new Image[4];
        imagesGameField[9] = new Image[4];

        imagesGameField[10] = new Image[9];

        startImage();
        startBtnEditLvl();

        Log.d("GameViewField", "3");

        buttonsGameField = new Button[3];
        buttonsGameField[0] = new Button(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.cursorback_normal),
                        normalSizeBtn / 2 , normalSizeBtn / 2,false),
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.cursorback_pressed),
                        normalSizeBtn / 2 , normalSizeBtn / 2,false));
        buttonsGameField[1] = new Button(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.cursornext_normal),
                        normalSizeBtn/2, normalSizeBtn / 2,false),
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.cursornext_pressed),
                        normalSizeBtn/2, normalSizeBtn / 2,false));
        buttonsGameField[2] = new Button(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.restart_eng_normal),
                        normalSizeBtn, normalSizeBtn / 2,false),
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.restart_eng_pressed),
                        normalSizeBtn, normalSizeBtn / 2,false));

        buttonsOptions = new Button[8];
        buttonsOptions[0] = new Button(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.cursorback_normal),
                        normalSizeBtn / 2 , normalSizeBtn / 2,false),
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.cursorback_pressed),
                        normalSizeBtn / 2 , normalSizeBtn / 2,false));
        buttonsOptions[1] = new Button(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.musicon_eng_normal),
                        normalSizeBtn, normalSizeBtn / 2,false),
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.musicon_eng_pressed),
                        normalSizeBtn, normalSizeBtn / 2,false));
        buttonsOptions[2] = new Button(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.musicoff_eng_normal),
                        normalSizeBtn, normalSizeBtn / 2,false),
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.musicoff_eng_pressed),
                        normalSizeBtn, normalSizeBtn / 2,false));
        buttonsOptions[3] = new Button(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.soundon_eng_normal),
                        normalSizeBtn, normalSizeBtn / 2,false),
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.soundon_eng_pressed),
                        normalSizeBtn, normalSizeBtn / 2,false));
        buttonsOptions[4] = new Button(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.soundoff_eng_normal),
                        normalSizeBtn, normalSizeBtn / 2,false),
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.soundoff_eng_pressed),
                        normalSizeBtn, normalSizeBtn / 2,false));
        buttonsOptions[5] = new Button(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.resetgame_eng_normal),
                        normalSizeBtn, normalSizeBtn / 2,false),
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.resetgame_eng_pressed),
                        normalSizeBtn, normalSizeBtn / 2,false));
        buttonsOptions[6] = new Button(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.yes_eng_normal),
                        normalSizeBtn/2, normalSizeBtn / 2,false),
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.yes_eng_pressed),
                        normalSizeBtn/2, normalSizeBtn / 2,false));
        buttonsOptions[7] = new Button(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.no_eng_normal),
                        normalSizeBtn / 2, normalSizeBtn / 2,false),
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.no_eng_pressed),
                        normalSizeBtn / 2, normalSizeBtn / 2,false));

        Log.d("GameViewField", "4");


        imagesEditLvl[0] = new Image(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.num0),
                        normalSizeBtn / 4, normalSizeBtn / 4,false));
        imagesEditLvl[1] = new Image(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.num1),
                        normalSizeBtn / 4, normalSizeBtn / 4,false));
        imagesEditLvl[2] = new Image(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.num2),
                        normalSizeBtn / 4, normalSizeBtn / 4,false));
        imagesEditLvl[3] = new Image(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.num3),
                        normalSizeBtn / 4, normalSizeBtn / 4,false));
        imagesEditLvl[4] = new Image(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.num4),
                        normalSizeBtn / 4, normalSizeBtn / 4,false));
        imagesEditLvl[5] = new Image(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.num5),
                        normalSizeBtn / 4, normalSizeBtn / 4,false));
        imagesEditLvl[6] = new Image(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.num6),
                        normalSizeBtn / 4, normalSizeBtn / 4,false));
        imagesEditLvl[7] = new Image(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.num7),
                        normalSizeBtn / 4, normalSizeBtn / 4,false));
        imagesEditLvl[8] = new Image(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.num8),
                        normalSizeBtn / 4, normalSizeBtn / 4,false));
        imagesEditLvl[9] = new Image(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.num9),
                        normalSizeBtn / 4, normalSizeBtn / 4,false));
        imagesEditLvl[10] = new Image(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.sqrt),
                        normalSizeBtn *2, normalSizeBtn * 5 / 2,false));

        Log.d("GameViewField", "5");
        Image imageOptions = new Image(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.sqrt),
                        normalSizeBtn * 2, normalSizeBtn/2 * 4 ,false));
        Image imageGameField = new Image(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.sqrt),
                        normalSizeBtn * 2, normalSizeBtn,false));
        Image imageCredits  = new Image(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.sqrt),
                        normalSizeBtn * 2, normalSizeBtn * 2,false));
        buttonCredits = new Button(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.cursorback_normal),
                        normalSizeBtn / 2 , normalSizeBtn / 2,false),
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.cursorback_pressed),
                        normalSizeBtn / 2 , normalSizeBtn / 2,false));
        imagesGameField2 = new Image[12];
        imagesGameField2[0] = new Image(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.num0),
                        normalSizeBtn / 5, normalSizeBtn / 5,false));
        imagesGameField2[1] = new Image(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.num1),
                        normalSizeBtn / 5, normalSizeBtn / 5,false));
        imagesGameField2[2] = new Image(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.num2),
                        normalSizeBtn / 5, normalSizeBtn / 5,false));
        imagesGameField2[3] = new Image(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.num3),
                        normalSizeBtn / 5, normalSizeBtn / 5,false));
        imagesGameField2[4] = new Image(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.num4),
                        normalSizeBtn / 5, normalSizeBtn / 5,false));
        imagesGameField2[5] = new Image(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.num5),
                        normalSizeBtn / 5, normalSizeBtn / 5,false));
        imagesGameField2[6] = new Image(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.num6),
                        normalSizeBtn / 5, normalSizeBtn / 5,false));
        imagesGameField2[7] = new Image(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.num7),
                        normalSizeBtn / 5, normalSizeBtn / 5,false));
        imagesGameField2[8] = new Image(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.num8),
                        normalSizeBtn / 5, normalSizeBtn / 5,false));
        imagesGameField2[9] = new Image(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.num9),
                        normalSizeBtn / 5, normalSizeBtn / 5,false));
        imagesGameField2[10] = new Image(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.block_0_normal_pressed),
                        normalSizeBtn / 5, normalSizeBtn / 5,false));
        imagesGameField2[11] = new Image(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.x),
                        normalSizeBtn / 5, normalSizeBtn / 5,false));

        mainMenuView = new MainMenuView(buttonsMainMenu, sizeDispX, sizeDispY, imagesEditLvl, cntrs, context);
        editorLevelView = new EditorLevelView(buttonsEditLvl,imagesEditLvl, sizeDispX, sizeDispY, cntrs,context);
        gameField = new GameField(buttonsGameField, imagesGameField, imagesGameField2, imageGameField,sizeDispX, sizeDispY,cntrs,context, sound);
        options = new Options(buttonsOptions, imageOptions ,sizeDispX,sizeDispY, context, music, sound);
        credits = new Credits(buttonCredits, imageCredits ,sizeDispX,sizeDispY, context);
        mainMenuView.start();
        cookManImage = new Image[4][3];
        cookManImage[0][0] = new Image(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.zadidle),
                        sizeDispY * 7 / 50, sizeDispY / 5,false));
        cookManImage[0][1] = new Image(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.zadleft),
                        sizeDispY * 7 / 50, sizeDispY / 5,false));
        cookManImage[0][2] = new Image(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.zadright),
                        sizeDispY * 7 / 50, sizeDispY / 5,false));
        cookManImage[1][0] = new Image(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.rbokilde),
                        sizeDispY * 7 / 50, sizeDispY / 5,false));
        cookManImage[1][1] = new Image(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.rbokleft),
                        sizeDispY * 7 / 50, sizeDispY / 5,false));
        cookManImage[1][2] = new Image(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.rbokright),
                        sizeDispY * 7 / 50, sizeDispY / 5,false));
        cookManImage[2][0] = new Image(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.lisoilde),
                        sizeDispY * 7 / 50, sizeDispY / 5,false));
        cookManImage[2][1] = new Image(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.lisoleft),
                        sizeDispY * 7 / 50, sizeDispY / 5,false));
        cookManImage[2][2] = new Image(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.lisoright),
                        sizeDispY * 7 / 50, sizeDispY / 5,false));
        cookManImage[3][0] = new Image(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.lbokilde),
                        sizeDispY * 7 / 50, sizeDispY / 5,false));
        cookManImage[3][1] = new Image(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.lbokleft),
                        sizeDispY * 7 / 50, sizeDispY / 5,false));
        cookManImage[3][2] = new Image(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.lbokright),
                        sizeDispY * 7 / 50, sizeDispY / 5,false));
        cookMan = new Cook(cookManImage,sizeDispX,sizeDispY);


        Log.d("GameViewField", "6");
            
    }


    public void update(short fps) {
        if (gameField.gameBlocks != null){
//            Log.d("vfaaae restart3", " " + gameField.gameBlocks.counter);
            mainMenuView.counter = gameField.gameBlocks.counter;
            gameField.counter = gameField.gameBlocks.counter;
            editorLevelView.counter = gameField.gameBlocks.counter;
        }
        if (buttonsMainMenu[0].tripped) {
            buttonsMainMenu[0].tripped = false;
            if(sound) {
                mSoundPool.play(pressBtn, 1, 1, 0, 0, 1);
            }
            mainMenuView.next();
            gameField.start();
        }
        if (buttonsMainMenu[1].tripped) {
            if(sound) {
                mSoundPool.play(pressBtn, 1, 1, 0, 0, 1);
            }
            buttonsMainMenu[1].tripped = false;
            mainMenuView.next();
            level = 1;
            gameField.start((int)Math.ceil((10 * level + 235)/49), (int)Math.floor((10 * level + 235)/49), (int)Math.round((2 * level + 145)/49),
                    Math.round((10 * level + 235)/49), Math.round((8*level + 90)/49), Math.round((7*level + 140)/49),(int)level *50, options.sound);
        }

        if (buttonsMainMenu[2].tripped) {
            if(sound) {
                mSoundPool.play(pressBtn, 1, 1, 0, 0, 1);
            }
            buttonsMainMenu[2].tripped = false;
            mainMenuView.next();
            editorLevelView.start();
        }
        if (buttonsMainMenu[3].tripped) {
            if(sound) {
                mSoundPool.play(pressBtn, 1, 1, 0, 0, 1);
            }
            buttonsMainMenu[3].tripped = false;
            mainMenuView.next();
            options.start();
        }
        if (buttonsMainMenu[4].tripped) {
            if(sound) {
                mSoundPool.play(pressBtn, 1, 1, 0, 0, 1);
            }
            buttonsMainMenu[4].tripped = false;
            mainMenuView.next();
            credits.start();
        }
        if (buttonsOptions[0].tripped) {
            if(sound) {
                mSoundPool.play(pressBtn, 1, 1, 0, 0, 1);
            }
            buttonsOptions[0].tripped = false;
            options.prev();
            mainMenuView.start();
        }
        if (buttonsOptions[1].tripped) {
            if(sound) {
                mSoundPool.play(pressBtn, 1, 1, 0, 0, 1);
            }
            buttonsOptions[1].tripped = false;
            options.music = true;
            music = true;

            context.startService(new Intent(context, MyService.class));
        }
        if (buttonsOptions[2].tripped) {
            if(sound) {
                mSoundPool.play(pressBtn, 1, 1, 0, 0, 1);
            }
            buttonsOptions[2].tripped = false;

            context.stopService(new Intent(context, MyService.class));
            options.music = false;
            music =false;
        }
        if (buttonsOptions[3].tripped) {
            if(sound) {
                mSoundPool.play(pressBtn, 1, 1, 0, 0, 1);
            }
            buttonsOptions[3].tripped = false;
            options.sound = true;
            sound = true;
        }
        if (buttonsOptions[4].tripped) {
            if(sound) {
                mSoundPool.play(pressBtn, 1, 1, 0, 0, 1);
            }
            buttonsOptions[4].tripped = false;
            options.sound = false;
            sound = false;
        }
        if (buttonsOptions[5].tripped) {
            if(sound) {
                mSoundPool.play(pressBtn, 1, 1, 0, 0, 1);
            }
            buttonsOptions[5].tripped = false;
            options.resetPos = -maxX;
        }
        if (buttonsOptions[6].tripped) {
            if(sound) {
                mSoundPool.play(pressBtn, 1, 1, 0, 0, 1);
            }
            buttonsOptions[6].tripped = false;
            counter = 0;
            mainMenuView.counter = 0;
            gameField.counter = 0;
            editorLevelView.counter = 0;
            if (gameField.gameBlocks != null) {
                gameField.gameBlocks.counter = 0;
            }
//            counter = 3000;
//            mainMenuView.counter = 3000;
//            gameField.counter = 3000;
//            editorLevelView.counter = 3000;
            mainMenuView.next();

            options.prev();
//            level = 61;
            level = 1;
            gameField.start((int)Math.ceil((10 * level + 235)/49), (int)Math.floor((10 * level + 235)/49), (int)Math.round((2 * level + 145)/49),
                    Math.round((10 * level + 235)/49), Math.round((8*level + 90)/49), Math.round((7*level + 140)/49),(int)level *50, options.sound);

            options.resetPos = 0;
        }
        if (buttonsOptions[7].tripped) {
            if(sound) {
                mSoundPool.play(pressBtn, 1, 1, 0, 0, 1);
            }
            buttonsOptions[7].tripped = false;
            options.resetPos = 0;
        }
        if (buttonCredits.tripped) {
            if(sound) {
                mSoundPool.play(pressBtn, 1, 1, 0, 0, 1);
            }
            buttonCredits.tripped = false;
            credits.prev();
            mainMenuView.start();
        }





        for(float i = 1; i < buttonsEditLvl.length; i++) {
            if (buttonsEditLvl[(int)i].tripped) {
                if(sound) {
                    mSoundPool.play(pressBtn, 1, 1, 0, 0, 1);
                }
                buttonsEditLvl[(int)i].tripped = false;
                Log.d("vfaae"," " + level);
                if (level == 0 && i == 1) {
                    editorLevelView.next();
                    gameField.start((int) Math.ceil((10 * i + 235) / 49), (int) Math.floor((10 * i + 235) / 49), (int)Math.round((2 * level + 145)/49),
                            Math.round((10 * i + 235) / 49), Math.round((8 * i + 90) / 49), Math.round((7 * i + 140) / 49), (int)i * 50, options.sound);


                }
                if(i==level) {
                    editorLevelView.next();
                    gameField.start();
                }
                if (i < level){
                    editorLevelView.next();
                    gameField.start((int) Math.ceil((10 * i + 235) / 49), (int) Math.floor((10 * i + 235) / 49), (int)Math.round((2 * level + 145)/49),
                            Math.round((10 * i + 235) / 49), Math.round((8 * i + 90) / 49), Math.round((7 * i + 140) / 49), (int)i * 50, options.sound);
                }
            }
        }
        
        if (buttonsEditLvl[0].tripped) {
            if(sound) {
                mSoundPool.play(pressBtn, 1, 1, 0, 0, 1);
            }
            buttonsEditLvl[0].tripped = false;
            editorLevelView.prev();
            mainMenuView.start();
        }

        if (buttonsGameField[0].tripped) {
            if(sound) {
                mSoundPool.play(pressBtn, 1, 1, 0, 0, 1);
            }
            buttonsGameField[0].tripped = false;
            gameField.prev();
            if (editorLevelView.getMainPointX() == -maxX) {
                editorLevelView.start();
            }
            else {
                editorLevelView.setMainPointX(maxX);
                mainMenuView.start();
            }
        }
        if (buttonsGameField[1].tripped) {
            if(sound) {
                mSoundPool.play(pressBtn, 1, 1, 0, 0, 1);
            }
            buttonsGameField[1].tripped = false;
            level++;
            gameField.start((int)Math.ceil((10 * level + 235)/49), (int)Math.floor((10 * level + 235)/49), (int)Math.round((2 * level + 145)/49),
                    Math.round((10 * level + 235)/49), Math.round((8*level + 90)/49), Math.round((7*level + 140)/49),(int)level *50, options.sound);
        }
        if (buttonsGameField[2].tripped) {
            if(sound) {
                mSoundPool.play(pressBtn, 1, 1, 0, 0, 1);
            }
            buttonsGameField[2].tripped = false;
            setCounter((int) level * 50 - 50);
            gameField.gameBlocks.counter = (int) level * 50 - 50;
            gameField.start((int)Math.ceil((10 * level + 235)/49), (int)Math.floor((10 * level + 235)/49), (int)Math.round((2 * level + 145)/49),
                    Math.round((10 * level + 235)/49), Math.round((8*level + 90)/49), Math.round((7*level + 140)/49),(int)level *50, options.sound);

        }
        if(mainMenuView.isVisible()){
            mainMenuView.update(fps);
        }
        if(editorLevelView.isVisible()){
            editorLevelView.update(fps);
        }
        if(gameField.isVisible()){
            gameField.update(fps);
        }
        if(credits.isVisible()){
            credits.update(fps);
        }
        if(options.isVisible()){
            options.update(fps);
        }
        cookMan.update(fps);



    }
    public void setCounter(int x) {
        counter = x;
        mainMenuView.counter = x;
        gameField.counter = x;
    }

    public void motion(MotionEvent motionEvent) {
        cookMan.touch(motionEvent);
        if(mainMenuView.isVisible()){
            mainMenuView.touch(motionEvent);
        }
        if(editorLevelView.isVisible()){
            editorLevelView.touch(motionEvent);
        }
        if(gameField.isVisible()){

            gameField.touch(motionEvent,sound);
        }
        if(credits.isVisible()){
            credits.touch(motionEvent);
        }
        if(options.isVisible()){
            options.touch(motionEvent);
//            Log.d("Option.sound", " " + sound);
        }
    }
    protected void startBtnEditLvl() {
        Bitmap[] tempBitmap = new Bitmap[4];
        tempBitmap[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_0_normal_pressed),
                normalSizeBtn / 2 , normalSizeBtn / 2,false);
        tempBitmap[1] = Bitmap.createScaledBitmap(imagesGameField[0][0].getBitmap(),
                normalSizeBtn / 2 , normalSizeBtn / 2,false);
        tempBitmap[2] = Bitmap.createScaledBitmap(imagesGameField[1][0].getBitmap(),
                normalSizeBtn / 2 , normalSizeBtn / 2,false);
        tempBitmap[3] = Bitmap.createScaledBitmap(imagesGameField[2][0].getBitmap(),
                normalSizeBtn / 2 , normalSizeBtn / 2,false);
        buttonsEditLvl = new Button[51];
        buttonsEditLvl[0] = new Button(
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.cursorback_normal),
                        normalSizeBtn / 2 , normalSizeBtn / 2,false),
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.cursorback_pressed),
                        normalSizeBtn / 2 , normalSizeBtn / 2,false));
        buttonsEditLvl[1] = new Button(tempBitmap[1], tempBitmap[0]);
        buttonsEditLvl[2] = new Button(tempBitmap[2], tempBitmap[0]);
        buttonsEditLvl[3] = new Button(tempBitmap[3], tempBitmap[0]);
        buttonsEditLvl[4] = new Button(tempBitmap[1], tempBitmap[0]);
        buttonsEditLvl[5] = new Button(tempBitmap[2], tempBitmap[0]);
        buttonsEditLvl[6] = new Button(tempBitmap[3], tempBitmap[0]);
        buttonsEditLvl[7] = new Button(tempBitmap[1], tempBitmap[0]);
        buttonsEditLvl[8] = new Button(tempBitmap[2], tempBitmap[0]);
        buttonsEditLvl[9] = new Button(tempBitmap[3], tempBitmap[0]);
        buttonsEditLvl[10] = new Button(tempBitmap[1], tempBitmap[0]);
        buttonsEditLvl[11] = new Button(tempBitmap[2], tempBitmap[0]);
        buttonsEditLvl[12] = new Button(tempBitmap[3], tempBitmap[0]);
        buttonsEditLvl[13] = new Button(tempBitmap[1], tempBitmap[0]);
        buttonsEditLvl[14] = new Button(tempBitmap[2], tempBitmap[0]);
        buttonsEditLvl[15] = new Button(tempBitmap[3], tempBitmap[0]);
        buttonsEditLvl[16] = new Button(tempBitmap[1], tempBitmap[0]);
        buttonsEditLvl[17] = new Button(tempBitmap[2], tempBitmap[0]);
        buttonsEditLvl[18] = new Button(tempBitmap[3], tempBitmap[0]);
        buttonsEditLvl[19] = new Button(tempBitmap[1], tempBitmap[0]);
        buttonsEditLvl[20] = new Button(tempBitmap[2], tempBitmap[0]);
        buttonsEditLvl[21] = new Button(tempBitmap[3], tempBitmap[0]);
        buttonsEditLvl[22] = new Button(tempBitmap[1], tempBitmap[0]);
        buttonsEditLvl[23] = new Button(tempBitmap[2], tempBitmap[0]);
        buttonsEditLvl[24] = new Button(tempBitmap[3], tempBitmap[0]);
        buttonsEditLvl[25] = new Button(tempBitmap[1], tempBitmap[0]);
        buttonsEditLvl[26] = new Button(tempBitmap[2], tempBitmap[0]);
        buttonsEditLvl[27] = new Button(tempBitmap[3], tempBitmap[0]);
        buttonsEditLvl[28] = new Button(tempBitmap[1], tempBitmap[0]);
        buttonsEditLvl[29] = new Button(tempBitmap[2], tempBitmap[0]);
        buttonsEditLvl[30] = new Button(tempBitmap[3], tempBitmap[0]);
        buttonsEditLvl[31] = new Button(tempBitmap[1], tempBitmap[0]);
        buttonsEditLvl[32] = new Button(tempBitmap[2], tempBitmap[0]);
        buttonsEditLvl[33] = new Button(tempBitmap[3], tempBitmap[0]);
        buttonsEditLvl[34] = new Button(tempBitmap[1], tempBitmap[0]);
        buttonsEditLvl[35] = new Button(tempBitmap[2], tempBitmap[0]);
        buttonsEditLvl[36] = new Button(tempBitmap[3], tempBitmap[0]);
        buttonsEditLvl[37] = new Button(tempBitmap[1], tempBitmap[0]);
        buttonsEditLvl[38] = new Button(tempBitmap[2], tempBitmap[0]);
        buttonsEditLvl[39] = new Button(tempBitmap[3], tempBitmap[0]);
        buttonsEditLvl[40] = new Button(tempBitmap[1], tempBitmap[0]);
        buttonsEditLvl[41] = new Button(tempBitmap[2], tempBitmap[0]);
        buttonsEditLvl[42] = new Button(tempBitmap[3], tempBitmap[0]);
        buttonsEditLvl[43] = new Button(tempBitmap[1], tempBitmap[0]);
        buttonsEditLvl[44] = new Button(tempBitmap[2], tempBitmap[0]);
        buttonsEditLvl[45] = new Button(tempBitmap[3], tempBitmap[0]);
        buttonsEditLvl[46] = new Button(tempBitmap[1], tempBitmap[0]);
        buttonsEditLvl[47] = new Button(tempBitmap[2], tempBitmap[0]);
        buttonsEditLvl[48] = new Button(tempBitmap[3], tempBitmap[0]);
        buttonsEditLvl[49] = new Button(tempBitmap[1], tempBitmap[0]);
        buttonsEditLvl[50] = new Button(tempBitmap[2], tempBitmap[0]);

    }
    protected void pause() {
        if (gameField.gameBlocks != null) {
            Log.d("soundPause", "0 ");
//            if (gameField.gameBlocks.mSoundPool != null) {
//                gameField.gameBlocks.mSoundPool.release();
//                gameField.gameBlocks.mSoundPool = null;
//                Log.d("soundPause", "1 ");
//            }
        }
//        if(mainMenuView.mSoundPool != null){
//            mainMenuView.mSoundPool.release();
//            mainMenuView.mSoundPool = null;
//        }
    }
    protected void startImage() {
// up right down left number
// 0    0     0    0    0
// 0    0     0    1    1
// 0    0     1    0    2
// 0    0     1    1    3
// 0    1     0    0    4
// 0    1     0    1    5
// 0    1     1    0    6
// 0    1     1    1    7
// 1    0     0    0    8
// 1    0     0    1    9
// 1    0     1    0    10
// 1    0     1    1    11
// 1    1     0    0    12
// 1    1     0    1    13
// 1    1     1    0    14
// 1    1     1    1    15
        imagesGameField[0][0] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_0_normal));
        imagesGameField[0][1] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_0_left));
        imagesGameField[0][2] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_0_down));
        imagesGameField[0][3] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_0_down_left));
        imagesGameField[0][4] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_0_right));
        imagesGameField[0][5] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_0_right_left));
        imagesGameField[0][6] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_0_right_down));
        imagesGameField[0][7] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_0_right_down_left));
        imagesGameField[0][8] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_0_up));
        imagesGameField[0][9] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_0_up_left));
        imagesGameField[0][10] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_0_up_down));
        imagesGameField[0][11] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_0_up_down_left));
        imagesGameField[0][12] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_0_up_right));
        imagesGameField[0][13] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_0_up_right_left));
        imagesGameField[0][14] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_0_up_right_down));
        imagesGameField[0][15] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_0_up_right_down_left));

        imagesGameField[1][0] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_1_normal));
        imagesGameField[1][1] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_1_left));
        imagesGameField[1][2] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_1_down));
        imagesGameField[1][3] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_1_down_left));
        imagesGameField[1][4] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_1_right));
        imagesGameField[1][5] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_1_right_left));
        imagesGameField[1][6] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_1_right_down));
        imagesGameField[1][7] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_1_right_down_left));
        imagesGameField[1][8] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_1_up));
        imagesGameField[1][9] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_1_up_left));
        imagesGameField[1][10] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_1_up_down));
        imagesGameField[1][11] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_1_up_down_left));
        imagesGameField[1][12] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_1_up_right));
        imagesGameField[1][13] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_1_up_right_left));
        imagesGameField[1][14] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_1_up_right_down));
        imagesGameField[1][15] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_1_up_right_down_left));

        imagesGameField[2][0] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_2_normal));
        imagesGameField[2][1] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_2_left));
        imagesGameField[2][2] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_2_down));
        imagesGameField[2][3] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_2_down_left));
        imagesGameField[2][4] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_2_right));
        imagesGameField[2][5] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_2_right_left));
        imagesGameField[2][6] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_2_right_down));
        imagesGameField[2][7] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_2_right_down_left));
        imagesGameField[2][8] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_2_up));
        imagesGameField[2][9] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_2_up_left));
        imagesGameField[2][10] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_2_up_down));
        imagesGameField[2][11] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_2_up_down_left));
        imagesGameField[2][12] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_2_up_right));
        imagesGameField[2][13] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_2_up_right_left));
        imagesGameField[2][14] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_2_up_right_down));
        imagesGameField[2][15] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_2_up_right_down_left));

        imagesGameField[3][0] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_3_normal));
        imagesGameField[3][1] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_3_left));
        imagesGameField[3][2] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_3_down));
        imagesGameField[3][3] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_3_down_left));
        imagesGameField[3][4] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_3_right));
        imagesGameField[3][5] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_3_right_left));
        imagesGameField[3][6] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_3_right_down));
        imagesGameField[3][7] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_3_right_down_left));
        imagesGameField[3][8] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_3_up));
        imagesGameField[3][9] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_3_up_left));
        imagesGameField[3][10] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_3_up_down));
        imagesGameField[3][11] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_3_up_down_left));
        imagesGameField[3][12] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_3_up_right));
        imagesGameField[3][13] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_3_up_right_left));
        imagesGameField[3][14] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_3_up_right_down));
        imagesGameField[3][15] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_3_up_right_down_left));

        imagesGameField[4][0] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_4_normal));
        imagesGameField[4][1] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_4_left));
        imagesGameField[4][2] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_4_down));
        imagesGameField[4][3] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_4_down_left));
        imagesGameField[4][4] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_4_right));
        imagesGameField[4][5] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_4_right_left));
        imagesGameField[4][6] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_4_right_down));
        imagesGameField[4][7] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_4_right_down_left));
        imagesGameField[4][8] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_4_up));
        imagesGameField[4][9] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_4_up_left));
        imagesGameField[4][10] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_4_up_down));
        imagesGameField[4][11] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_4_up_down_left));
        imagesGameField[4][12] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_4_up_right));
        imagesGameField[4][13] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_4_up_right_left));
        imagesGameField[4][14] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_4_up_right_down));
        imagesGameField[4][15] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.block_4_up_right_down_left));

        imagesGameField[5][0] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.anim_0_0));
        imagesGameField[5][1] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.anim_0_1));
        imagesGameField[5][2] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.anim_0_2));
        imagesGameField[5][3] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.anim_0_3));

        imagesGameField[6][0] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.anim_1_0));
        imagesGameField[6][1] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.anim_1_1));
        imagesGameField[6][2] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.anim_1_2));
        imagesGameField[6][3] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.anim_1_3));

        imagesGameField[7][0] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.anim_2_0));
        imagesGameField[7][1] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.anim_2_1));
        imagesGameField[7][2] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.anim_2_2));
        imagesGameField[7][3] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.anim_2_3));

        imagesGameField[8][0] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.anim_3_0));
        imagesGameField[8][1] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.anim_3_1));
        imagesGameField[8][2] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.anim_3_2));
        imagesGameField[8][3] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.anim_3_3));

        imagesGameField[9][0] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.anim_4_0));
        imagesGameField[9][1] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.anim_4_1));
        imagesGameField[9][2] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.anim_4_2));
        imagesGameField[9][3] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.anim_4_3));

        imagesGameField[10][0] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.empty_block));
        imagesGameField[10][1] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.empty_block_left));
        imagesGameField[10][2] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.empty_block_up));
        imagesGameField[10][3] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.empty_block_down));
        imagesGameField[10][4] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.empty_block_right));
        imagesGameField[10][5] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.empty_block_right_up));
        imagesGameField[10][6] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.empty_block_right_down));
        imagesGameField[10][7] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.empty_block_left_up));
        imagesGameField[10][8] = new Image(BitmapFactory.decodeResource(context.getResources(), R.drawable.empty_block_left_down));

    }
    public void resume(float level, int[] blocks, int maxCounter) {
        this.level = level;
        gameField.resume((int)Math.ceil((10 * level + 235)/49), (int)Math.floor((10 * level + 235)/49), 3, Math.round((8*level + 90)/49), Math.round((7*level + 140)/49),(int)level *50, options.sound);
        //gameField.resume(field/10000000, field%10000000/100000,field%100000/10000,field%10000/100,field%100, maxCounter);
        gameField.gameBlocks.resume(blocks);
    }
}
