package com.example.laktionov.githubsearcher.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.laktionov.githubsearcher.data.source.local.database.LocalDataBase;
import com.example.laktionov.githubsearcher.data.source.local.database.RepositoryDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LocalModule {

    private static final String DATABASE_NAME = "repo_db";

    @Provides
    @Singleton
    LocalDataBase providesLocalDB(Application application) {
        return Room.databaseBuilder(application.getApplicationContext(), LocalDataBase.class, DATABASE_NAME).build();
    }

    @Provides
    @Singleton
    RepositoryDao providesRepositoryDao(LocalDataBase localDataBase) {
        return localDataBase.getDao();
    }
}
