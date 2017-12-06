package com.example.laktionov.githubsearcher.di.module;

import com.example.laktionov.githubsearcher.search.SearchPresenterImp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Provides
    @Singleton
    SearchPresenterImp providePresenter() {
        return new SearchPresenterImp();
    }
}
