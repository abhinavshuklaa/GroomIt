package com.avenger.timesaver.ui;

import android.content.Context;
import android.content.SharedPreferences;

public class DarkModePreference {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "education-dark-mode";

    private static final String IS_NIGHT_MODE = "IsNightMode";


    public void DarkModePrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setDarkMode(boolean isFirstTime) {
        editor.putBoolean(IS_NIGHT_MODE, isFirstTime);
        editor.commit();
    }

    public boolean isNightMode() {
        return pref.getBoolean(IS_NIGHT_MODE, true);
    }

}
