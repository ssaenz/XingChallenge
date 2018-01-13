package com.ssaenz.xingchallenge;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Provides a binding between the view and the presenter.
 */

class GitHubRepoAdapter extends RecyclerView.Adapter<GitHubRepoHolder> {

    GitHubRepoPresenter presenter;

    public GitHubRepoAdapter(GitHubRepoPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public GitHubRepoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View repoItemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_repo, parent, false);
        return new GitHubRepoHolder(repoItemView);
    }

    @Override
    public void onBindViewHolder(GitHubRepoHolder holder, int position) {
        presenter.showRepositoryAtPosition(holder, position);
    }

    @Override
    public int getItemCount() {
        return presenter.getRepositoriesCount();
    }
}
