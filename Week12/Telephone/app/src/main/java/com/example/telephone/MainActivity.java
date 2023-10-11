package com.example.telephone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edtPhone, edtMessage;
    Button btnCall, btnSms;
    TelephonyManager telephonyManager;
    SmsManager smsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtPhone = (EditText) findViewById(R.id.edtPhone);
        edtMessage = (EditText) findViewById(R.id.edtMessage);
        btnCall = (Button) findViewById(R.id.btnCall);
        btnCall.setOnClickListener(view -> {
            makePhoneCall();
        });
        btnSms = (Button) findViewById(R.id.btnSms);
        btnSms.setOnClickListener(view -> {
            sendSMS();
        });
        telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        smsManager = SmsManager.getDefault();
    }

    public void makePhoneCall() {
        String phone = edtPhone.getText().toString();
        if (phone.trim().length() > 0) {
            phone = "tel:" + phone;
            if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.CALL_PHONE) != getPackageManager().PERMISSION_GRANTED) {
                requestPermissions(new String[]{android.Manifest.permission.CALL_PHONE}, 1);
            }
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(phone)));
        } else {
            Toast.makeText(this, "Please enter phone number", Toast.LENGTH_SHORT).show();
        }

    }

    public void sendSMS() {
        String phone = edtPhone.getText().toString();
        String message = edtMessage.getText().toString();
        if (phone.trim().length() > 0 && message.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) != getPackageManager().PERMISSION_GRANTED) {
                requestPermissions(new String[]{android.Manifest.permission.CALL_PHONE}, 1);
            }
            smsManager.sendTextMessage(phone, null, message, null, null);
            Toast.makeText(this, "Message sent", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please enter phone number and message", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults[0] == getPackageManager().PERMISSION_GRANTED) {
            makePhoneCall();
        } else {
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
        }
    }
}