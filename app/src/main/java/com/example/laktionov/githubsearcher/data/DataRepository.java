package com.example.laktionov.githubsearcher.data;

import com.example.laktionov.githubsearcher.data.source.Error;
import com.example.laktionov.githubsearcher.data.source.RepositoryContract;
import com.example.laktionov.githubsearcher.data.source.local.LocalDataSource;
import com.example.laktionov.githubsearcher.data.source.local.entity.RepositoryInfo;
import com.example.laktionov.githubsearcher.data.source.remote.RemoteDataSource;

import java.util.List;

import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Cancellable;

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
        return Observable.create(emitter ->
                mLocalDataSource.findRepositories(query)
                        .subscribe(localList -> {
                            if (!localList.isEmpty()) {
                                emitter.onNext(localList);
                                emitter.onComplete();
                            } else {
                                Disposable subscribe = mRemoteDataSource.findRepositories(query).subscribe(remoteList -> {
                                    if (!remoteList.isEmpty()) {
                                        emitter.onNext(remoteList);
                                        emitter.onComplete();
                                        persistData(remoteList);
                                    } else {
                                        emitter.tryOnError(new Error(Error.ERROR_FOUND_NOTHING));
                                    }
                                }, throwable -> emitter.tryOnError(new Error(Error.ERROR_SERVER_RESPONSE)));
                                emitter.setCancellable(subscribe::dispose);
                            }
                        }));
    }

    private void persistData(List<RepositoryInfo> remoteRepositories) {
        mLocalDataSource.persistResponseData(remoteRepositories);
    }
}
