package com.ssaenz.xingchallenge.data.network;

import com.ssaenz.xingchallenge.domain.GitHubRepository;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Interface for GitHub API access
 */

public interface GitHubEndpoint {


    @GET("users/{user}/repos")
    public Observable<List<GitHubRepository>> listRepos (@Path("user") String user,
                                                         @Query("page") int page,
                                                         @Query("per_page") int size,
                                                         @Query("access_token") String token);

}
