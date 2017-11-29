package com.example.laktionov.githubsearcher.data.source.local.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.laktionov.githubsearcher.data.source.local.entity.RepositoryInfo;

@Database(entities = {RepositoryInfo.class}, version = 1)
public abstract class LocalDataBase extends RoomDatabase {
    public abstract RepositoryDao getDao();
}
