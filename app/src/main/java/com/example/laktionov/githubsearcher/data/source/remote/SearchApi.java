package com.example.laktionov.githubsearcher.data.source.remote;

import com.example.laktionov.githubsearcher.data.source.remote.entity.RepositoryRequest;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchApi {

    @GET("/search/repositories")
    Flowable<RepositoryRequest> searchRepos(@Query("q") String request);
}
