package com.example.laktionov.githubsearcher.data.source.local;

import com.example.laktionov.githubsearcher.data.source.BaseDataSource;
import com.example.laktionov.githubsearcher.data.source.local.database.RepositoryDao;
import com.example.laktionov.githubsearcher.data.source.local.entity.RepositoryInfo;

import java.util.List;

import javax.inject.Singleton;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class LocalDataSource extends BaseDataSource {

    private final RepositoryDao mRepositoryDao;

    public LocalDataSource(RepositoryDao dao) {
        mRepositoryDao = dao;
    }

    @Override
    public void findRepositories(String query, SourceCallBack callBack) {
        mRepositoryDao.getAllLocalData(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callBack::onSuccess);
    }

    @Override
    public void persistResponseData(List<RepositoryInfo> repositoryInfoList) {
        new Thread(() -> {
            mRepositoryDao.deletePersistData();
            mRepositoryDao.persistData(repositoryInfoList.toArray(new RepositoryInfo[repositoryInfoList.size()]));
        }).start();

    }

}
