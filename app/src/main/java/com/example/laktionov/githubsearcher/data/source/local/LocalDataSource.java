package com.example.laktionov.githubsearcher.data.source.local;

import android.arch.persistence.room.Room;

import com.example.laktionov.githubsearcher.application.GitHubSearcher;
import com.example.laktionov.githubsearcher.data.DataSource;
import com.example.laktionov.githubsearcher.data.source.local.database.LocalDataBase;
import com.example.laktionov.githubsearcher.data.source.local.entity.RepositoryInfo;

import java.util.List;
import java.util.concurrent.Executor;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LocalDataSource implements DataSource {

    private static final String DATABASE_NAME = "repo_db";

    private static LocalDataSource INSTANCE;
    private LocalDataBase mDataBase;

    public static LocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LocalDataSource();
        }
        return INSTANCE;
    }

    private LocalDataSource() {
        mDataBase = Room.databaseBuilder(GitHubSearcher.getContext(), LocalDataBase.class, DATABASE_NAME).build();
    }

    @Override
    public void findRepositories(String query, SourceCallBack callBack) {
        mDataBase.getDao().getAllLocalData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callBack::onSuccess);
    }

    @Override
    public void persistLastResponseData(List<RepositoryInfo> repositoryInfoList) {
        new Thread(() -> {
            mDataBase.getDao().deletePersistData();
            mDataBase.getDao().persistData(repositoryInfoList.toArray(new RepositoryInfo[repositoryInfoList.size()]));
        }).start();

    }

}
