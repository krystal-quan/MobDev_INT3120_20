package com.example.week7;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnSend, btnBroadcast;
    TextView tvFb, tvBroadcast;

    EditText edtName;

//    BroadcastReceiver receiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String text = intent.getStringExtra("message");
//            tvBroadcast.setText(text);
//        }
//    };

    NewBroadcastReceiver receiver = new NewBroadcastReceiver();
    int MY_REQUEST_CODE = 1000001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.explicit_activity_main);

        edtName = (EditText) findViewById(R.id.editTextText);
        tvFb = (TextView) findViewById(R.id.tvFb);
        tvBroadcast = (TextView) findViewById(R.id.tvBroadcast);

        btnSend = (Button) findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });

        btnBroadcast = (Button) findViewById(R.id.btnBroadcast);
        btnBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendBroadcast1();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter("com.example.BROADCAST_EXAMPLE");
        registerReceiver(receiver, intentFilter);
    }

    public void sendMessage() {
        String fullName = edtName.getText().toString();
        String message = "Hello, Stranger!!! I am alone, so don't forget to hi me!!!";

        Intent intent = new Intent(this, GreetingActivity.class);
        intent.putExtra("fullName", fullName);
        intent.putExtra("message", message);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


//        this.startActivity(intent);
        this.startActivityForResult(intent, MY_REQUEST_CODE);
    }

    public void sendBroadcast1() {
        Intent intent = new Intent("com.example.BROADCAST_EXAMPLE");
        intent.putExtra("message", "This is a broadcast event.");
        intent.setAction("com.example.BROADCAST_EXAMPLE");
        sendBroadcast(intent);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == MY_REQUEST_CODE) {
            String feedback = data.getStringExtra("feedback");
            tvFb.setText(feedback);

        }
        else {
            tvFb.setText("Something Wrong happened.");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}