package com.ssaenz.xingchallenge.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ssaenz.xingchallenge.R;
import com.ssaenz.xingchallenge.domain.GitHubRepository;
import com.ssaenz.xingchallenge.ui.presenter.GitHubRepoPresenter;

import java.util.List;

/**
 * Provides a binding between the view and the presenter.
 */

public class GitHubRepoAdapter extends RecyclerView.Adapter<GitHubRepoHolder> {

    GitHubRepoPresenter presenter;

    View.OnLongClickListener onLongClickListener;

    public GitHubRepoAdapter(GitHubRepoPresenter presenter) {
        this.presenter = presenter;
    }

    public void setOnLongClickListener (View.OnLongClickListener listener) {
        this.onLongClickListener = listener;
    }

    @Override
    public GitHubRepoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View repoItemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_repo, parent, false);
        if (this.onLongClickListener != null) {
            repoItemView.setOnLongClickListener(this.onLongClickListener);
        }
        return new GitHubRepoHolder(repoItemView);
    }

    @Override
    public void onBindViewHolder(GitHubRepoHolder holder, int position) {
        this.presenter.showRepositoryAtPosition(holder, position);
    }

    public void addRepositories (List<GitHubRepository> repositories) {
        this.presenter.addRepositories(repositories);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return presenter.getRepositoriesCount();
    }
}
