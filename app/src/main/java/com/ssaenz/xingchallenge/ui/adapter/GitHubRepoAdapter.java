package com.ssaenz.xingchallenge.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ssaenz.xingchallenge.R;
import com.ssaenz.xingchallenge.domain.GitHubRepository;
import com.ssaenz.xingchallenge.ui.presenter.GitHubRepoPresenter;

import java.util.List;

/**
 * Provides a binding between the view and the presenter.
 */

public class GitHubRepoAdapter extends RecyclerView.Adapter<GitHubRepoHolder> {

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

    public void addRepositories (List<GitHubRepository> repositories) {
        presenter.addRepositories(repositories);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return presenter.getRepositoriesCount();
    }
}
