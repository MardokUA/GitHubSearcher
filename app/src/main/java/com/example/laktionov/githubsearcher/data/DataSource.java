package com.example.laktionov.githubsearcher.data;

public interface DataSource {

    interface SourceCallBack {

        void onSuccess();

        void onFailure();
    }
}
