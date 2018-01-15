package com.ssaenz.xingchallenge.data.storage;

import android.content.Context;

import com.ssaenz.xingchallenge.domain.GitHubRepository;

import java.util.Calendar;
import java.util.List;

/**
 *
 */

public class GitHubRepoCache {

    private static final long CACHE_EXPIRATION_PERIOD = 28800000;

    private SharedPreferencesGitHubStore store;

    public GitHubRepoCache(Context context) {
        store = SharedPreferencesGitHubStore.getInstance(context);
    }

    public List<GitHubRepository> listRepos(int page, int size) {

        List<GitHubRepository> storedRepos = null;
        if (!isCacheExpired()) {
            storedRepos = store.listRepos(page, size);
        } else {
            store.clear();
        }
        return storedRepos;
    }

    public void saveReposPage(List<GitHubRepository> repositories, int page, int size) {
        store.saveReposPage(repositories, page, size);
    }

    private boolean isCacheExpired() {
        long now = Calendar.getInstance().getTimeInMillis();
        long lastUpdate = store.lastUpdate();
        return (now - lastUpdate) >= CACHE_EXPIRATION_PERIOD;
    }
}
