package com.example.sharedpreference;

import android.content.Context;

public class DataLocalManager {

    private static final String FIRST_INSTALL = "FIRST_INSTALL";
    private static DataLocalManager instance;
    private NewSharedPreference newSharedPreference;

    public static void init(Context context) {
        instance = new DataLocalManager();
        instance.newSharedPreference = new NewSharedPreference(context);
    }

    public static DataLocalManager getInstance() {
        if (instance == null) {
            instance = new DataLocalManager();
        }
        return instance;
    }

    public static void setFirstInstall(boolean isFirst) {
        DataLocalManager.getInstance().newSharedPreference.putBooleanValue(FIRST_INSTALL, isFirst);
    }

    public static boolean getFirstInstall() {
        return DataLocalManager.getInstance().newSharedPreference.getBooleanValue(FIRST_INSTALL);
    }
}
