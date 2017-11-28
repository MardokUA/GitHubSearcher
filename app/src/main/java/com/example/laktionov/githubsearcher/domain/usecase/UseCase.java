package com.example.laktionov.githubsearcher.domain.usecase;

public interface UseCase<Q extends UseCase.RequestValues, P extends UseCase.ResponseValues> {

    void execute(Q values, UseCaseCallBack<P> caseCallBack);

    interface RequestValues {

    }

    interface ResponseValues {

    }

    interface UseCaseCallBack<K> {
        void onSuccess(K response);

        void onFailure();
    }

}
