package com.example.laktionov.githubsearcher.domain.usecase;

import com.example.laktionov.githubsearcher.data.DataRepository;
import com.example.laktionov.githubsearcher.data.DataSource;
import com.example.laktionov.githubsearcher.data.source.local.entity.Repository;
import com.example.laktionov.githubsearcher.data.source.remote.entity.RemoteRepository;

import java.util.List;

public class GetRepos implements UseCase<GetRepos.RequestValues, GetRepos.ResponseValues> {

    private DataRepository mDataRepository;

    public GetRepos() {
        mDataRepository = DataRepository.getInstance();
    }

    @Override
    public void execute(RequestValues values, UseCaseCallBack<ResponseValues> caseCallBack) {
        String query = values.getRequestString();
        mDataRepository.findRepositories(query, mSourceCallback);
    }

    private DataSource.SourceCallBack mSourceCallback = new DataSource.SourceCallBack() {
        @Override
        public void onSuccess(List<Repository> repositories) {

        }

        @Override
        public void onFailure() {

        }
    };

    public static class RequestValues implements UseCase.RequestValues {

        private final String mRequestString;

        public RequestValues(String mRequestString) {
            this.mRequestString = mRequestString;
        }

        public String getRequestString() {
            return mRequestString;
        }
    }

    public static class ResponseValues implements UseCase.ResponseValues {

        private final List<RemoteRepository> mResponseReps;

        public ResponseValues(List<RemoteRepository> mResponseReps) {
            this.mResponseReps = mResponseReps;
        }

        public List<RemoteRepository> getResponseReps() {
            return mResponseReps;
        }
    }
}
