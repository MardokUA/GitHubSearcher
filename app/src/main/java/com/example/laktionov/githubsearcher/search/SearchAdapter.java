package com.example.laktionov.githubsearcher.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.laktionov.githubsearcher.R;
import com.example.laktionov.githubsearcher.custom.CircleTransform;
import com.example.laktionov.githubsearcher.data.source.local.entity.RepositoryInfo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.example.laktionov.githubsearcher.custom.CircleTransform.IMAGE_SIZE;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private List<RepositoryInfo> mRepositoryList;
    private String[] mStatusText;

    SearchAdapter(Context context) {
        mRepositoryList = new ArrayList<>();
        mStatusText = new String[]{context.getString(R.string.private_status_text), context.getString(R.string.public_status_text)};
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repository_info, parent, false);
        return new SearchViewHolder(item);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        RepositoryInfo repositoryInfo = mRepositoryList.get(position);

        Picasso.with(holder.mAvatar.getContext())
                .load(repositoryInfo.getAvatarUrl())
                .resize(IMAGE_SIZE, IMAGE_SIZE)
                .centerCrop()
                .placeholder(R.drawable.image_placeholder)
                .transform(new CircleTransform())
                .into(holder.mAvatar);

        holder.mRepositoryName.setText(repositoryInfo.getFullName());
        holder.mRepositoryUrl.setText(repositoryInfo.getRepoUrl());
        holder.mOwnerLogin.setText(repositoryInfo.getUserLogin());
        holder.mOwnerUrl.setText(repositoryInfo.getUserUrl());
        holder.mRepositoryStatus.setText(repositoryInfo.getPrivate() == 1 ? mStatusText[0] : mStatusText[1]);
        holder.mRepositoryCreatedTime.setText(repositoryInfo.getCreated());
    }

    @Override
    public int getItemCount() {
        return mRepositoryList.size();
    }

    void addItems(List<RepositoryInfo> items) {
        mRepositoryList.clear();
        mRepositoryList.addAll(items);
        notifyDataSetChanged();
    }

    class SearchViewHolder extends RecyclerView.ViewHolder {

        private ImageView mAvatar;
        private TextView mRepositoryName;
        private TextView mRepositoryUrl;

        private TextView mOwnerLogin;
        private TextView mOwnerUrl;
        private TextView mRepositoryStatus;
        private TextView mRepositoryCreatedTime;

        SearchViewHolder(View itemView) {
            super(itemView);

            mAvatar = itemView.findViewById(R.id.info_avatar);
            mRepositoryName = itemView.findViewById(R.id.info_repo_name);
            mRepositoryUrl = itemView.findViewById(R.id.info_repo_url);

            mOwnerLogin = itemView.findViewById(R.id.info_user_login_value);
            mOwnerUrl = itemView.findViewById(R.id.info_user_url_value);
            mRepositoryStatus = itemView.findViewById(R.id.info_repo_status_value);
            mRepositoryCreatedTime = itemView.findViewById(R.id.info_repo_created_value);
        }
    }
}
