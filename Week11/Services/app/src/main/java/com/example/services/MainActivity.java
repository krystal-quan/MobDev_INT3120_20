package com.example.services;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Button;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private MyBoundService myBoundService;
    private boolean isBound = false;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyBoundService.MyBinder myBinder = (MyBoundService.MyBinder) iBinder;
            myBoundService = myBinder.getService();
            myBoundService.startMusic();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound = false;
        }
    };
    public static final String CHANNEL_ID = "musicChannel";
    Button btnStart, btnStop, btnStartBound, btnStopBound, btnStartBackground;
    EditText edtText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = (Button) findViewById(R.id.btnStartMusic);
        btnStop = (Button) findViewById(R.id.btnStopMusic);
        btnStartBound = (Button) findViewById(R.id.btnStartBoundService);
        btnStopBound = (Button) findViewById(R.id.btnStopBoundService);
        btnStartBackground = (Button) findViewById(R.id.btnStartBackgroundService);
        btnStart.setOnClickListener(view -> {
            startMusic();
        });
        btnStop.setOnClickListener(view -> {
            stopMusic();
        });
        btnStartBound.setOnClickListener(view -> {
            startBound();
        });
        btnStopBound.setOnClickListener(view -> {
            stopBound();
        });
        edtText = (EditText) findViewById(R.id.edtText);
        btnStartBackground.setOnClickListener(view -> {
            startBackground();
        });


    }

    private void startMusic() {
        Intent serviceIntent = new Intent(this, MusicService.class);
        serviceIntent.putExtra("inputExtra", edtText.getText().toString());
        ContextCompat.startForegroundService(this, serviceIntent);
    }

    private void stopMusic() {
        stopService(new Intent(MainActivity.this, MusicService.class));
    }

    private void startBound() {
        Intent serviceIntent = new Intent(this, MyBoundService.class);
        bindService(serviceIntent, serviceConnection, BIND_AUTO_CREATE);
    }

    private void stopBound() {
        if (isBound) {
            unbindService(serviceConnection);
            isBound = false;
        }
    }

    private void startBackground() {
        Intent serviceIntent = new Intent(this, MyBackgroundService.class);
        startService(serviceIntent);
    }


}