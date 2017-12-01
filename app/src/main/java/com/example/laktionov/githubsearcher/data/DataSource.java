package com.example.laktionov.githubsearcher.data;

import com.example.laktionov.githubsearcher.data.source.Error;
import com.example.laktionov.githubsearcher.data.source.local.entity.RepositoryInfo;
import com.example.laktionov.githubsearcher.data.source.remote.entity.RemoteRepository;

import java.util.List;

public interface DataSource {

    void findRepositories(String query, SourceCallBack callBack);

    void persistResponseData(List<RepositoryInfo> repositoryInfoList);

    interface SourceCallBack {

        void onSuccess(List<RepositoryInfo> repositories);

        void onFailure(Error error);
    }
}
