package com.example.sensor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private TextView tvCoordinates;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        tvCoordinates = findViewById(R.id.tvCoordinates);
        setUpSensor();
    }

    public void setUpSensor() {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST, SensorManager.SENSOR_DELAY_FASTEST);

    }

    @Override
    protected void onPause() {
        super.onPause();

        // Don't receive any more updates from either sensor.
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float sides = event.values[0];
            float upDown = event.values[1];
            sides = Math.round(sides * 100.0f) / 100.0f;
            upDown = Math.round(upDown * 100.0f) / 100.0f;
            tvCoordinates.setRotationX(upDown * 3f);
            tvCoordinates.setRotationY(sides * 3f);
            tvCoordinates.setRotation(-sides);
            tvCoordinates.setTranslationX(sides * -10);
            tvCoordinates.setTranslationY(upDown * 10);

            int color;
            if (Math.abs(Math.round(upDown)) <=1  && Math.abs(Math.round(sides)) <= 1) {
                color = Color.GREEN;
            } else {
                color = Color.RED;
            }
            tvCoordinates.setBackgroundColor(color);
            tvCoordinates.setText("X: " + sides + "\nY: " + upDown);

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}