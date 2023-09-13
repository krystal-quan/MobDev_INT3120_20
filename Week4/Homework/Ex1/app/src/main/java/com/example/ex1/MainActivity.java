package com.example.ex1;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener {
    private int totalDonated = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Change layout with the one you need.
        setContentView(R.layout.linear_layout);

        TextView tvTotal = (TextView) findViewById(R.id.tvTotal);
        Button btnDonate = (Button) findViewById(R.id.btnDonate);
        NumberPicker numberPicker = (NumberPicker) findViewById(R.id.numberPicker);

        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(1000);
        numberPicker.setWrapSelectorWheel(false);
        numberPicker.setOnValueChangedListener(this);



    }

    public void onBtnDonateClick (View v) {
        TextView tvTotal = (TextView) findViewById(R.id.tvTotal);
        EditText edtAmount = (EditText) findViewById(R.id.editTextNumber);
        NumberPicker numberPicker = (NumberPicker) findViewById(R.id.numberPicker);
        ProgressBar pb = (ProgressBar) findViewById(R.id.progress_horizontal);

        int donatedAmount = numberPicker.getValue() * Integer.parseInt(edtAmount.getText().toString());
        totalDonated = totalDonated + donatedAmount;
        pb.setProgress(Integer.min(totalDonated, pb.getMax()));
        tvTotal.setText("Total so far: " + totalDonated + "$");
    }

    @Override
    public void onValueChange(NumberPicker np, int oldVal, int newVal) {
        Log.i("Value is", "" + newVal);
    }


}