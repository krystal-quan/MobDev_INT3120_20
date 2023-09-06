package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    protected int totalDonated = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
    }


    public void onBtnDonateClick(View v) {
        TextView txtDonateTotal = findViewById(R.id.txtDonateTotal);
        EditText edtDonateAmount = findViewById(R.id.edtDonateAmount);

        totalDonated = totalDonated + Integer.parseInt(edtDonateAmount.getText().toString());
        txtDonateTotal.setText("Total so far: " + totalDonated + "$");
    }
}