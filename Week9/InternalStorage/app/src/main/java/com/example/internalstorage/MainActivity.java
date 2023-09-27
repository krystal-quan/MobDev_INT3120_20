package com.example.internalstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public static final String INTERNAL_PATH = Environment.getDataDirectory().getPath() + "/data/com.example.internalstorage/";
    public static final String EXTERNAL_PATH = Environment.getExternalStorageDirectory().getPath() + "/Android/data/com.example.internalstorage/";

    public static final String PROFILE_FILE = "profile.txt";
    EditText edtName, edtFullName;
    TextView tvName, tvFullname;

    Button btnSubmit, btnGetData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = (EditText) findViewById(R.id.edtUsername);
        edtFullName = (EditText) findViewById(R.id.edtFullname);
        tvName = (TextView) findViewById(R.id.tvName);
        tvFullname = (TextView) findViewById(R.id.tvFullname);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });
        btnGetData = (Button) findViewById(R.id.btnGetData);
        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showData();
            }
        });
    }

    private void saveData() {
        try {
            String path = INTERNAL_PATH + PROFILE_FILE;
//            String path = EXTERNAL_PATH + PROFILE_FILE;
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            else {
                file.delete();
                file.createNewFile();
            }

            FileOutputStream outputStream = new FileOutputStream(file, true);

            String data = edtName.getText().toString() + ":" + edtFullName.getText().toString();
            byte buff[] = data.getBytes();
            outputStream.write(buff, 0, buff.length);
            outputStream.close();
            Toast.makeText(this, "Submited", Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error Somewhere in Code.", Toast.LENGTH_LONG).show();
        }
    }

    private void showData() {
        String result = getData();
        if (result == null) {
            Toast.makeText(this, "Something wrong happened while trying to get data, please try again.", Toast.LENGTH_LONG).show();
            return;
        }
        else {
            String listInfo[] = result.split(":");
            tvName.setVisibility(TextView.VISIBLE);
//            tvName.setText("Current Name: " + listInfo[0]);
            tvName.setText(Environment.getExternalStorageDirectory().getPath());
            tvFullname.setVisibility(TextView.VISIBLE);
            tvFullname.setText("Current Full Name: " + listInfo[1]);
            return;
        }
    }

    private String getData() {
        try {
            String path = INTERNAL_PATH + PROFILE_FILE;
//            String path = EXTERNAL_PATH + PROFILE_FILE;
            File file = new File(path);
            if (!file.exists()) {
                Toast.makeText(this, "No Data saved yet", Toast.LENGTH_LONG).show();
                return null;
            }

            FileInputStream inputStream = new FileInputStream(path);
            String data = "";
            int len;
            byte buff[] = new byte[1024];
            while ((len = inputStream.read(buff)) > 0) {
                data += new String(buff, 0, len);
            }
            inputStream.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}