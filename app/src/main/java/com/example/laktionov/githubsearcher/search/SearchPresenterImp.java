package com.example.laktionov.githubsearcher.search;

import com.example.laktionov.githubsearcher.data.source.Error;
import com.example.laktionov.githubsearcher.data.source.local.entity.RepositoryInfo;
import com.example.laktionov.githubsearcher.domain.usecase.GetRepos;
import com.example.laktionov.githubsearcher.domain.usecase.UseCase;

import java.util.List;

public class SearchPresenterImp implements SearchContract.Presenter {

    private SearchContract.View mView;
    private GetRepos mGetUseCase;

    SearchPresenterImp() {
        mGetUseCase = new GetRepos();
    }

    @Override
    public void onInit(SearchContract.View view) {
        this.mView = view;
    }

    @Override
    public void showLastRequestResults(String query) {
        if (isQueryIsValid(query))
            checkRepos(query);
    }

    @Override
    public void onSearchCLick(String query) {
        if (isQueryIsValid(query)) {
            checkRepos(query);
        } else {
            showError(new Error(Error.ERROR_EMPTY_QUERY));
        }
    }

    private void checkRepos(String query) {
        changeProgressState(true);
        mGetUseCase.execute(new GetRepos.RequestValues(query), new UseCase.UseCaseCallBack<GetRepos.ResponseValues>() {
            @Override
            public void onSuccess(GetRepos.ResponseValues response) {
                changeProgressState(false);
                showResult(response.getResponseReps());
            }

            @Override
            public void onFailure(Error error) {
                showError(error);
            }
        });
    }

    private void changeProgressState(boolean isShown) {
        if (mView != null) {
            mView.showProgress(isShown);
        }
    }

    private void showResult(List<RepositoryInfo> repositoryInfoList) {
        if (mView != null) {
            mView.showSearchResult(repositoryInfoList);
        }
    }

    private void showError(Error error) {
        if (mView != null) {
            changeProgressState(false);
            mView.showErrorMessage(error.getError());
        }
    }

    private boolean isQueryIsValid(String query) {
        return query != null && query.trim().length() > 0;
    }

    @Override
    public void onDestroy() {
        mView = null;
    }

}
