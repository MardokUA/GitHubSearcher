package com.example.laktionov.githubsearcher.data.source.remote;

import com.example.laktionov.githubsearcher.data.DataSource;
import com.example.laktionov.githubsearcher.data.source.remote.entity.RemoteRepository;
import com.example.laktionov.githubsearcher.data.source.remote.entity.RepositoryRequest;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class RemoteDataSource implements DataSource {

    private static RemoteDataSource INSTANCE;

    private SearchApi mSearchApi;

    public static RemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource();
        }
        return INSTANCE;
    }

    private RemoteDataSource() {
        mSearchApi = ApiFactory.getInstance().getRetrofit().create(SearchApi.class);
    }

    @Override
    public void findRepositories(String query, SourceCallBack callBack) {
        mSearchApi.searchRepos(query).subscribe(repositoryRequest -> {

        }, throwable -> {

        });
    }

    @Override
    public void persistLastResponseData() {

    }
}
