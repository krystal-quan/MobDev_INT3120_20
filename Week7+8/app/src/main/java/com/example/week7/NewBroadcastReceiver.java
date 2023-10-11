package com.example.week7;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NewBroadcastReceiver extends android.content.BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra("message");
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
