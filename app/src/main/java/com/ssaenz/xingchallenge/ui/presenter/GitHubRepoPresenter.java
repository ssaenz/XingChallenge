package com.ssaenz.xingchallenge.ui.presenter;

import com.ssaenz.xingchallenge.domain.GitHubRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * The presenter has the responsibility to tell to the view which data have to be shown
 */

public class GitHubRepoPresenter {

    private List<GitHubRepository> repositories;

    public GitHubRepoPresenter() {
        this.repositories = new ArrayList<>();
    }

    public void addRepositories (List<GitHubRepository> repositories) {
        this.repositories.addAll(repositories);
    }

    public void showRepositoryAtPosition (GitHubRepoView view, int position) {

        GitHubRepository repo = repositories.get(position);
        view.setOwnerName(repo.getOwner().getLogin());
        view.setRepoDescription(repo.getDescription());
        view.setRepoName(repo.getName());
        view.setFork(repo.isFork());
        view.setTag(repo);
    }

    public int getRepositoriesCount() {
        return repositories.size();
    }
}
