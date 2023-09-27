package com.example.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!DataLocalManager.getFirstInstall()) {
            Toast.makeText(this, "THIS IS THE FIRST TIME YOU USED THIS APP.", Toast.LENGTH_LONG).show();
            DataLocalManager.setFirstInstall(true);
        }
    }
}