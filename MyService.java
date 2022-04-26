package ru.takemakekeep.chainofcakes;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = "MyService";
    MediaPlayer player;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        Log.d("MyService", "onCreate()");

        player = MediaPlayer.create(this, R.raw.mainsound);
        player.setLooping(true); // зацикливаем
    }
    @Override
    public void onDestroy() {
        player.stop();
        Log.d("MyService", "onDestroy()");
    }

  //  @Override
  //  public void onStart(Intent intent, int startid) {
   //     player.start();
   //     Log.d("MyService", "onStart()");
   // }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        player.start();
        Log.d("MyService", "onStartCommand()");
        return Service.START_STICKY;
    }

}
