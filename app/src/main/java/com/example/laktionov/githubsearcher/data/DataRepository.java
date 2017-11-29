package com.example.laktionov.githubsearcher.data;

import com.example.laktionov.githubsearcher.data.source.local.LocalDataSource;
import com.example.laktionov.githubsearcher.data.source.remote.RemoteDataSource;

public class DataRepository implements DataSource {

    private static DataRepository INSTANCE;

    private DataSource mLocalDataSource;
    private DataSource mRemoteDataSource;

    private String mLastRequestSequence;

    public static DataRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataRepository();
        }
        return INSTANCE;
    }

    private DataRepository() {
        mLocalDataSource = LocalDataSource.getInstance();
        mRemoteDataSource = RemoteDataSource.getInstance();
    }

    @Override
    public void findRepositories(String query, SourceCallBack callBack) {
        if (isCurrentAndLastQueriesAreSame(query)) {
            mLocalDataSource.findRepositories(query, callBack);
        } else {
            mLastRequestSequence = query;
            mRemoteDataSource.findRepositories(query, callBack);
            mLocalDataSource.persistLastResponseData();
        }
    }

    @Override
    public void persistLastResponseData() {

    }

    private boolean isCurrentAndLastQueriesAreSame(String query) {
        return mLastRequestSequence != null && mLastRequestSequence.toLowerCase().equals(query.toLowerCase());
    }
}
