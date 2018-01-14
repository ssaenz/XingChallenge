package com.ssaenz.xingchallenge.data;

import com.ssaenz.xingchallenge.domain.GitHubRepository;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Interface for GitHub API access
 */

public interface GitHubService {


    @GET("users/{user}/repos")
    public Observable<List<GitHubRepository>> listRepos (@Path("user") String user, @Query("page") int page, @Query("per_page") int size);

/*
    private List<GitHubRepository> createMockData (int page, int pageSize) {
        List<GitHubRepository> repositories = new ArrayList<>();
        for (int i = 0; i < pageSize; i ++) {
            repositories.add(createRepo("repo " + page + i, "Description repo " + page + i));
        }
        return repositories;
    }

    private GitHubRepository createRepo (String name, String description) {
        GitHubRepository repo = new GitHubRepository();
        repo.setName(name);
        repo.setDescription(description);
        repo.setOwner(createOwner());
        return repo;
    }

    private GitHubUser createOwner() {
        GitHubUser user = new GitHubUser();
        user.setLogin("xing");
        user.setHtmlUrl("https://github.com/xing");
        return user;
    }*/
}
