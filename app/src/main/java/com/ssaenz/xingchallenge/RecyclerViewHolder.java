package com.ssaenz.xingchallenge;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * View holder to show the GitHubRepository information.
 *
 */

class RecyclerViewHolder extends RecyclerView.ViewHolder {

    TextView repoName;
    TextView ownerName;
    TextView repoDescription;

    public RecyclerViewHolder(View repoItemView) {
        super(repoItemView);
        repoName = repoItemView.findViewById(R.id.tv_repo_name);
        ownerName = repoItemView.findViewById(R.id.tv_repo_owner);
        repoDescription = repoItemView.findViewById(R.id.tv_repo_description);
    }

    public void setRepoName(String repoName) {
        this.repoName.setText(repoName);
    }

    public void setOwnerName(String ownerName) {
        this.ownerName.setText(ownerName);
    }

    public void setRepoDescription(String repoDescription) {
        this.repoDescription.setText(repoDescription);
    }
}
