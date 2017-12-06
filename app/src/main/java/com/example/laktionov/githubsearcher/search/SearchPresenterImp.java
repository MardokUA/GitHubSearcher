package com.example.laktionov.githubsearcher.search;

import android.os.Handler;
import android.os.Looper;

import com.example.laktionov.githubsearcher.R;
import com.example.laktionov.githubsearcher.data.source.Error;
import com.example.laktionov.githubsearcher.data.source.local.entity.RepositoryInfo;
import com.example.laktionov.githubsearcher.domain.usecase.GetRepos;
import com.example.laktionov.githubsearcher.domain.usecase.UseCase;

import java.util.List;

public class SearchPresenterImp implements SearchContract.Presenter {

    private SearchContract.View mView;
    private GetRepos mGetUseCase;
    private ButtonState mCurrentButtonState = ButtonState.IDLE;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    private String mLastSuccessQuery;

    private enum ButtonState {
        SEARCHING, IDLE
    }

    public SearchPresenterImp() {
        mGetUseCase = new GetRepos();
    }

    @Override
    public void onInit(SearchContract.View view) {
        this.mView = view;
        restoreViewState();
    }

    private void restoreViewState() {
        if (mLastSuccessQuery != null) {
            restoreLoadingState();
        }
    }

    private void restoreLoadingState() {
        changeButtonState(mCurrentButtonState);
        switch (mCurrentButtonState) {
            case SEARCHING:
                if (mView != null) {
                    mView.showProgress(true);
                }
                break;
            case IDLE:
                checkRepos(mLastSuccessQuery);
                break;
        }

    }

    @Override
    public void onSearchCLick(String query) {
        switch (mCurrentButtonState) {
            case IDLE:
                if (isQueryIsValid(query)) {
                    checkRepos(query);
                } else {
                    showError(new Error(Error.ERROR_EMPTY_QUERY));
                }
                break;
            case SEARCHING:
                cancelRequest();
                break;
        }
    }

    private void checkRepos(String query) {
        changeProgressState(true);
        mGetUseCase.execute(new GetRepos.RequestValues(query), new UseCase.UseCaseCallBack<GetRepos.ResponseValues>() {
            @Override
            public void onSuccess(GetRepos.ResponseValues response) {
                changeProgressState(false);
                mHandler.post(() -> {
                    showResult(response.getResponseReps(), query);
                });
            }

            @Override
            public void onFailure(Error error) {
                mHandler.post(() -> {
                    showEmptyScreen();
                    showError(error);
                });
            }
        });
    }

    private void changeProgressState(boolean isShown) {
        changeButtonState(isShown ? ButtonState.SEARCHING : ButtonState.IDLE);
        if (mView != null) {
            mView.showProgress(isShown);
        }
    }

    private void showResult(List<RepositoryInfo> repositoryInfoList, String query) {
        mLastSuccessQuery = query;
        if (mView != null) {
            mView.showSearchResult(repositoryInfoList);
        }
    }

    private void changeButtonState(ButtonState buttonState) {
        mCurrentButtonState = buttonState;
        mView.changeButtonState(buttonState == ButtonState.IDLE ? R.string.search_text : R.string.search_text_cancel);
    }

    private void showEmptyScreen() {
        if (mView != null) {
            mView.clearData();
        }
    }

    private void cancelRequest() {
        mGetUseCase.cancel();
        changeProgressState(false);
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

    }

}
