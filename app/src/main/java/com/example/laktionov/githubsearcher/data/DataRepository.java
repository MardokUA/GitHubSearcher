package com.example.laktionov.githubsearcher.data;

import com.example.laktionov.githubsearcher.data.source.Error;
import com.example.laktionov.githubsearcher.data.source.local.LocalDataSource;
import com.example.laktionov.githubsearcher.data.source.local.entity.RepositoryInfo;
import com.example.laktionov.githubsearcher.data.source.remote.RemoteDataSource;

import java.util.List;

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
            mRemoteDataSource.findRepositories(query, new SourceCallBack() {
                @Override
                public void onSuccess(List<RepositoryInfo> repositories) {
                    if (repositories.isEmpty()) {
                        callBack.onFailure(new Error(Error.ERROR_FOUND_NOTHING));
                    } else {
                        callBack.onSuccess(repositories);
                        mLocalDataSource.persistLastResponseData(repositories);
                    }
                }

                @Override
                public void onFailure(Error error) {
                    callBack.onFailure(error);
                }
            });
        }
    }

    @Override
    public void persistLastResponseData(List<RepositoryInfo> repositoryInfoList) {

    }

    private boolean isCurrentAndLastQueriesAreSame(String query) {
        return mLastRequestSequence != null && mLastRequestSequence.toLowerCase().equals(query.toLowerCase());
    }
}
