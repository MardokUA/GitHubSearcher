package com.example.laktionov.githubsearcher.data.source.remote.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Owner {

    @Expose
    @SerializedName("login")
    private String mLogin;
    @Expose
    @SerializedName("id")
    private Integer mId;
    @Expose
    @SerializedName("avatar_url")
    private String mAvatarUrl;
    @Expose
    @SerializedName("html_url")
    private String mUrl;

    public Owner() {
    }

    public String getLogin() {
        return mLogin;
    }

    public void setLogin(String mLogin) {
        this.mLogin = mLogin;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer mId) {
        this.mId = mId;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public void setAvatarUrl(String mAvatarUrl) {
        this.mAvatarUrl = mAvatarUrl;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }
}
