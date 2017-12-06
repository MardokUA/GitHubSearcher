package com.example.laktionov.githubsearcher.data.source;

import com.example.laktionov.githubsearcher.data.source.local.entity.RepositoryInfo;

import java.util.List;

import io.reactivex.Observable;

public interface RepositoryContract {

    Observable<List<RepositoryInfo>> findRepositories(String query);
}
