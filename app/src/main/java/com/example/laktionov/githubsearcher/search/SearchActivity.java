package com.example.laktionov.githubsearcher.search;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;

import com.example.laktionov.githubsearcher.GitHubSearcher;
import com.example.laktionov.githubsearcher.R;
import com.example.laktionov.githubsearcher.data.source.local.entity.RepositoryInfo;

import java.util.List;

import javax.inject.Inject;

public class SearchActivity extends AppCompatActivity implements SearchContract.View {

    @Inject
    SearchPresenterImp mPresenter;

    private AppCompatButton mSearchButton;
    private AppCompatEditText mSearchEditText;
    private RecyclerView mSearchRecycler;
    private SearchAdapter mSearchAdapter;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        iniViewElements();
        initPresenter();
        initListeners();
    }

    private void initPresenter() {
        GitHubSearcher.getAppComponent().inject(this);
        mPresenter.onInit(this);
    }

    private void iniViewElements() {
        mSearchButton = findViewById(R.id.search_button);
        mSearchEditText = findViewById(R.id.search_query);
        mProgressBar = findViewById(R.id.search_progress_bar);

        mSearchRecycler = findViewById(R.id.search_recycler);
        mSearchRecycler.setLayoutManager(new LinearLayoutManager(this));
        mSearchRecycler.setHasFixedSize(true);
        mSearchAdapter = new SearchAdapter(this);
        mSearchRecycler.setAdapter(mSearchAdapter);
    }

    private void initListeners() {
        mSearchButton.setOnClickListener(view -> {
            closeKeyboard();
            mPresenter.onSearchCLick(mSearchEditText.getText().toString());
        });
    }

    @Override
    public void showSearchResult(List<RepositoryInfo> repositories) {
        if (mSearchAdapter != null) {
            mSearchAdapter.addItems(repositories);
        }
    }

    @Override
    public void showErrorMessage(int messageId) {
        Snackbar.make(findViewById(R.id.search_container), messageId, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress(boolean isShown) {
        mProgressBar.setVisibility(isShown ? View.VISIBLE : View.GONE);
        mSearchRecycler.setVisibility(isShown ? View.GONE : View.VISIBLE);
    }

    @Override
    public void changeButtonState(int buttonText) {
        mSearchButton.setText(getString(buttonText));
    }

    @Override
    public void clearData() {
        if (mSearchAdapter != null) {
            mSearchAdapter.clear();
        }
    }

    private void closeKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(mSearchEditText.getWindowToken(), 0);
        }
        mSearchEditText.clearFocus();
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
        super.onDestroy();
    }
}
