package com.example.laktionov.githubsearcher.data.source.remote;

import com.example.laktionov.githubsearcher.data.DataSource;
import com.example.laktionov.githubsearcher.data.source.local.entity.RepositoryInfo;
import com.example.laktionov.githubsearcher.data.source.remote.entity.RemoteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class RemoteDataSource implements DataSource {

    private final SearchApi mSearchApi;

    public RemoteDataSource(SearchApi searchApi) {
        mSearchApi = searchApi;
    }

    @Override
    public Single<List<RepositoryInfo>> findRepositories(String query) {
        return mSearchApi.searchRepos(query)
                .observeOn(AndroidSchedulers.mainThread())
                .map(mRepositoryRequest ->
                        transmorph(mRepositoryRequest.getRepos(), query))
                .subscribeOn(Schedulers.io());
    }

    private List<RepositoryInfo> transmorph(List<RemoteRepository> remoteRepositoryList, String query) {
        List<RepositoryInfo> repositoryInfoList = new ArrayList<>(30);
        for (RemoteRepository remoteRepository : remoteRepositoryList) {
            RepositoryInfo item = new RepositoryInfo.Builder()
                    .withQuery(query)
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
    public void persistResponseData(List<RepositoryInfo> repositoryInfoList) {

    }

}
