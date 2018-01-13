package com.ssaenz.xingchallenge;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * View holder to show the GitHubRepository information.
 *
 */

class GitHubRepoHolder extends RecyclerView.ViewHolder implements GitHubRepoView {

    TextView repoName;
    TextView ownerName;
    TextView repoDescription;

    public GitHubRepoHolder(View repoItemView) {
        super(repoItemView);
        repoName = repoItemView.findViewById(R.id.tv_repo_name);
        ownerName = repoItemView.findViewById(R.id.tv_repo_owner);
        repoDescription = repoItemView.findViewById(R.id.tv_repo_description);
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
}
