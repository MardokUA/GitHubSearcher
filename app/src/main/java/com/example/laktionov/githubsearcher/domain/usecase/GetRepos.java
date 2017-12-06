package com.example.laktionov.githubsearcher.domain.usecase;

import com.example.laktionov.githubsearcher.GitHubSearcher;
import com.example.laktionov.githubsearcher.data.DataRepository;
import com.example.laktionov.githubsearcher.data.source.Error;
import com.example.laktionov.githubsearcher.data.source.local.entity.RepositoryInfo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class GetRepos implements UseCase<GetRepos.RequestValues, GetRepos.ResponseValues> {

    @Inject
    DataRepository mDataRepository;
    private Disposable mDisposable;

    public GetRepos() {
        GitHubSearcher.getAppComponent().inject(this);
    }

    @Override
    public void execute(RequestValues values, UseCaseCallBack<ResponseValues> caseCallBack) {
        String query = values.getRequestString();
        Observable<List<RepositoryInfo>> repositories = mDataRepository.findRepositories(query);
        mDisposable = repositories
                .subscribe(repositoryInfos -> {
                    GetRepos.ResponseValues responseValues = new GetRepos.ResponseValues(repositoryInfos);
                    caseCallBack.onSuccess(responseValues);
                }, throwable -> {
                    caseCallBack.onFailure((Error) throwable);
                });
    }

    @Override
    public void cancel() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    public static class RequestValues implements UseCase.RequestValues {

        private final String mRequestString;

        public RequestValues(String mRequestString) {
            this.mRequestString = mRequestString;
        }

        String getRequestString() {
            return mRequestString;
        }
    }

    public static class ResponseValues implements UseCase.ResponseValues {

        private final List<RepositoryInfo> mResponseReps;

        ResponseValues(List<RepositoryInfo> mResponseReps) {
            this.mResponseReps = mResponseReps;
        }

        public List<RepositoryInfo> getResponseReps() {
            return mResponseReps;
        }
    }
}
