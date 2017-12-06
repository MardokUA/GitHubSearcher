package com.example.laktionov.githubsearcher.data.source.remote;

import com.example.laktionov.githubsearcher.data.source.remote.entity.RepositoryRequest;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchApi {

    @GET("/search/repositories")
    Single<RepositoryRequest> searchRepos(@Query("q") String request);
}
