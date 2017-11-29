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
    public void onSearchCLick(String query) {
        if (query.trim().length() == 0) {
            showError(new Error(Error.ERROR_EMPTY_QUERY));
        } else {
            checkRepos(query);
        }
    }

    private void checkRepos(String query) {
        mGetUseCase.execute(new GetRepos.RequestValues(query), new UseCase.UseCaseCallBack<GetRepos.ResponseValues>() {
            @Override
            public void onSuccess(GetRepos.ResponseValues response) {
                showResult(response.getResponseReps());
            }

            @Override
            public void onFailure(Error error) {
                showError(error);
            }
        });
    }

    private void showResult(List<RepositoryInfo> repositoryInfoList) {
        if (mView != null) {
            mView.showSearchResult(repositoryInfoList);
        }
    }

    private void showError(Error error) {
        if (mView != null) {
            mView.showErrorMessage(error.getError());
        }
    }

    @Override
    public void onDestroy() {
        mView = null;
    }
}
