package com.example.week8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    StaticBroadcastReceiver staticBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        staticBroadcastReceiver = new StaticBroadcastReceiver();
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter =  new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(staticBroadcastReceiver, intentFilter);

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(staticBroadcastReceiver);
    }
}