package com.example.laktionov.githubsearcher.data;

import com.example.laktionov.githubsearcher.data.source.local.LocalDataSource;
import com.example.laktionov.githubsearcher.data.source.remote.RemoteDataSource;

public class DataRepository implements DataSource {

    private static DataRepository INSTANCE;

    private DataSource mLocalDataSource;
    private DataSource mRemoteDataSoutce;

    public static DataRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataRepository();
        }
        return INSTANCE;
    }

    private DataRepository() {
        mLocalDataSource = LocalDataSource.getInstance();
        mRemoteDataSoutce = RemoteDataSource.getInstance();
    }

    @Override
    public void findRepositories(String query, SourceCallBack callBack) {

    }
}
