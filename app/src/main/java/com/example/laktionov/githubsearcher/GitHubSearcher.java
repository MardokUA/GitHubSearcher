package com.example.laktionov.githubsearcher;

import android.app.Application;

import com.example.laktionov.githubsearcher.di.component.AppComponent;
import com.example.laktionov.githubsearcher.di.component.DaggerAppComponent;
import com.example.laktionov.githubsearcher.di.module.AppModule;
import com.example.laktionov.githubsearcher.di.module.LocalModule;
import com.example.laktionov.githubsearcher.di.module.NetworkModule;
import com.example.laktionov.githubsearcher.di.module.RepositoryModule;

public class GitHubSearcher extends Application {

    public static AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .localModule(new LocalModule())
                .repositoryModule(new RepositoryModule())
                .build();
    }

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }
}
