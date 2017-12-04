package com.example.laktionov.githubsearcher.domain.usecase;

import com.example.laktionov.githubsearcher.application.GitHubSearcher;
import com.example.laktionov.githubsearcher.data.DataRepository;
import com.example.laktionov.githubsearcher.data.DataSource;
import com.example.laktionov.githubsearcher.data.source.Error;
import com.example.laktionov.githubsearcher.data.source.local.entity.RepositoryInfo;

import java.util.List;

import javax.inject.Inject;

public class GetRepos implements UseCase<GetRepos.RequestValues, GetRepos.ResponseValues> {

    @Inject
    DataRepository mDataRepository;

    public GetRepos() {
        GitHubSearcher.getAppComponent().inject(this);
    }

    @Override
    public void execute(RequestValues values, UseCaseCallBack<ResponseValues> caseCallBack) {
        String query = values.getRequestString();
        mDataRepository.findRepositories(query, new DataSource.SourceCallBack() {
            @Override
            public void onSuccess(List<RepositoryInfo> repositories) {
                GetRepos.ResponseValues responseValues = new ResponseValues(repositories);
                caseCallBack.onSuccess(responseValues);
            }

            @Override
            public void onFailure(Error error) {
                caseCallBack.onFailure(error);
            }
        });
    }


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

        private final List<RepositoryInfo> mResponseReps;

        public ResponseValues(List<RepositoryInfo> mResponseReps) {
            this.mResponseReps = mResponseReps;
        }

        public List<RepositoryInfo> getResponseReps() {
            return mResponseReps;
        }
    }
}
