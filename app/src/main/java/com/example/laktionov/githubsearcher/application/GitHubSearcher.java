package com.example.laktionov.githubsearcher.application;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

public class GitHubSearcher extends Application {

    // No Retrofit2 in required libs
    @SuppressLint("StaticFieldLeak")
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }

    public static Context getContext() {
        return sContext;
    }
}
