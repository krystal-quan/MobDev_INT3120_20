package com.example.week4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    String[] mobileArray = {"Android", "Apple", "IOS", "Bla", "PopOS", "Ubuntu", "Linux", "Still Linux"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_layout);
        setTitle(R.string.app_name);

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_main, mobileArray);
        ListView listView = (ListView) findViewById(R.id.android_list);
        listView.setAdapter(adapter);

    }
}