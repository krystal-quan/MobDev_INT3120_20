package com.example.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;

public class NewSharedPreference {
    public static final String SHARED_PREFERENCE = "MY_SHARED_PREFERENCE";
    private Context context;

    public NewSharedPreference(Context mContext) {
        this.context = mContext;
    }

    public void putBooleanValue(String key, boolean value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getBooleanValue(String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }
}
