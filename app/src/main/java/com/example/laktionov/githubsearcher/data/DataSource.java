package com.example.laktionov.githubsearcher.data;

import com.example.laktionov.githubsearcher.data.source.Error;
import com.example.laktionov.githubsearcher.data.source.local.entity.RepositoryInfo;

import java.util.List;

import io.reactivex.Maybe;

public interface DataSource {

    void findRepositories(String query, SourceCallBack callBack);

    void persistResponseData(List<RepositoryInfo> repositoryInfoList);

    interface SourceCallBack {

        void onSuccess(List<RepositoryInfo> repositories);

        void onFailure(Error error);
    }
}
