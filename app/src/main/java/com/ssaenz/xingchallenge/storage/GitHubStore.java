package com.ssaenz.xingchallenge.storage;

import com.ssaenz.xingchallenge.domain.GitHubRepository;

import java.util.List;

/**
 *
 */

public interface GitHubStore {

    public List<GitHubRepository> listRepos (int page, int size);

    public void saveReposPage(List<GitHubRepository> repos, int page, int size);

    public void clear ();

}
