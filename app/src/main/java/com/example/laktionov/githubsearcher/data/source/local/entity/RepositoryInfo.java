package com.example.laktionov.githubsearcher.data.source.local.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "local_reps")
public class RepositoryInfo {

    @PrimaryKey
    @ColumnInfo(name = "_id")
    private Integer mId;
    @ColumnInfo(name = "repo_name")
    private String mFullName;
    @ColumnInfo(name = "repo_url")
    private String mRepoUrl;
    @ColumnInfo(name = "repo_status")
    private Integer mPrivate;
    @ColumnInfo(name = "user_login_name")
    private String mUserLogin;
    @ColumnInfo(name = "user_avatar_url")
    private String mAvatarUrl;
    @ColumnInfo(name = "user_url")
    private String mUserUrl;
    @ColumnInfo(name = "created_time")
    private String mCreated;
    @ColumnInfo(name = "query")
    private String mQuery;

    public RepositoryInfo() {
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer mId) {
        this.mId = mId;
    }

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String mFullName) {
        this.mFullName = mFullName;
    }

    public String getRepoUrl() {
        return mRepoUrl;
    }

    public void setRepoUrl(String mRepoUrl) {
        this.mRepoUrl = mRepoUrl;
    }

    public Integer getPrivate() {
        return mPrivate;
    }

    public void setPrivate(Integer mIsPrivate) {
        this.mPrivate = mIsPrivate;
    }

    public String getUserLogin() {
        return mUserLogin;
    }

    public void setUserLogin(String mLogin) {
        this.mUserLogin = mLogin;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public void setAvatarUrl(String mAvatarUrl) {
        this.mAvatarUrl = mAvatarUrl;
    }

    public String getUserUrl() {
        return mUserUrl;
    }

    public void setUserUrl(String mUserUrl) {
        this.mUserUrl = mUserUrl;
    }

    public String getCreated() {
        return mCreated;
    }

    public void setCreated(String mCreated) {
        this.mCreated = mCreated;
    }

    public String getQuery() {
        return mQuery;
    }

    public void setQuery(String query) {
        mQuery = query;
    }

    public static class Builder {

        private RepositoryInfo mRepositoryInfo;

        public Builder() {
            mRepositoryInfo = new RepositoryInfo();
        }

        public Builder withQuery(String query) {
            mRepositoryInfo.mQuery = query;
            return this;
        }

        public Builder withInfoId(Integer id) {
            mRepositoryInfo.mId = id;
            return this;
        }

        public Builder withNameId(String name) {
            mRepositoryInfo.mFullName = name;
            return this;
        }

        public Builder withUrl(String url) {
            mRepositoryInfo.mRepoUrl = url;
            return this;
        }

        public Builder withStatus(Boolean isPrivate) {
            mRepositoryInfo.mPrivate = isPrivate ? 1 : 0;
            return this;
        }

        public Builder withLogin(String login) {
            mRepositoryInfo.mUserLogin = login;
            return this;
        }

        public Builder withAvatar(String url) {
            mRepositoryInfo.mAvatarUrl = url;
            return this;
        }

        public Builder withUserUrl(String userUrl) {
            mRepositoryInfo.mUserUrl = userUrl;
            return this;
        }

        public Builder withCreatedDate(String createdDate) {
            mRepositoryInfo.mCreated = createdDate.replaceAll("([T]\\d)|Z", " ");
            return this;
        }

        public RepositoryInfo build() {
            return mRepositoryInfo;
        }
    }
}
