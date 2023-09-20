package com.example.week7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class ImplicitActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        //Call Activity
        Intent callActivity = new Intent(Intent.ACTION_DIAL);
        callActivity.setData(Uri.parse("tel:0178346192"));
        startActivity(callActivity);

//        //Web search Activity
//        Intent webSearchActivity = new Intent(Intent.ACTION_WEB_SEARCH);
//        webSearchActivity.putExtra(SearchManager.QUERY, "MobDev20");
//        startActivity(webSearchActivity);
//
//        //Send Activity
//        Intent sendIntent = new Intent();
//        sendIntent.setAction(Intent.ACTION_SEND);
//        sendIntent.setType("text/plain");
//        sendIntent.putExtra(Intent.EXTRA_TEXT, "Welcome to MobDev20");
//        sendIntent.addCategory(Intent.CATEGORY_DEFAULT);
//        sendIntent.setComponent(new ComponentName("com.example.app", "com.example.app.SendMessageActivity"));
//        startActivity(sendIntent);
//
//        //SMS Activity
//        Intent SMSActivity = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:0217846742"));
//        SMSActivity.putExtra("sms body", "Hey, what time is it now friend?");
//        startActivity(SMSActivity);
//
//        //Image Activity
//        Intent imageActivity = new Intent(Intent.ACTION_GET_CONTENT);
//        imageActivity.setType("image/pictures/*");
//        startActivity(imageActivity);
//
//        //Contacts Activity
////        Intent contactActivity = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/"));
//        Intent contactActivity = new Intent(Intent.ACTION_EDIT, Uri.parse("content://contacts/people/"));
//        startActivity(contactActivity);
//
//        //Map Activity
//        String geoCode = "geo:54.5020952,-86.6789717";
//        Intent mapActivity = new Intent(Intent.ACTION_VIEW, Uri.parse(geoCode));
//        startActivity(mapActivity);


    }
}