package com.example.laktionov.githubsearcher.search;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.laktionov.githubsearcher.data.source.local.entity.RepositoryInfo;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private List<RepositoryInfo> mRepositoryList;

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {

        public SearchViewHolder(View itemView) {
            super(itemView);
        }
    }

}
