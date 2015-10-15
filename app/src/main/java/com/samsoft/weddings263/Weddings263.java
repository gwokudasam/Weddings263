package com.samsoft.weddings263;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseACL;

/**
 * Created by mister on 10-Sep-2015.
 */
public class Weddings263 extends Application {

    public static final String APP_ID = "Zkm7SXTdhtvfAsNuW0gilw8YZ1yHgcIaZ6Fz6Xia";
    public static final String CLIENT_ID = "9C9lUNyxNhAXyHKJCvKxsjiY6Jo3o13ab6wA08Ri";
    private static final String MASTER_KEY = "rRomBuByx2GlzrIKjl5ciAASXqChTVfa9abIteKf";

    private static final String TAG = Weddings263.class.getSimpleName();

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, APP_ID, CLIENT_ID);
        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);


        Log.i(TAG, "Pass Initialized");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }


}
