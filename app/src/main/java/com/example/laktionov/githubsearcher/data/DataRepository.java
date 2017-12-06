package com.example.laktionov.githubsearcher.data;

import com.example.laktionov.githubsearcher.data.source.Error;
import com.example.laktionov.githubsearcher.data.source.RepositoryContract;
import com.example.laktionov.githubsearcher.data.source.local.LocalDataSource;
import com.example.laktionov.githubsearcher.data.source.local.entity.RepositoryInfo;
import com.example.laktionov.githubsearcher.data.source.remote.RemoteDataSource;

import java.util.List;

import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class DataRepository implements RepositoryContract {

    private final LocalDataSource mLocalDataSource;
    private final RemoteDataSource mRemoteDataSource;

    public DataRepository(LocalDataSource localDataSource, RemoteDataSource remoteDataSource) {
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
    }

    @Override
    public Observable<List<RepositoryInfo>> findRepositories(String query) {
        return Observable.create(emitter -> mLocalDataSource.findRepositories(query, new DataSource.SourceCallBack() {
            @Override
            public void onSuccess(List<RepositoryInfo> repositories) {
                if (repositories.isEmpty()) {
                    mRemoteDataSource.findRepositories(query, new DataSource.SourceCallBack() {
                        @Override
                        public void onSuccess(List<RepositoryInfo> repositories) {
                            if (!repositories.isEmpty()) {
                                persistData(repositories);
                                emitter.onNext(repositories);
                                emitter.onComplete();
                            } else {
                                emitter.onError(new Error(Error.ERROR_FOUND_NOTHING));
                            }
                        }

                        @Override
                        public void onFailure(Error error) {
                            emitter.onError(error);
                        }
                    });
                } else {
                    emitter.onNext(repositories);
                    emitter.onComplete();
                }
            }

            @Override
            public void onFailure(Error error) {

            }
        }));
    }

    private void persistData(List<RepositoryInfo> remoteRepositories) {
        mLocalDataSource.persistResponseData(remoteRepositories);
    }
}
