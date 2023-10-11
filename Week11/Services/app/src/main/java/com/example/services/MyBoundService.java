package com.example.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyBoundService extends Service {
    public class MyBinder extends Binder {
        MyBoundService getService() {
            return MyBoundService.this;
        }
    }
    private MyBinder myBinder = new MyBinder();
    MediaPlayer mp;

    @Override
    public void onCreate() {
        Log.i("Create", "Create");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.i("Destroy", "Destroy");
        super.onDestroy();
        if (mp != null) {
            mp.release();
            mp = null;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public void startMusic() {
        Log.i("Start Music", "Start Music");
        if (mp == null) {
            mp = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
            mp.setLooping(true);
            mp.start();
        }
    }
}
