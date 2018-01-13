package com.ssaenz.xingchallenge;

import com.ssaenz.xingchallenge.domain.GitHubRepository;

import java.util.List;

/**
 * The presenter has the responsibility to tell to the view which data have to be shown
 */

public class GitHubRepoPresenter {

    private List<GitHubRepository> repositories;

    public GitHubRepoPresenter(List<GitHubRepository> repositories) {
        this.repositories = repositories;
    }

    public void showRepositoryAtPosition (GitHubRepoView view, int position) {

        GitHubRepository repo = repositories.get(position);
        view.setOwnerName(repo.getOwner().getLogin());
        view.setRepoDescription(repo.getDescription());
        view.setRepoName(repo.getName());
    }

    public int getRepositoriesCount() {
        return repositories.size();
    }
}
