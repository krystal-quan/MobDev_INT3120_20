package com.example.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btnLoad, btnLoadCallLog, btnBrowser;
    private ContentResolver resolver;
    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLoad = (Button) findViewById(R.id.btnLoad);
        btnLoad.setOnClickListener(view -> {
            doLoad();
        });
        btnLoadCallLog = (Button) findViewById(R.id.btnLoadCallLogs);
        btnLoadCallLog.setOnClickListener(view -> {
            doLoadCallLog();
        });
        btnBrowser = (Button) findViewById(R.id.btnBrowser);
        btnBrowser.setOnClickListener(view -> {
            doBrowser();
        });
        resolver = getContentResolver();

        if (checkSelfPermission(android.Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.READ_CONTACTS}, 100);
        }

        if (checkSelfPermission(android.Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.READ_CALL_LOG}, 100);
        }



    }

    @SuppressLint("Range")
    protected void doLoad() {
//        Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI,
//                new String[]{ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts._ID},
//                "name LIKE ?",
//                new String[]{"%h%"},
//                "id");
        Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI,
                null,
                null,
                null,
                null);
        while (cursor.moveToNext()) {
            Cursor cursor1 = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                    new String[]{cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))},
                    null);

            while (cursor1.moveToNext()) {
                Log.i("Message", "Name: " + cursor1.getString(cursor1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));
                Log.i("Message", "Phone:" + cursor1.getString(cursor1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));

            }
        }
    }

    @SuppressLint("Range")
    protected void doLoadCallLog() {
        Cursor cursor = resolver.query(CallLog.Calls.CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            int type = cursor.getInt(cursor.getColumnIndex(CallLog.Calls.TYPE));
            String callType = "";
            if (type == 2) {
                callType = "Outgoing";
            }
            if (type == 1) {
                callType = "Incoming";
            }
            if (type == 3) {
                callType = "Missed";
            }
            int duration = cursor.getInt(cursor.getColumnIndex(CallLog.Calls.DURATION));
            int hr, min, sec;
            String time = "";
            hr = duration / 3600;
            min = (duration % 3600) / 60;
            sec = duration % 60;
            if (hr > 0) {
                if (hr < 10)
                    time = "0" + hr + ":";
                else
                    time = hr + ":";
            }
            if (min < 10)
                time += "0" + min + ":";
            else
                time += min + ":";
            if (sec < 10)
                time += "0" + sec;
            else
                time += sec;

            Log.i("Message", "Name: " + cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME)));
            Log.i("Message", "Phone: " + cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER)));
            Log.i("Message", "Date: " + cursor.getString(cursor.getColumnIndex(CallLog.Calls.DATE)));
            Log.i("Message", "Duration: " + time);
            Log.i("Message", "Type: " + callType);
        }
    }

    @SuppressLint("Range")
    protected void doBrowser() {
        Uri uri = Uri.parse("content://browser/bookmarks");
        Cursor cursor = resolver.query(uri, new String[]{"_id", "url", "title"}, null, null, null);
        while (cursor.moveToNext()) {
            Log.i("Message", "ID: " + cursor.getString(cursor.getColumnIndex("_id")));
            Log.i("Message", "URL: " + cursor.getString(cursor.getColumnIndex("url")));
            Log.i("Message", "Title: " + cursor.getString(cursor.getColumnIndex("title")));
        }
    }
}