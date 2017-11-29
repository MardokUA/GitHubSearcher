package com.example.laktionov.githubsearcher.search;

import android.support.annotation.StringRes;

import com.example.laktionov.githubsearcher.data.source.local.entity.Repository;

import java.util.List;

public interface SearchContract {

    interface Presenter {

        void onInit(SearchContract.View view);

        void onSearchCLick(String query);

        void onDestroy();
    }

    interface View {

        void showSearchResult(List<Repository> repositories);

        void showSearchResult(String message);

        void showErrorMessage(@StringRes int messageId);

    }
}
