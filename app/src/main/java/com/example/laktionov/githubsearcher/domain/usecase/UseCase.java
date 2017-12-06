package com.example.laktionov.githubsearcher.domain.usecase;

import com.example.laktionov.githubsearcher.data.source.Error;

public interface UseCase<Q extends UseCase.RequestValues, P extends UseCase.ResponseValues> {

    void execute(Q values, UseCaseCallBack<P> caseCallBack);

    void cancel();

    interface RequestValues {

    }

    interface ResponseValues {

    }

    interface UseCaseCallBack<K> {
        void onSuccess(K response);

        void onFailure(Error error);
    }

}
