package com.example.laktionov.githubsearcher.data.source.remote;

import com.example.laktionov.githubsearcher.data.DataSource;

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

    }
}
