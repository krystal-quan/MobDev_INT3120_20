package com.example.services;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyBackgroundService extends Service {

    private static final String TAG = "MyBackgroundService";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flag, int startId) {
        CountDownTimer countDownTimer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {
                Log.i(TAG, "onTick: " + l);

            }

            @Override
            public void onFinish() {
                Log.i(TAG, "onFinish: ");
            }
        }.start();
        return super.START_NOT_STICKY;
    }
}
