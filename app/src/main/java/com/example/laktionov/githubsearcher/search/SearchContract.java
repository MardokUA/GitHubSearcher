package com.example.laktionov.githubsearcher.search;

import android.support.annotation.StringRes;

import com.example.laktionov.githubsearcher.data.source.local.entity.RepositoryInfo;

import java.util.List;

public interface SearchContract {

    interface Presenter {

        void onInit(SearchContract.View view);

        void onSearchCLick(String query);

        void showLastRequestResults(String query);

        void onDestroy();
    }

    interface View {

        void showSearchResult(List<RepositoryInfo> repositories);

        void showErrorMessage(@StringRes int messageId);

        void showProgress(boolean isShown);

        void setSuccessResult(String result);

    }
}
