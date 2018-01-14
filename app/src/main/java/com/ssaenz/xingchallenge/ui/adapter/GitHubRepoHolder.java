package com.ssaenz.xingchallenge.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ssaenz.xingchallenge.R;
import com.ssaenz.xingchallenge.ui.presenter.GitHubRepoView;

/**
 * View holder to show the GitHubRepository information.
 *
 */

public class GitHubRepoHolder extends RecyclerView.ViewHolder implements GitHubRepoView {

    TextView repoName;
    TextView ownerName;
    TextView repoDescription;
    View view;

    public GitHubRepoHolder(View repoItemView) {
        super(repoItemView);
        repoName = repoItemView.findViewById(R.id.tv_repo_name);
        ownerName = repoItemView.findViewById(R.id.tv_repo_owner);
        repoDescription = repoItemView.findViewById(R.id.tv_repo_description);
        view = repoItemView;
    }

    @Override
    public void setRepoName(String repoName) {
        this.repoName.setText(repoName);
    }

    @Override
    public void setOwnerName(String ownerName) {
        this.ownerName.setText(ownerName);
    }

    @Override
    public void setRepoDescription(String repoDescription) {
        this.repoDescription.setText(repoDescription);
    }

    @Override
    public void setFork(boolean isFork) {
        int resource = isFork? R.color.xingGreen : R.color.white;
        view.setBackgroundColor(view.getResources().getColor(resource));
    }
}
