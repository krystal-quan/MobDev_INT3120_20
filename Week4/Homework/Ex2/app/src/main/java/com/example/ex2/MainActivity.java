package com.example.ex2;

import androidx.annotation.ChecksSdkIntAtLeast;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_layout);

    }

    public void onSubmitButtonClick (View v) {
        EditText edtName = (EditText) findViewById(R.id.editTextName);
        EditText edtPhone = (EditText) findViewById(R.id.editTextPhone);
        RadioGroup rgCheese = (RadioGroup) findViewById(R.id.chooseCheeseRadio);
        RadioGroup rgShape = (RadioGroup) findViewById(R.id.chooseShapeRadio);
        CheckBox tp1 = (CheckBox) findViewById(R.id.cbTopping1);
        CheckBox tp2 = (CheckBox) findViewById(R.id.cbTopping2);
        CheckBox tp3 = (CheckBox) findViewById(R.id.cbTopping3);
        TextView detail = (TextView) findViewById(R.id.tvOrderList);

        RadioButton rbCheese = (RadioButton) findViewById(rgCheese.getCheckedRadioButtonId());
        RadioButton rbShape = (RadioButton) findViewById(rgShape.getCheckedRadioButtonId());

        String detailString = "";
        detailString += edtName.getText().toString() + " - " + edtPhone.getText().toString()
                + " - " + rbCheese.getText().toString() + " - " + rbShape.getText().toString();

        if (tp1.isChecked()) detailString += " - " + tp1.getText().toString();
        if (tp2.isChecked()) detailString += " - " + tp2.getText().toString();
        if (tp3.isChecked()) detailString += " - " + tp3.getText().toString();
        detail.setText(detailString);

        edtName.setText("");
        edtPhone.setText("");
        rbCheese.setChecked(false);
        rbShape.setChecked(false);
        tp1.setChecked(false);
        tp2.setChecked(false);
        tp3.setChecked(false);
    }
}