package ru.takemakekeep.chainofcakes;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.content.SharedPreferences;
import android.view.Gravity;
import android.view.WindowManager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class GameView extends Activity {
    GameEngine gameEngine;
    int counter ;
    int maxCounter ;
    float level;
    int[] blocks;
    boolean music;
    boolean sound;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Делаю так чтобы клавиши громкости изменяли громкость аудио потока Медиа(игры)
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        Display display = getWindowManager().getDefaultDisplay();
        Point sizeDisp = new Point();
        display.getSize(sizeDisp);
        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
        counter = save.getInt("Counter",0);
        maxCounter = save.getInt("maxCounter",0);
        level = save.getInt("level",0);
        blocks = new int[((int)Math.ceil((10 * level + 235)/49)) * ((int)Math.floor((10 * level + 235)/49))];
        for (int i = 0; i < blocks.length; i++) {
            blocks[i] = save.getInt(Integer.toString(i),0);
        }
        music = save.getBoolean("Music", false);
        sound = save.getBoolean("Sound", true);

        if(music) {
            startService(new Intent(this, MyService.class));
        }

        gameEngine = new GameEngine(this, sizeDisp.x, sizeDisp.y, counter, music,sound);

//        gameEngine.resume(counter, level,blocks,maxCounter);
        gameEngine.viewFieldAndAllElem.setCounter(counter);
        gameEngine.viewFieldAndAllElem.resume(level, blocks, maxCounter);

        setContentView(gameEngine);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        WindowManager.LayoutParams windowParams = new WindowManager.LayoutParams();
        windowParams.gravity = Gravity.BOTTOM;
        windowParams.x = 0;
        windowParams.y = 0;
        windowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        windowParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        windowParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
        windowParams.format = PixelFormat.TRANSLUCENT;
        windowParams.windowAnimations = 0;

        WindowManager wm = getWindowManager();
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-9488118969901249/1616749443");
        //adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        wm.addView(adView, windowParams);


    }
    @Override
    public void onResume() {

        Log.e("GameView  ", "onResume()");
        gameEngine.resume(counter, level,blocks,maxCounter);
        if(music) {
            startService(new Intent(this, MyService.class));
        }
        super.onResume();
    }
    @Override
    public void onPause() {

        Log.e("GameView  ", "onPause()");
        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
        SharedPreferences.Editor editor = save.edit();
        editor.putBoolean("Music",gameEngine.viewFieldAndAllElem.options.music);
        editor.putBoolean("Sound",gameEngine.viewFieldAndAllElem.options.sound);
        editor.putInt("Counter",gameEngine.viewFieldAndAllElem.mainMenuView.counter);
        if (gameEngine.viewFieldAndAllElem.gameField.gameBlocks!=null) {
            editor.putInt("maxCounter", gameEngine.viewFieldAndAllElem.gameField.gameBlocks.maxCounter);
            // Изменение 16.04.2020
//            maxCounter = gameEngine.viewFieldAndAllElem.gameField.gameBlocks.maxCounter;
        }
        editor.putInt("level", (int)gameEngine.viewFieldAndAllElem.level);

        int[] reserv = gameEngine.viewFieldAndAllElem.gameField.gameBlocks.reserveBlock();
        for (int i = 0; i < reserv.length; i++) {
            editor.putInt(Integer.toString(i),reserv[i]);

        }
        // Изменение 16.04.2020
//        music = gameEngine.viewFieldAndAllElem.options.music;
//        sound = gameEngine.viewFieldAndAllElem.options.sound;
//        counter = gameEngine.viewFieldAndAllElem.mainMenuView.counter;
//        level = (int)gameEngine.viewFieldAndAllElem.level;
//        blocks = gameEngine.viewFieldAndAllElem.gameField.gameBlocks.reserveBlock();
//        for (int i = 0; i < blocks.length; i++) {
//            editor.putInt(Integer.toString(i),blocks[i]);
//
//        }
        editor.commit();
        gameEngine.pause();
        stopService(new Intent(this, MyService.class));
        super.onPause();
    }
}
