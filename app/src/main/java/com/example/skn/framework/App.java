package com.example.skn.framework;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by skn on 2017/7/5.
 */

public class App extends Application {

    private static App app;

    public List<Activity> activityList;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }


    private void init() {
        app = this;
        activityList = new ArrayList<>();
    }

    public static App getApp() {
        return app;
    }
}
