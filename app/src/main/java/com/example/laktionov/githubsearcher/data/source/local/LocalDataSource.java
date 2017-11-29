package com.example.laktionov.githubsearcher.data.source.local;

import android.arch.persistence.room.Room;

import com.example.laktionov.githubsearcher.application.GitHubSearcher;
import com.example.laktionov.githubsearcher.data.DataSource;
import com.example.laktionov.githubsearcher.data.source.local.database.LocalDataBase;
import com.example.laktionov.githubsearcher.data.source.local.entity.RepositoryInfo;

import java.util.List;

public class LocalDataSource implements DataSource {

    private static final String DATABASE_NAME = "repo_db";

    private static LocalDataSource INSTANCE;
    private LocalDataBase mDataBase;

    public static LocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LocalDataSource();
        }
        return INSTANCE;
    }

    private LocalDataSource() {
        mDataBase = Room.databaseBuilder(GitHubSearcher.getContext(), LocalDataBase.class, DATABASE_NAME).build();
    }

    @Override
    public void findRepositories(String query, SourceCallBack callBack) {

    }

    @Override
    public void persistLastResponseData(List<RepositoryInfo> repositoryInfoList) {

    }
}
