package com.example.laktionov.githubsearcher.data.source.remote;

import com.example.laktionov.githubsearcher.data.DataSource;
import com.example.laktionov.githubsearcher.data.source.Error;
import com.example.laktionov.githubsearcher.data.source.local.entity.RepositoryInfo;
import com.example.laktionov.githubsearcher.data.source.remote.entity.RemoteRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RemoteDataSource implements DataSource {

    private static RemoteDataSource INSTANCE;

    private SearchApi mSearchApi;

    public static RemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource();
        }
        return INSTANCE;
    }

    private RemoteDataSource() {
        mSearchApi = ApiFactory.getInstance().getRetrofit().create(SearchApi.class);
    }

    @Override
    public void findRepositories(String query, SourceCallBack callBack) {
        mSearchApi.searchRepos(query)
                .observeOn(AndroidSchedulers.mainThread())
                .map(mRepositoryRequest ->
                        transmorph(mRepositoryRequest.getRepos()))
                .subscribeOn(Schedulers.io())
                .subscribe(callBack::onSuccess, throwable -> callBack.onFailure(new Error(Error.ERROR_FOUND_NOTHING)));
    }

    private List<RepositoryInfo> transmorph(List<RemoteRepository> remoteRepositoryList) {
        List<RepositoryInfo> repositoryInfoList = new ArrayList<>(100);
        for (RemoteRepository remoteRepository : remoteRepositoryList) {
            RepositoryInfo item = new RepositoryInfo.Builder()
                    .withInfoId(remoteRepository.getId())
                    .withNameId(remoteRepository.getFullName())
                    .withUrl(remoteRepository.getUrl())
                    .withStatus(remoteRepository.getIsPrivate())
                    .withLogin(remoteRepository.getOwner().getLogin())
                    .withAvatar(remoteRepository.getOwner().getAvatarUrl())
                    .withUserUrl(remoteRepository.getOwner().getUrl())
                    .withCreatedDate(remoteRepository.getCreated())
                    .build();
            repositoryInfoList.add(item);
        }
        return repositoryInfoList;
    }

    @Override
    public void persistLastResponseData(List<RepositoryInfo> repositoryInfoList) {

    }
}
