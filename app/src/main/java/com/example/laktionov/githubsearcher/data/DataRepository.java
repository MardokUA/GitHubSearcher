package com.example.laktionov.githubsearcher.data;

import com.example.laktionov.githubsearcher.data.source.BaseDataSource;
import com.example.laktionov.githubsearcher.data.source.Error;
import com.example.laktionov.githubsearcher.data.source.local.LocalDataSource;
import com.example.laktionov.githubsearcher.data.source.local.entity.RepositoryInfo;
import com.example.laktionov.githubsearcher.data.source.remote.RemoteDataSource;

import java.util.List;

import javax.inject.Singleton;

@Singleton
public class DataRepository extends BaseDataSource {

    private final LocalDataSource mLocalDataSource;
    private final RemoteDataSource mRemoteDataSource;

    public DataRepository(LocalDataSource localDataSource, RemoteDataSource remoteDataSource) {
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
    }

    @Override
    public void findRepositories(String query, SourceCallBack callBack) {
        mLocalDataSource.findRepositories(query, new SourceCallBack() {
            @Override
            public void onSuccess(List<RepositoryInfo> repositories) {
                if (repositories.isEmpty()) {
                    getRemoteData(query, callBack);
                } else {
                    callBack.onSuccess(repositories);
                }
            }

            @Override
            public void onFailure(Error error) {
                callBack.onFailure(new Error(Error.ERROR_INTERNAL));
            }
        });
    }

    private void getRemoteData(String query, SourceCallBack callBack) {
        mRemoteDataSource.findRepositories(query, new SourceCallBack() {
            @Override
            public void onSuccess(List<RepositoryInfo> repositories) {
                if (repositories.isEmpty()) {
                    callBack.onFailure(new Error(Error.ERROR_FOUND_NOTHING));
                } else {
                    callBack.onSuccess(repositories);
                    mLocalDataSource.persistResponseData(repositories);
                }
            }

            @Override
            public void onFailure(Error error) {
                callBack.onFailure(error);
            }
        });
    }
}
