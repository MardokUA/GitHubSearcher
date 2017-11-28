package com.example.laktionov.githubsearcher.data;

import com.example.laktionov.githubsearcher.data.source.local.entity.Repository;

import java.util.List;

public interface DataSource {

    void findRepositories(String query, SourceCallBack callBack);

    interface SourceCallBack {

        void onSuccess(List<Repository> repositories);

        void onFailure();
    }
}
