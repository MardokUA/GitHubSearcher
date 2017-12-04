package com.example.laktionov.githubsearcher.di.component;

import com.example.laktionov.githubsearcher.data.source.local.LocalDataSource;
import com.example.laktionov.githubsearcher.data.source.remote.RemoteDataSource;
import com.example.laktionov.githubsearcher.di.module.AppModule;
import com.example.laktionov.githubsearcher.di.module.LocalModule;
import com.example.laktionov.githubsearcher.di.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {
        NetworkModule.class,
        AppModule.class,
        LocalModule.class})
@Singleton

public interface AppComponent {
    void inject(RemoteDataSource remoteDataSource);

    void inject(LocalDataSource localDataSource);
}
