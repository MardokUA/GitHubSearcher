package com.example.laktionov.githubsearcher.data.source.remote;

import com.example.laktionov.githubsearcher.data.source.remote.entity.RepositoryRequest;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchApi {

    @GET("/search/repositories")
    Observable<RepositoryRequest> searchRepos(@Query("q") String request);
}
