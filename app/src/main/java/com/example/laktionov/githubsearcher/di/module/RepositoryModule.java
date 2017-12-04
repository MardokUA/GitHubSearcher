package com.example.laktionov.githubsearcher.di.module;

import com.example.laktionov.githubsearcher.data.DataRepository;
import com.example.laktionov.githubsearcher.data.source.local.LocalDataSource;
import com.example.laktionov.githubsearcher.data.source.local.database.RepositoryDao;
import com.example.laktionov.githubsearcher.data.source.remote.RemoteDataSource;
import com.example.laktionov.githubsearcher.data.source.remote.SearchApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    LocalDataSource providesLocalDataSource(RepositoryDao repositoryDao) {
        return new LocalDataSource(repositoryDao);
    }

    @Provides
    @Singleton
    RemoteDataSource providesRemoteDataSource(SearchApi searchApi) {
        return new RemoteDataSource(searchApi);
    }

    @Provides
    @Singleton
    DataRepository providesDataRepository(LocalDataSource localDataSource, RemoteDataSource remoteDataSource) {
        return new DataRepository(localDataSource, remoteDataSource);
    }
}
