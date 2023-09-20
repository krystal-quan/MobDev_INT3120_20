package com.example.week4;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GridViewTest extends AppCompatActivity implements AdapterView.OnItemClickListener {
    TextView selection;
    String[] items = {"1", "2", "3", "Item 1", "Item 2", "Item 3"};

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.grid_view);
        selection = (TextView) findViewById(R.id.selection);
        GridView gv = (GridView) findViewById(R.id.grid);
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,items );
        gv.setAdapter(aa);
        gv.setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> parent, View v,int position, long id) {
        selection.setText(items[position]);
    }

}
