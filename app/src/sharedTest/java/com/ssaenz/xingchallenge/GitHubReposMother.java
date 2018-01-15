package com.ssaenz.xingchallenge;

import com.ssaenz.xingchallenge.domain.GitHubRepository;
import com.ssaenz.xingchallenge.domain.GitHubUser;

import java.util.ArrayList;
import java.util.List;

/**
 */

public class GitHubReposMother {

    public static final String MOCK_OWNER_LOGIN = "xing";
    private static final String MOCK_OWNER_URL = "https://github.com/xing";

    public static List<GitHubRepository> createMockData (int size) {
        List<GitHubRepository> repositories = new ArrayList<>();
        for (int i = 0; i < size; i ++) {
            repositories.add(createRepo("repo " + i, "Description repo " + i, i % 2 == 0));
        }
        return repositories;
    }

    private static GitHubRepository createRepo (String name, String description, boolean isFork) {
        GitHubRepository repo = new GitHubRepository();
        repo.setName(name);
        repo.setDescription(description);
        repo.setOwner(createOwner());
        repo.setFork(isFork);
        return repo;
    }

    private static GitHubUser createOwner() {
        GitHubUser user = new GitHubUser();
        user.setLogin(MOCK_OWNER_LOGIN);
        user.setHtmlUrl(MOCK_OWNER_URL);
        return user;
    }
}
