package com.example.sqlite;

import static android.app.ProgressDialog.show;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FeedReaderDbHelper sqLite;
    TextView selection;
    EditText edtName, edtNumber;
    ArrayList<String> items = new ArrayList<>();
    ArrayAdapter<String> aa;
    Button btnSave, btnLoad;
    GridView gv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_selector);

        TabHost tabs = (TabHost) findViewById(R.id.tabhost);
        tabs.setup();
        TabHost.TabSpec spec;
        spec =tabs.newTabSpec("tag1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Save Database");
        tabs.addTab(spec);
        spec=tabs.newTabSpec("tag2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Show Database");
        tabs.addTab(spec);
        tabs.setCurrentTab(0);


        sqLite = new FeedReaderDbHelper(this);


        edtName = (EditText) findViewById(R.id.edtName);
        edtNumber = (EditText) findViewById(R.id.edtNumber);
        btnSave = (Button) findViewById(R.id.btnSaveDatabase);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtName.getText().toString() == "" || edtNumber.getText().toString() == "") {
                    doToast("Name and Number must be filled!!!");
                } else {
                    sqLite.exampleQuerySave(edtName.getText().toString(), Integer.parseInt(edtNumber.getText().toString()));
                    doToast("Success");
                }
            }
        });
        btnLoad = (Button) findViewById(R.id.btnLoadData);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor detail = sqLite.getData("SELECT * FROM data");
                while (detail.moveToNext()) {
                    String name = detail.getString(1);
                    int number = detail.getInt(2);
                    items.add(name + ": " + number);
                    resetGridView();
                }
            }
        });

        selection = (TextView) findViewById(R.id.selection);
        gv = (GridView) findViewById(R.id.grid);
        aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,items );
        gv.setAdapter(aa);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selection.setText(items.get(i));
            }
        });
    }

    public void doToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public void resetGridView() {
        aa = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        gv.setAdapter(aa);
    }
}