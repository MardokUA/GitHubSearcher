package com.example.laktionov.githubsearcher.data;

import com.example.laktionov.githubsearcher.data.source.remote.entity.RemoteRepository;

import java.util.List;

public interface DataSource {

    void findRepositories(String query, SourceCallBack callBack);

    void persistLastResponseData();

    interface SourceCallBack {

        void onSuccess(List<RemoteRepository> repositories);

        void onFailure();
    }
}
