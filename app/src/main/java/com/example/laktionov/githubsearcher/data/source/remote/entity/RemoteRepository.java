package com.example.laktionov.githubsearcher.data.source.remote.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RemoteRepository {

    @Expose
    @SerializedName("id")
    public Integer mId;
    @Expose
    @SerializedName("name")
    public String mShortName;
    @Expose
    @SerializedName("full_name")
    public String mFullName;
    @Expose
    @SerializedName("owner")
    public Owner mOwner;
    @Expose
    @SerializedName("private")
    public Boolean mIsPrivate;
    @Expose
    @SerializedName("html_url")
    public String mUrl;
    @Expose
    @SerializedName("created_at")
    public String mCreated;

    public RemoteRepository() {
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer mId) {
        this.mId = mId;
    }

    public String getShortName() {
        return mShortName;
    }

    public void setShortName(String nShortName) {
        this.mShortName = nShortName;
    }

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String mFullName) {
        this.mFullName = mFullName;
    }

    public Owner getOwner() {
        return mOwner;
    }

    public void setOwner(Owner mOwner) {
        this.mOwner = mOwner;
    }

    public Boolean getmIsPrivate() {
        return mIsPrivate;
    }

    public void setIsPrivate(Boolean mIsPrivate) {
        this.mIsPrivate = mIsPrivate;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getCreated() {
        return mCreated;
    }

    public void setCreated(String mCreated) {
        this.mCreated = mCreated;
    }
}

