package com.example.laktionov.githubsearcher.data.source.remote.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RemoteRepository {

    @Expose
    @SerializedName("id")
    private Integer mId;
    @Expose
    @SerializedName("name")
    private String mShortName;
    @Expose
    @SerializedName("full_name")
    private String mFullName;
    @Expose
    @SerializedName("owner")
    private Owner mOwner;
    @Expose
    @SerializedName("private")
    private Boolean mIsPrivate;
    @Expose
    @SerializedName("html_url")
    private String mUrl;
    @Expose
    @SerializedName("created_at")
    private String mCreated;

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

    public Boolean getIsPrivate() {
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

