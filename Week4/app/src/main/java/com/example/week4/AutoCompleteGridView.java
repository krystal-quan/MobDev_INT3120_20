package com.example.week4;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AutoCompleteGridView extends AppCompatActivity implements TextWatcher {
    TextView selection;
    AutoCompleteTextView edit;
    String[] items = {"Android","IPhone","WindowsMobile", "Blackberry","WebOS","Ubuntu","Windows7", "Max OS X", "Android1"};
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.auto_complete_text_view);
        selection = (TextView) findViewById(R.id.selection);
        edit = (AutoCompleteTextView) findViewById(R.id.edit);
        edit.addTextChangedListener(this);
        edit.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, items));
    }
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        selection.setText(edit.getText());
    }
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }
    public void afterTextChanged(Editable s) {

    }


}
