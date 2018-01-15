package com.ssaenz.xingchallenge.data;

import android.content.Context;

import com.ssaenz.xingchallenge.data.network.EndpointFactory;
import com.ssaenz.xingchallenge.data.network.GitHubEndpoint;
import com.ssaenz.xingchallenge.data.storage.GitHubRepoCache;
import com.ssaenz.xingchallenge.domain.GitHubRepository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Provides a cache management with shared preferences.
 */

public class GitHubService {

    private GitHubEndpoint endpoint;
    private GitHubRepoCache cache;

    public GitHubService (Context context, String gitHubUrl) {
        endpoint = new EndpointFactory<GitHubEndpoint>()
                .createEndpoint(GitHubEndpoint.class, gitHubUrl);
        cache = new GitHubRepoCache(context);
    }

    public Observable<List<GitHubRepository>> listRepos (String user, int page, int size, String token) {

        List<GitHubRepository> storedRepos = cache.listRepos(page, size);
        if (storedRepos == null || storedRepos.isEmpty()) {
            Observable<List<GitHubRepository>> sharedResult = endpoint.listRepos(user, page, size, token).share();
            sharedResult.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new DisposableObserver<List<GitHubRepository>>() {
                        @Override
                        public void onNext(List<GitHubRepository> repositories) {
                            cache.saveReposPage(repositories, page, size);
                        }

                        @Override
                        public void onError(Throwable e) { /*Do nothing*/ }

                        @Override
                        public void onComplete() { /*Do nothing*/ }
                    });
            return sharedResult;
        }
        return Observable.just(storedRepos);

    }

}
