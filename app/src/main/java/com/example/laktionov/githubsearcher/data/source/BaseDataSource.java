package com.example.laktionov.githubsearcher.data.source;

import com.example.laktionov.githubsearcher.data.DataSource;
import com.example.laktionov.githubsearcher.data.source.local.entity.RepositoryInfo;

import java.util.List;

public abstract class BaseDataSource implements DataSource {
    @Override
    public void findRepositories(String query, SourceCallBack callBack) {

    }

    @Override
    public void persistResponseData(List<RepositoryInfo> repositoryInfoList) {

    }
}
