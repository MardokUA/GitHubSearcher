package com.example.laktionov.githubsearcher.data.source.local;

import com.example.laktionov.githubsearcher.data.DataSource;
import com.example.laktionov.githubsearcher.data.source.local.database.RepositoryDao;
import com.example.laktionov.githubsearcher.data.source.local.entity.RepositoryInfo;

import java.util.List;

import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class LocalDataSource implements DataSource {

    private final RepositoryDao mRepositoryDao;

    public LocalDataSource(RepositoryDao dao) {
        mRepositoryDao = dao;
    }

    @Override
    public void findRepositories(String query, SourceCallBack sourceCallBack) {
        mRepositoryDao.getCachedLocalDataWithQuery(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(sourceCallBack::onSuccess);
    }

    @Override
    public void persistResponseData(List<RepositoryInfo> repositoryInfoList) {
        new Thread(() -> {
            mRepositoryDao.deletePersistData();
            mRepositoryDao.persistData(repositoryInfoList.toArray(new RepositoryInfo[repositoryInfoList.size()]));
        }).start();

    }

}
