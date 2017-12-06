package com.example.laktionov.githubsearcher.data;

import com.example.laktionov.githubsearcher.data.source.local.entity.RepositoryInfo;

import java.util.List;

import io.reactivex.Single;

public interface DataSource {

    Single<List<RepositoryInfo>> findRepositories(String query);

    void persistResponseData(List<RepositoryInfo> repositoryInfoList);

}
