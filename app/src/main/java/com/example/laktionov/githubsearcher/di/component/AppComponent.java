package com.example.laktionov.githubsearcher.di.component;

import com.example.laktionov.githubsearcher.di.module.AppModule;
import com.example.laktionov.githubsearcher.di.module.LocalModule;
import com.example.laktionov.githubsearcher.di.module.NetworkModule;
import com.example.laktionov.githubsearcher.di.module.PresenterModule;
import com.example.laktionov.githubsearcher.di.module.RepositoryModule;
import com.example.laktionov.githubsearcher.domain.usecase.GetRepos;
import com.example.laktionov.githubsearcher.search.SearchActivity;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {
        NetworkModule.class,
        AppModule.class,
        RepositoryModule.class,
        LocalModule.class,
        PresenterModule.class})

@Singleton
public interface AppComponent {

    void inject(GetRepos repos);

    void inject(SearchActivity activity);
}
