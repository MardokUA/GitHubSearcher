package com.example.laktionov.githubsearcher.data.source.remote.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RepositoryRequest {

    @Expose
    @SerializedName("total_count")
    private Integer mCount;
    @Expose
    @SerializedName("items")
    private List<RemoteRepository> mRepos;

    public RepositoryRequest() {

    }

    public Integer getCount() {
        return mCount;
    }

    public void setCount(Integer mCount) {
        this.mCount = mCount;
    }

    public List<RemoteRepository> getRepos() {
        return mRepos;
    }

    public void setoRepos(List<RemoteRepository> mRepos) {
        this.mRepos = mRepos;
    }
}
